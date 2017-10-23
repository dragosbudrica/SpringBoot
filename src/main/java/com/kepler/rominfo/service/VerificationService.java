package com.kepler.rominfo.service;

import com.kepler.rominfo.dao.VerificationDao;
import com.kepler.rominfo.domain.vo.Verification;
import com.kepler.rominfo.utils.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class VerificationService {

    private VerificationDao verificationDao;

    private static final int DAYS_UNTIL_EXPIRY_DATE_OF_TOKEN = 7;

    @Autowired
    public VerificationService(VerificationDao verificationDao) {
        this.verificationDao = verificationDao;
    }

    private Date computeExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, DAYS_UNTIL_EXPIRY_DATE_OF_TOKEN);
        return cal.getTime();
    }

    private String generateVerificationToken(long userId) {
        return new RandomString().nextString() + userId;
    }

    public Verification getVerificationByToken(String token) {
        return verificationDao.getVerificationByToken(token);
    }

     List<Verification> getAllUnverifiedEntities() {
       return verificationDao.getAllUnverifiedEntities();
    }

    public String addVerification(long userId) {
        String token = generateVerificationToken(userId);
        Date expiryDate = computeExpiryDate();
        verificationDao.addVerification(userId, token, expiryDate);
        return token;
    }

    public String updateVerification(long userId) {
        String token = generateVerificationToken(userId);
        Date expiryDate = computeExpiryDate();
        verificationDao.updateVerification(userId, token, expiryDate);
        return token;
    }

    public void removeVerification(long verificationId) {
        verificationDao.removeVerification(verificationId);
    }
}
