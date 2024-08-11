package com.example.hrms.entities.concretes;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_CANDIDATE("CANDIDATE"),
    ROLE_EMPLOYEE("EMPLOYEE"),
    ROLE_EMPLOYER("EMPLOYER");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
