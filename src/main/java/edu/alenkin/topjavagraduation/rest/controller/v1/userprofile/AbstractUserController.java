package edu.alenkin.topjavagraduation.rest.controller.v1.userprofile;

import edu.alenkin.topjavagraduation.entity.User;
import edu.alenkin.topjavagraduation.service.UserService;
import edu.alenkin.topjavagraduation.transferobject.UserTo;
import edu.alenkin.topjavagraduation.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static edu.alenkin.topjavagraduation.util.ValidationUtil.assureIdConsistent;
import static edu.alenkin.topjavagraduation.util.ValidationUtil.checkNew;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public abstract class AbstractUserController {

    @Autowired
    protected UserService service;

    public User create(UserTo userTo) {
        checkNew(userTo);
        return create(UserUtil.createNewFromTo(userTo));
    }

    public User create(User user) {
        checkNew(user);
        return service.create(user);
    }

    public List<User> getAll() {
        return service.getAll();
    }

    public User get(int id) {
        return service.get(id);
    }

    public User getByEmail(String email) {
        return service.getByEmail(email);
    }

    public void delete(int id) {
        service.delete(id);
    }

    public User update(User user, int id) {
        assureIdConsistent(user, id);
        return service.update(user);
    }

    public User update(UserTo userTo, int id) {
        assureIdConsistent(userTo, id);
        return service.update(userTo);
    }

    public void enable(int id, boolean enabled) {
        service.enable(id, enabled);
    }
}

