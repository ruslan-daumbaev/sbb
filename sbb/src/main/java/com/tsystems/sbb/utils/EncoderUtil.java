package com.tsystems.sbb.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

public class EncoderUtil implements PasswordEncoder {

    public String encode(CharSequence charSequence) {
        return null;
    }

    public boolean matches(CharSequence charSequence, String s) {
        return true;
    }
}
