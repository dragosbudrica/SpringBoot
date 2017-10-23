package com.kepler.rominfo.dao;

import com.kepler.rominfo.domain.vo.Verification;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VerificationDao {
    void addVerification(@Param("userId") long userId, @Param("token") String token, @Param("expiryDate") Date expiryDate);
    void updateVerification(@Param("userId") long userId, @Param("token") String token, @Param("expiryDate") Date expiryDate);
    Verification getVerificationByToken(@Param("token") String token);
    List<Verification> getAllUnverifiedEntities();
    void removeVerification(@Param("verificationId") long verificationId);
}
