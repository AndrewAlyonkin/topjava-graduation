package edu.alenkin.topjavagraduation;

import edu.alenkin.topjavagraduation.model.Role;
import edu.alenkin.topjavagraduation.model.User;

import java.util.Collections;
import java.util.Date;

import static edu.alenkin.topjavagraduation.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class UserTestData {
    public static final MatcherUtils.Matcher<User> MATCHER = MatcherUtils.usingIgnoringFieldsComparator(User.class, "registered", "password");
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int NOT_FOUND = 10;
    public static final String ADMIN_MAIL = "admin@gmail.com";
    public static final String USER_MAIL = "user@gmail.com";

    public static final User user = new User(USER_ID, "User", "user@gmail.com", "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN, Role.USER);

    public static void assertNoIdAndPasswordAndRegisteredEquals(User actual, User expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("id", "password", "registered").isEqualTo(expected);
    }

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", true, new Date(), Collections.singleton(Role.USER));
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
