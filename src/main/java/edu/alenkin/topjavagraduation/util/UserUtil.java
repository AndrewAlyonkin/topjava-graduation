package edu.alenkin.topjavagraduation.util;

import edu.alenkin.topjavagraduation.model.Role;
import edu.alenkin.topjavagraduation.model.User;
import edu.alenkin.topjavagraduation.dto.UserTo;
import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@UtilityClass
public class UserUtil {
    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.USER);
    }

    public static UserTo createTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.hasLength(password) ? passwordEncoder.encode(password): password);
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
