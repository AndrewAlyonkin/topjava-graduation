package edu.alenkin.topjavagraduation.service;

import edu.alenkin.topjavagraduation.dto.UserTo;
import edu.alenkin.topjavagraduation.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface UserService extends UserDetailsService {
    User create(User user);

    List<User> getAll();

    User get(int id);

    User getByEmail(String email);

    User update(User user);

    void delete(int id);

    User update(UserTo user);

    void enable(int id, boolean enabled);

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

}
