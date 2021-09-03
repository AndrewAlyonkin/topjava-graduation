package edu.alenkin.topjavagraduation.rest.controller.v1.restaurant;

import edu.alenkin.topjavagraduation.RestaurantTestData;
import edu.alenkin.topjavagraduation.repository.RestaurantRepository;
import edu.alenkin.topjavagraduation.rest.controller.v1.AbstractControllerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static edu.alenkin.topjavagraduation.RestaurantTestData.*;
import static edu.alenkin.topjavagraduation.UserTestData.USER_MAIL;
import static edu.alenkin.topjavagraduation.util.JsonUtil.writeValue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class UserRestaurantControllerTest extends AbstractControllerTest {

    static final String URL = "/rest/v1/restaurants/";

    @Autowired
    private RestaurantRepository repository;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + GOLDEN_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonMatcher(GOLDEN, RestaurantTestData::assertNoMenuEquals));
    }

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + GOLDEN_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonMatcher(allRestaurants, (actual, expected) -> assertThat(actual).hasSameElementsAs(expected)));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAllWithMenuForToday() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + "today"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonMatcher(allRestaurantsWithMenuInCurrDay, Assertions::assertIterableEquals));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAllWithMenuForDate() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + "date")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(LocalDate.now())))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonMatcher(allRestaurantsWithMenuInCurrDay, Assertions::assertIterableEquals));
    }
}
