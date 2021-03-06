package edu.alenkin.topjavagraduation.service;

import edu.alenkin.topjavagraduation.dto.UserTo;
import edu.alenkin.topjavagraduation.model.User;
import edu.alenkin.topjavagraduation.repository.UserRepository;
import edu.alenkin.topjavagraduation.security.AuthorizedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

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
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    //field injection caused by cyclic dependencies
    @Autowired
    private PasswordEncoder passwordEncoder;

    @CacheEvict(value = "user_cache", allEntries = true)
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return prepareAndSave(user);
    }

    private User prepareAndSave(User user) {
        return repository.save(prepareToSave(user, passwordEncoder));
    }

    @Cacheable("user_cache")
    public User get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Cacheable("user_cache")
    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email).orElse(null), "email=" + email);
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    @CacheEvict(value = "user_cache", allEntries = true)
    public User update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(prepareToSave(user, passwordEncoder)), user.getId());
        return repository.findById(user.getId()).get();
    }

    @CacheEvict(value = "user_cache", allEntries = true)
    public User update(UserTo userTo) {
        User user = updateFromTo(get(userTo.getId()), userTo);
        repository.save(prepareToSave(user, passwordEncoder));
        return repository.findById(user.getId()).get();
    }

    @CacheEvict(value = "user_cache", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }


    @CacheEvict(value = "user_cache", allEntries = true)
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = repository.getByEmail(email.toLowerCase())
                .orElseThrow(() -> new UsernameNotFoundException("User " + email + " is not found"));
        return new AuthorizedUser(user);
    }
}
