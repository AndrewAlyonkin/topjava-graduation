package edu.alenkin.topjavagraduation.security;

import edu.alenkin.topjavagraduation.entity.User;
import edu.alenkin.topjavagraduation.transferobject.UserTo;
import edu.alenkin.topjavagraduation.util.UserUtil;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private UserTo userTo;

    public AuthorizedUser(User user) {
        super(user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                user.isEnabled(),
                user.isEnabled(),
                user.isEnabled(),
                user.getRoles());
        this.userTo = UserUtil.createTo(user);
    }

    public int getId() {
        return userTo.getId();
    }

    public void update(UserTo newTo) {
        userTo = newTo;
    }

    @Override
    public String toString() {
        return userTo.toString();
    }
}
