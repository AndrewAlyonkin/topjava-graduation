package edu.alenkin.topjavagraduation.rest.controller.v1.userprofile;

import edu.alenkin.topjavagraduation.model.User;
import edu.alenkin.topjavagraduation.service.UserService;
import edu.alenkin.topjavagraduation.transferobject.UserTo;
import edu.alenkin.topjavagraduation.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static edu.alenkin.topjavagraduation.util.ValidationUtil.assureIdConsistent;
import static edu.alenkin.topjavagraduation.util.ValidationUtil.checkNew;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Slf4j
public abstract class AbstractUserController {

    @Autowired
    protected UserService service;

    public User create(UserTo userTo) {
        checkNew(userTo);
        log.info("Create new user {}", userTo);
        return create(UserUtil.createNewFromTo(userTo));
    }

    public User create(User user) {
        checkNew(user);
        log.info("Create new user {}", user);
        return service.create(user);
    }

    public List<User> getAll() {
        log.info("Get all users");
        return service.getAll();
    }

    public User get(int id) {
        log.info("Get user {}", id);
        return service.get(id);
    }

    public User getByEmail(String email) {
        log.info("Get user by {}", email);
        return service.getByEmail(email);
    }

    public void delete(int id) {
        log.info("Delete user {}", id);
        service.delete(id);
    }

    public User update(User user, int id) {
        assureIdConsistent(user, id);
        log.info("Update user {}", id);
        return service.update(user);
    }

    public void update(UserTo userTo, int id) {
        assureIdConsistent(userTo, id);
        log.info("Update user {}", id);
        service.update(userTo);
    }

    public void enable(int id, boolean enabled) {
        log.info("Set enabled {} for user {}", enabled, id);
        service.enable(id, enabled);
    }
}

