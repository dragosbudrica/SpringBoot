package com.kepler.rominfo.domain.dto;

import java.io.Serializable;

public class ForgotPasswordDto implements Serializable {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
