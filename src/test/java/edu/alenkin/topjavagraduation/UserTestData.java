package edu.alenkin.topjavagraduation;

import edu.alenkin.topjavagraduation.entity.Role;
import edu.alenkin.topjavagraduation.entity.User;

import java.util.Collections;
import java.util.Date;

import static edu.alenkin.topjavagraduation.entity.AbstractBaseEntity.START_SEQ;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class UserTestData {
    public static final MatcherFactory.Matcher<User> MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "registered", "password");
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int NOT_FOUND = 10;

    public static final User user = new User(USER_ID, "User", "user@gmail.com", "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN, Role.USER);


    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(user);
        updated.setEmail("update@gmail.com");
        updated.setName("UpdatedName");
        updated.setPassword("newPass");
        updated.setEnabled(false);
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }

}
