package com.kepler.rominfo.web;

import com.kepler.rominfo.domain.dto.ForgotPasswordDto;
import com.kepler.rominfo.domain.dto.RegisterStudentDto;
import com.kepler.rominfo.domain.dto.ResetPasswordDto;
import com.kepler.rominfo.domain.logic.BusinessMessageAndCode;
import com.kepler.rominfo.domain.vo.User;
import com.kepler.rominfo.domain.vo.Verification;
import com.kepler.rominfo.service.EmailService;
import com.kepler.rominfo.service.UserService;
import com.kepler.rominfo.service.VerificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.jws.WebParam;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Api(value = "Account Api")
@Controller
public class AccountController {

    private static final Logger LOGGER = LogManager.getLogger(AccountController.class);

    private UserService userService;
    private VerificationService verificationService;
    private EmailService emailService;
    private MessageSource messageSource;

    @Autowired
    public AccountController(UserService userService, VerificationService verificationService, MessageSource messageSource, EmailService emailService) {
        this.userService = userService;
        this.verificationService = verificationService;
        this.emailService = emailService;
        this.messageSource = messageSource;
    }

    @ApiOperation(value = "Redirect to New Student Account View")
    @GetMapping(value = "/registerStudent")
    public String newStudentAccount(Model model) {
        model.addAttribute("registerStudentDto", new RegisterStudentDto());
        return "registerStudent";
    }

    @ApiOperation(value = "Redirect to Send Confirmation Email View")
    @GetMapping(value = "/confirmationEmail")
    public String confirmationEmail() {
        return "confirmationEmail";
    }

    @ApiOperation(value = "Redirect to Registration Confirmation View")
    @GetMapping(value = "/registrationConfirmation")
    public String registrationConfirmation() {
        return "registrationConfirmation";
    }

    @ApiOperation(value = "Redirect to Reset Password View")
    @GetMapping(value = "/resetPassword")
    public String resetPassword(Model model, @RequestParam(value = "token", required = false) String token, Locale locale) {
        String message = null;
        if(token != null) {
            Verification verification = verificationService.getVerificationByToken(token);
            if(verification != null) {
                model.addAttribute("resetPasswordDto", new ResetPasswordDto());
            } else {
                message =  messageSource.getMessage("global.invalidRequest", null, locale);
                model.addAttribute("message", message);
            }
        } else {
            message =  messageSource.getMessage("global.invalidRequest", null, locale);
            model.addAttribute("message", message);
        }

        return "resetPasswordForm";
    }

    @ApiOperation(value = "Redirect to Reset Password Form View")
    @GetMapping(value = "/resetPasswordForm")
    public String resetPasswordForm() {
        return "resetPasswordForm";
    }

    @ApiOperation(value = "Redirect to Reset Password Success View")
    @GetMapping(value = "/resetPasswordSuccess")
    public String resetPasswordSuccess() {
        return "resetPasswordSuccess";
    }

    @ApiOperation(value = "Send Email Again")
    @GetMapping(value = "/sendEmailAgain")
    public RedirectView sendConfirmationEmailAgain(@RequestParam(value = "email", required = false) String email, RedirectAttributes attributes, Locale locale) {
        RedirectView view = new RedirectView("confirmationEmail");
        String message = null;

        if(email != null) {
            User user = userService.findUser(email);
            if(user != null && !user.isEnabled()) {
                String token = verificationService.updateVerification(user.getUserId());
                emailService.sendEmail(user.getEmail(), user.getFirstName(), token, false);
                message =  messageSource.getMessage("newAccount.confirm", new Object[]{email}, locale);
                attributes.addFlashAttribute("message", message);
            }
        }

      return view;
    }

    @ApiOperation(value = "Redirect to Forgot Password View")
    @GetMapping(value = "/forgotPassword")
    public String forgotPassword(Model model) {
        model.addAttribute("forgotPasswordDto", new ForgotPasswordDto());
        return "forgotPassword";
    }

    @ApiOperation(value = "Redirect to Reset Password Email View")
    @GetMapping(value = "/resetPasswordEmail")
    public String resetPasswordEmail() {
        return "resetPasswordEmail";
    }

    @ApiOperation(value = "Send email for password reset")
    @PostMapping(value = "/sendEmailForgotPassword")
    public RedirectView sendEmailForgotPassword(@ModelAttribute("resetPasswordDto") ForgotPasswordDto forgotPasswordDto, Locale locale, RedirectAttributes attributes) {
        RedirectView view = null;
        String message = null;

        String email = forgotPasswordDto.getEmail();
        User user = userService.findUser(email);
        if (user == null) {
            message = messageSource.getMessage("account.forgotPasswordEmailWarning1", null, locale);
            attributes.addFlashAttribute("message", message);
            view = new RedirectView("forgotPassword");
        } else if (!user.isEnabled()) {
            message = messageSource.getMessage("global.warning3", null, locale);
            attributes.addFlashAttribute("message", message);
            view = new RedirectView("forgotPassword");
        } else {
            String token = verificationService.addVerification(user.getUserId());
            emailService.sendEmail(user.getEmail(), user.getFirstName(), token, true);
            message = messageSource.getMessage("account.forgotPasswordSendEmail", new Object[]{email}, locale);
            attributes.addFlashAttribute("message", message);
            view = new RedirectView("resetPasswordEmail");
        }

        return view;
    }

