package edu.alenkin.topjavagraduation.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */

public enum Role implements GrantedAuthority {
    USER,
    ADMIN;

    public String getAuthority() {
        return "ROLE_" + name();
    }
}
