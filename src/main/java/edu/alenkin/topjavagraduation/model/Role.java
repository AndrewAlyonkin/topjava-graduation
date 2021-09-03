package edu.alenkin.topjavagraduation.model;

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
