package com.sovadeveloper.entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    CLIENT,
    DOCTOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
