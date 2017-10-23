package com.kepler.rominfo.web;

import com.kepler.rominfo.domain.dto.LoginDto;
import com.kepler.rominfo.domain.vo.User;
import com.kepler.rominfo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.Locale;

@Api(value = "Login Api")
@Controller
public class LoginController {

    private static final Log LOGGER = LogFactory.getLog(LoginController.class);

    private UserService userService;

    private MessageSource messageSource;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ApiOperation(value = "Get login page")
    @GetMapping(value="/login")
    public String login(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "login";
    }

    @ApiOperation(value = "Send credentials")
    @PostMapping(value = "/doLogin")
    public RedirectView loginProcess(HttpSession session,
                                     @ModelAttribute("loginDto") LoginDto loginDto, Locale locale, RedirectAttributes attributes) {
        RedirectView view = null;
        User user = userService.findUser(loginDto.getEmail());
        String message;
        if (user != null) {
            if(!userService.isEnabled(user)) {
                message = messageSource.getMessage("global.warning3", new Object[]{user.getEmail()}, locale);
                attributes.addFlashAttribute("message", message);
                view = new RedirectView("login");
            }
            else if (userService.checkCredentials(user, loginDto.getEmail(), loginDto.getPassword())) {
                session.setAttribute("user", user);
                switch (user.getRole().getRoleName()) {
                    case "Admin":
                        view = new RedirectView("registerProfessor");
                        break;
                    case "Professor":
                        view = new RedirectView("professorCourses");
                        break;
                    default:
                        view = new RedirectView("allCourses");
                        break;
                }
                LOGGER.info(view);
            }
            else {
                LOGGER.info("login failed for " + loginDto.getEmail() + ". Reason: Wrong password!");
                message = messageSource.getMessage("login.error2", null, locale);
                attributes.addFlashAttribute("message", message);
                view = new RedirectView("login");
            }
        } else {
            LOGGER.info("LoginDto failed for " + loginDto.getEmail() + ". Reason: That user does not exists!");
            message = messageSource.getMessage("login.error1", null, locale);
            attributes.addFlashAttribute("message", message);
            view = new RedirectView("login");
        }

        return view;
    }

    @ApiOperation(value = "Logout Action")
    @PostMapping(value = "/logout")
    public RedirectView logout(HttpSession session) {
        RedirectView view = new RedirectView("login");
        LOGGER.debug("invalidating session for " + ((User) session.getAttribute("user")).getEmail());
        session.invalidate();
        return view;
    }
}
