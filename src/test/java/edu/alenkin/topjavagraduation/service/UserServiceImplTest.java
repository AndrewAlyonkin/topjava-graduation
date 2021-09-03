package edu.alenkin.topjavagraduation.service;

import edu.alenkin.topjavagraduation.model.Role;
import edu.alenkin.topjavagraduation.model.User;
import edu.alenkin.topjavagraduation.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static edu.alenkin.topjavagraduation.UserTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */

class UserServiceImplTest extends AbstractServiceTest {

    @Autowired
    private UserService service;

    @Test
    void create() {
        User created = service.create(getNew());
        int newId = created.id();
        User newUser = getNew();
        newUser.setId(newId);
        assertEquals(newUser, created);
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void duplicateMailCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "Duplicate", "admin@gmail.com", "newPass", Role.USER)));
    }

    @Test
    void get() {
        User user = service.get(ADMIN_ID);
        MATCHER.assertMatch(user, admin);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    void getByEmail() {
        User user = service.getByEmail("admin@gmail.com");
        MATCHER.assertMatch(user, admin);
    }

    @Test
    void getByEmailNotFound() {
        assertThrows(NotFoundException.class, () -> service.getByEmail("not found"));
    }

    @Test
    void getAll() {
        List<User> all = service.getAll();
        MATCHER.assertMatch(all, user, admin);
    }

    @Test
    void update() {
        User updated = getUpdated();
        service.update(updated);
        MATCHER.assertMatch(service.get(USER_ID), getUpdated());
    }

    @Test
    void delete() {
        service.delete(USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(USER_ID));
    }

    @Test
    void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }
}
