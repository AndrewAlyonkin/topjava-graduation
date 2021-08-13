package edu.alenkin.topjavagraduation.service;

import edu.alenkin.topjavagraduation.entity.User;
import edu.alenkin.topjavagraduation.transferobject.UserTo;
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

    User getWithVotes(int id);

    void update(User user);

    void delete(int id);

    void update(UserTo userTo);

    void enable(int id, boolean enabled);

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

}
