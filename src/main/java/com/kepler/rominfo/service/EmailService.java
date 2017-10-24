package com.kepler.rominfo.service;

import com.kepler.rominfo.domain.vo.User;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Properties;

@Service
public class EmailService {

    private static final String URL_REGISTRATION_CONFIRMATION = "https://elearning-application2.herokuapp.com/confirmRegistration?token=";
    private static final String URL_RESET_PASSWORD = "https://elearning-application2.herokuapp.com/resetPassword?token=";
    private static final String CONFIRM_REGISTRATION = "Confirm Registration";
    private static final String RESET_PASSWORD = "Reset Password";
    private static final String HOST = "smtp.mail.yahoo.com";
    private static final String USERNAME = "studentenrollment2018@yahoo.com";
    private static final String PASSWORD = "enrollment021293";
    private static final String PORT = "25";
    private static final String FROM = "E-Learning";

     void sendEmailForCourseReminder(String courseName, List<User> students, long timeBeforeCourse) {
        Session session = getPropertiesAndPrepareSession();

        for (User student : students) {
            // Recipient's email ID needs to be mentioned.
            String to = student.getEmail();

            try {
                MimeMessage message = generateMessageForCourseReminder(session, FROM, to, courseName, timeBeforeCourse);
                // Send message
                Transport.send(message);
                System.out.println("The email was sent to " + to);
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
        }
    }

    private Session getPropertiesAndPrepareSession() {
        Properties props = System.getProperties();
        props = setupMailServer(props);

        return Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
    }

    public void sendEmail(String email, String firstName, String token, boolean enabled) throws UnknownHostException {
        Session session = getPropertiesAndPrepareSession();

        try {
            MimeMessage message = generateMessage(session, FROM, email, firstName, token, enabled);
            // Send message
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    private MimeMessage generateMessage(Session session, String from, String email, String firstName, String token, boolean enabled) throws MessagingException, UnknownHostException {
        // Create a default MimeMessage object.
        MimeMessage message = new MimeMessage(session);
        String subject;
        String messageText;

        if(!enabled) {
            subject = "Confirm your email address for E-Learning";
            messageText = "<h3>Hi, <b>" + firstName + "</b>!<br/><br/>This email was used to create an account on E-Learning platform.<br/><br/>To confirm your email address, click on the link below.</h3><br/><br/>" + URL_REGISTRATION_CONFIRMATION + token +"<br/><br/><h5>If you haven't done this, feel free to ignore this email.</h5>";
        } else {
            subject = "Reset Password for E-Learning";
            messageText = "<h3>Hi, <b>" + firstName + "</b>!<br/><br/>You have requested a reset password for your account on E-Learning platform.<br/><br/>In order to reset your current password and set a new one, click on the link below.</h3><br/><br/>" + URL_RESET_PASSWORD + token +"<br/><br/><h5>If you did not make such a request, feel free to ignore this email.</h5>";
        }

        // Set From: header field of the header.
        try {
            message.setFrom(new InternetAddress(USERNAME, from));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // Set To: header field of the header.
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

        // Set Subject: header field
        message.setSubject(subject);

        // Now set the actual message
        message.setText(messageText, null, "html");

        return message;
    }


    private MimeMessage generateMessageForCourseReminder(Session session, String from, String to, String courseName, long timeBeforeCourse) throws MessagingException {
        // Create a default MimeMessage object.
        MimeMessage message = new MimeMessage(session);

        // Set From: header field of the header.
        try {
            message.setFrom(new InternetAddress(USERNAME, from));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // Set To: header field of the header.
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

        // Set Subject: header field
        message.setSubject("Course reminder: " + courseName);

        // Now set the actual message
        message.setText("Hurry up! <b>" + courseName + "</b> is about to start in <b style=\"color: red;\">" + timeBeforeCourse + " minutes</b>!", null, "html");
        return message;
    }

    private Properties setupMailServer(Properties properties) {

        // Setup mail server
        properties.setProperty("mail.smtp.host", HOST);
        properties.setProperty("mail.smtp.port", PORT);
        properties.setProperty("mail.smtp.user", USERNAME);
        properties.setProperty("mail.smtp.password", PASSWORD);
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");

        return properties;
    }

    void sendEmailForConfirmationReminder(User user, String token) throws UnknownHostException {
        Session session = getPropertiesAndPrepareSession();
        String email = user.getEmail();
        String firstName = user.getFirstName();

        try {
            MimeMessage message = generateMessageForConfirmationReminder(session, FROM, email, firstName, token);
            // Send message
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    private MimeMessage generateMessageForConfirmationReminder(Session session, String from, String to, String firstName, String token) throws MessagingException, UnknownHostException {
        // Create a default MimeMessage object.
        MimeMessage message = new MimeMessage(session);

        // Set From: header field of the header.
        try {
            message.setFrom(new InternetAddress(USERNAME, from));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // Set To: header field of the header.
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

        // Set Subject: header field
        message.setSubject("Confirmation reminder");

        // Now set the actual message
        message.setText("<h3>Hi, <b>" + firstName + "</b>!<br/><br/>The verification token will expire in 24 hours. In order to use your account on E-Learning platform, you have to confirm registration by clicking on the link below.<br/><br/></h3><br/><br/>" + URL_REGISTRATION_CONFIRMATION + token + "<br/><br/><h5>If you haven't done this, feel free to ignore this email.</h5>", null, "html");
        return message;
    }
}