    @ApiOperation(value = "Send New Password")
    @PostMapping(value = "/sendNewPassword")
    public RedirectView sendNewPassword(@ModelAttribute("resetPasswordDto") ResetPasswordDto resetPasswordDto, Locale locale, RedirectAttributes attributes) {
        String message;

        String password = resetPasswordDto.getPassword();
        String confirmedPassword = resetPasswordDto.getConfirmedPassword();
        String token = resetPasswordDto.getToken();

        if (!password.equals("") && !confirmedPassword.equals("") && password.equals(confirmedPassword)) {
            Verification verification = verificationService.getVerificationByToken(token);
            userService.changePassword(verification.getUserId(), password);
            verificationService.removeVerification(verification.getVerificationId());
            message = messageSource.getMessage("resetPassword.success", null, locale);
            attributes.addFlashAttribute("message", message);
        }


        return new RedirectView("resetPasswordSuccess");
    }

    @ApiOperation(value = "Redirect to New Professor Account View")
    @GetMapping(value = "/registerProfessor")
    public String newProfessorAccount() {
        return "registerProfessor";
    }

    @ApiOperation(value = "Redirect to Access Denied View")
    @GetMapping(value = "/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

    @ApiOperation(value = "Redirect to Registration Confirmation View")
    @GetMapping(value = "/confirmRegistration")
    public RedirectView confirmRegistration(@RequestParam(value = "token", required = false) String token, Locale locale, RedirectAttributes attributes) {
        String message = null;

        if(token != null) {
            Verification verification = verificationService.getVerificationByToken(token);
            if(verification != null) {
                userService.setEnabled(verification.getUserId());
                verificationService.removeVerification(verification.getVerificationId());
                message = messageSource.getMessage("newAccount.confirmationSuccessful", null, locale);
                attributes.addFlashAttribute("message", message);
            }

        }
        return  new RedirectView("registrationConfirmation");
    }

    @ApiOperation(value = "Add New Professor Account")
    @PostMapping(value = "/addNewProfessorAccount")
    public @ResponseBody
    BusinessMessageAndCode addNewProfessorAccount(@RequestBody Map<String, Object> params, Locale locale) {
        String result = null;
        int code;

        String firstName = (String) params.get("firstName");
        String lastName = (String) params.get("lastName");
        String ssn = (String) params.get("ssn");
        String email = (String) params.get("email");
        String password = (String) params.get("password");
        String role = (String) params.get("role");

        User user = userService.findUser(email);
        if (user == null) {
            userService.addUser(firstName, lastName, Long.parseLong(ssn), email, password, role, true);
            result = messageSource.getMessage("newAccount.success", null, locale);
            code = BusinessMessageAndCode.SUCCESS;
        } else {
            LOGGER.info("register failed for " + email);
            result = messageSource.getMessage("newAccount.error3", null, locale);
            code = BusinessMessageAndCode.ERROR;
        }

        return new BusinessMessageAndCode(result, code);
    }

    @ApiOperation(value = "Add New Student Account")
    @PostMapping(value = "/addNewStudentAccount")
    public RedirectView addNewStudentAccount(@ModelAttribute("registerStudentDto") RegisterStudentDto registerStudentDto, Locale locale, RedirectAttributes attributes) {
        String message = null;
        RedirectView view = null;

        String firstName = registerStudentDto.getFirstName();
        String lastName = registerStudentDto.getLastName();
        String ssn = registerStudentDto.getSsn();
        String email = registerStudentDto.getEmail();
        String password = registerStudentDto.getPassword();
        String role = registerStudentDto.getRole();

        User user = userService.findUser(email);
        if (user == null) {
            userService.addUser(firstName, lastName, Long.parseLong(ssn), email, password, role, false);
            user = userService.findUser(email);
            String token = verificationService.addVerification(user.getUserId());
            emailService.sendEmail(email, firstName, token, user.isEnabled());
            message =  messageSource.getMessage("newAccount.confirm", new Object[]{email}, locale);
            attributes.addFlashAttribute("message", message);
            view = new RedirectView("confirmationEmail");
        } else {
            LOGGER.info("register failed for " + email);
            message = messageSource.getMessage("newAccount.error3", null, locale);
            attributes.addFlashAttribute("message", message);
            view = new RedirectView("registerStudent");
        }

        return view;

    }
}
