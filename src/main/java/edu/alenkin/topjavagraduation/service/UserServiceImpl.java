package edu.alenkin.topjavagraduation.service;

import edu.alenkin.topjavagraduation.entity.User;
import edu.alenkin.topjavagraduation.repository.UserRepository;
import edu.alenkin.topjavagraduation.security.AuthorizedUser;
import edu.alenkin.topjavagraduation.transferobject.UserTo;
import edu.alenkin.topjavagraduation.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;

import static edu.alenkin.topjavagraduation.util.UserUtil.prepareToSave;
import static edu.alenkin.topjavagraduation.util.UserUtil.updateFromTo;
import static edu.alenkin.topjavagraduation.util.ValidationUtil.checkNotFound;
import static edu.alenkin.topjavagraduation.util.ValidationUtil.checkNotFoundWithId;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @CacheEvict(value = "users", allEntries = true)
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return prepareAndSave(user);
    }

    private User prepareAndSave(User user) {
        return repository.save(prepareToSave(user, passwordEncoder));
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Cacheable("users")
    public List<User> getAll() {
        return repository.findAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    public User update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(prepareToSave(user, passwordEncoder)), user.getId());
        return repository.findById(user.getId()).get();
    }

    @CacheEvict(value = "users", allEntries = true)
    public User update(UserTo userTo) {
        User user = updateFromTo(get(userTo.getId()), userTo);
        repository.save(prepareToSave(user, passwordEncoder));
        return repository.findById(user.getId()).get();
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }


    @CacheEvict(value = "users", allEntries = true)
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
