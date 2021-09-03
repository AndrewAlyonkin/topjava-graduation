package edu.alenkin.topjavagraduation.rest.controller.v1.userprofile;

import edu.alenkin.topjavagraduation.JsonMatchers;
import edu.alenkin.topjavagraduation.UserTestData;
import edu.alenkin.topjavagraduation.entity.User;
import edu.alenkin.topjavagraduation.repository.UserRepository;
import edu.alenkin.topjavagraduation.rest.controller.v1.AbstractControllerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static edu.alenkin.topjavagraduation.JsonMatchers.jsonMatcher;
import static edu.alenkin.topjavagraduation.UserTestData.*;
import static edu.alenkin.topjavagraduation.util.JsonUtil.writeValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class UserProfileControllerTest extends AbstractControllerTest {

    static final String URL = "/rest/v1/profile/";

    @Autowired
    private UserRepository repository;

    @Test
    void register() throws Exception {
        User newUser = UserTestData.getNew();
        perform(MockMvcRequestBuilders.post(URL + "register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(newUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonMatcher(newUser, User.class, UserTestData::assertNoIdAndPasswordAndRegisteredEquals));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(JsonMatchers.jsonMatcher(user, User.class, Assertions::assertEquals));
    }

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void update() throws Exception {
        User updated = UserTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andExpect(status().isNoContent());
        assertEquals(updated, repository.findById(USER_ID).orElseThrow());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(URL))
                .andExpect(status().isNoContent());
        Assertions.assertFalse(repository.findById(USER_ID).isPresent());
    }
}
