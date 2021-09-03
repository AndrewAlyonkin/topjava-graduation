package edu.alenkin.topjavagraduation.rest.controller.v1.restaurant;

import edu.alenkin.topjavagraduation.RestaurantTestData;
import edu.alenkin.topjavagraduation.entity.Restaurant;
import edu.alenkin.topjavagraduation.repository.RestaurantRepository;
import edu.alenkin.topjavagraduation.rest.controller.v1.AbstractControllerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static edu.alenkin.topjavagraduation.RestaurantTestData.GOLDEN_ID;
import static edu.alenkin.topjavagraduation.RestaurantTestData.jsonMatcher;
import static edu.alenkin.topjavagraduation.UserTestData.ADMIN_MAIL;
import static edu.alenkin.topjavagraduation.UserTestData.USER_MAIL;
import static edu.alenkin.topjavagraduation.util.JsonUtil.writeValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class AdminRestaurantControllerTest extends AbstractControllerTest {

    static final String URL = "/rest/v1/admin/restaurants/";

    @Autowired
    private RestaurantRepository repository;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void create() throws Exception {
        Restaurant newRestaurant = RestaurantTestData.getNew();
        perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(newRestaurant)))
                .andExpect(status().isCreated())
                .andExpect(jsonMatcher(newRestaurant, RestaurantTestData::assertNoIdEquals));
    }

    @Test
    void createUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.post(URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void createWithUser() throws Exception {
        perform(MockMvcRequestBuilders.post(URL))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        Restaurant updated = RestaurantTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(URL + GOLDEN_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andExpect(status().isNoContent());
        RestaurantTestData.assertEquals(updated, repository.findById(GOLDEN_ID).orElseThrow());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(URL + GOLDEN_ID))
                .andExpect(status().isNoContent());
        Assertions.assertFalse(repository.findById(GOLDEN_ID).isPresent());
    }
}
