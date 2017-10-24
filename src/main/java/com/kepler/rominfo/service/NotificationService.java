package com.kepler.rominfo.service;

import com.kepler.rominfo.domain.dto.CourseDto;
import com.kepler.rominfo.domain.vo.User;
import com.kepler.rominfo.domain.vo.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Service
public class NotificationService {

    private static final long TIME_BEFORE_COURSE = 30;
    private static final long TIME_BEFORE_TOKEN_ERASING = 24 * 60;
    private static final int DELAY_UNTIL_NEXT_COURSE_CHECK = 30 * 60000;
    private static final int DELAY_UNTIL_NEXT_EXPIRATION_DATES_CHECK = 60000;

    private CourseService courseService;
    private UserService userService;
    private EmailService emailService;
    private VerificationService verificationService;

    public NotificationService(CourseService courseService, UserService userService, EmailService emailService, VerificationService verificationService) {
        this.courseService = courseService;
        this.userService = userService;
        this.emailService = emailService;
        this.verificationService = verificationService;
    }

    @Scheduled(fixedDelay = DELAY_UNTIL_NEXT_COURSE_CHECK)
    public void checkClosestCoursesStartTime() {
        Date currentTime = new Date();
        List<CourseDto> allCoursesWithDates = courseService.getAllRecurrentEvents();
        for (CourseDto course : allCoursesWithDates) {
            if (course.getStartTime().after(currentTime) && getDateDiff(currentTime, course.getStartTime(), TimeUnit.MINUTES) == TIME_BEFORE_COURSE) {
                List<User> enrolledStudents = userService.getEnrolledStudents(course.getCourseCode());
                emailService.sendEmailForCourseReminder(course.getCourseName(), enrolledStudents, TIME_BEFORE_COURSE);
            }
        }
    }

    @Scheduled(fixedDelay = DELAY_UNTIL_NEXT_EXPIRATION_DATES_CHECK)
    public void check24HoursBeforeExpiryDates() {
        Date currentTime = new Date();
        List<Verification> allUnverifiedEntities = verificationService.getAllUnverifiedEntities();
        for (Verification verification : allUnverifiedEntities) {
            if (getDateDiff(currentTime, verification.getExpiryDate(), TimeUnit.MINUTES) == TIME_BEFORE_TOKEN_ERASING) {
                try {
                    emailService.sendEmailForConfirmationReminder(verification.getUser(), verification.getToken());
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Scheduled(fixedDelay = DELAY_UNTIL_NEXT_EXPIRATION_DATES_CHECK)
    public void checkExpiryDates() {
        Date currentTime = new Date();
        List<Verification> allUnverifiedEntities = verificationService.getAllUnverifiedEntities();
        for (Verification verification : allUnverifiedEntities) {
            if (getDateDiff(currentTime, verification.getExpiryDate(), TimeUnit.MINUTES) == 0) {
                userService.removeUserAndToken(verification.getUser().getUserId());
            }
        }
    }

    private long getDateDiff(Date oldestDate, Date newestDate, TimeUnit timeUnit) {
        long diffInMillies = newestDate.getTime() - oldestDate.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
