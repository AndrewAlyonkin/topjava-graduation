package edu.alenkin.topjavagraduation.entity;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */

//public enum Role implements GrantedAuthority {
public enum Role {
    USER,
    ADMIN;

    //    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
