package com.kepler.rominfo.service;

import com.kepler.rominfo.dao.RoleDao;
import com.kepler.rominfo.dao.UserDao;
import com.kepler.rominfo.dao.VerificationDao;
import com.kepler.rominfo.domain.vo.Professor;
import com.kepler.rominfo.domain.vo.Student;
import com.kepler.rominfo.domain.vo.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private static final Logger LOG = LogManager.getLogger(UserService.class);

    private UserDao userDao;
    private RoleDao roleDao;
    private static final String STUDENT = "Student";

    private VerificationService verificationService;
    private EmailService emailService;
    private PasswordHashingService passwordHashingService;

    @Autowired
    public UserService(UserDao userDao, RoleDao roleDao, VerificationService verificationService, EmailService emailService, PasswordHashingService passwordHashingService) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.verificationService = verificationService;
        this.emailService = emailService;
        this.passwordHashingService = passwordHashingService;
    }


    @Transactional
    public void addUser(String firstName, String lastName, long ssn, String email, String password, String role, boolean enabled) {
        long roleId = roleDao.getRoleByName(role).getRoleId();
        String hashedPassword = passwordHashingService.generateHash(password);
        User user = setUserProperties(firstName, lastName, ssn, email, hashedPassword, roleId, enabled);
        userDao.addUser(user);
        if(role.equals(STUDENT)) {
            User student = userDao.findByEmail(email);
            userDao.addStudent(student.getUserId());
        } else {
            User professor = userDao.findByEmail(email);
            userDao.addProfessor(professor.getUserId());
        }
    }

    private User setUserProperties(String firstName, String lastName, long ssn, String email, String password, long roleId, boolean enabled) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSsn(ssn);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoleId(roleId);
        user.setEnabled(enabled);

        return user;
    }

    public User findUser(String email) {
        return userDao.findByEmail(email);
    }

    public Student findStudent(String email) {
        return userDao.findStudentByEmail(email);
    }

    public Professor findProfessor(String email) { return userDao.findProfessorByEmail(email); }

    public boolean checkCredentials(User user, String email, String password) {
        String hashedPassword = passwordHashingService.generateHash(password);
        return (user.getEmail().equals(email) && user.getPassword().equals(hashedPassword));
    }

    public List<User> getEnrolledStudents(long courseCode) {
        return userDao.getEnrolledStudents(courseCode);
    }


    public boolean isEnabled(User user) {
        return userDao.isEnabled(user.getUserId());
    }

     void removeUserAndToken(long courseCode) {
        try {
            userDao.removeUserAndToken(courseCode);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setEnabled(long userId) {
        userDao.setEnabled(userId);
    }

    public void changePassword(long userId, String password) {
        String hashedPassword = passwordHashingService.generateHash(password);
        userDao.changePassword(userId, hashedPassword);
    }
}

