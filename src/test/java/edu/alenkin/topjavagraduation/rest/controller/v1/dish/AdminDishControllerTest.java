package edu.alenkin.topjavagraduation.rest.controller.v1.dish;

import edu.alenkin.topjavagraduation.DishTestData;
import edu.alenkin.topjavagraduation.JsonMatchers;
import edu.alenkin.topjavagraduation.entity.Dish;
import edu.alenkin.topjavagraduation.repository.DishRepository;
import edu.alenkin.topjavagraduation.rest.controller.v1.AbstractControllerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static edu.alenkin.topjavagraduation.DishTestData.currDateSoupBison;
import static edu.alenkin.topjavagraduation.DishTestData.goldenMenu;
import static edu.alenkin.topjavagraduation.JsonMatchers.jsonMatcher;
import static edu.alenkin.topjavagraduation.RestaurantTestData.BISON_ID;
import static edu.alenkin.topjavagraduation.RestaurantTestData.GOLDEN_ID;
import static edu.alenkin.topjavagraduation.UserTestData.ADMIN_MAIL;
import static edu.alenkin.topjavagraduation.UserTestData.USER_MAIL;
import static edu.alenkin.topjavagraduation.entity.AbstractBaseEntity.START_SEQ;
import static edu.alenkin.topjavagraduation.util.JsonUtil.writeValue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class AdminDishControllerTest extends AbstractControllerTest {

    static final String URL = "/rest/v1/admin/restaurants/";
    static final String BISON_SOUP_URL = URL + BISON_ID + "/dishes/" + (START_SEQ + 5);

    @Autowired
    private DishRepository repository;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(BISON_SOUP_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(JsonMatchers.jsonMatcher(currDateSoupBison, Dish.class, DishTestData::assertNoRestaurantEquals));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void save() throws Exception {
        Dish newDish = DishTestData.getNew();
        perform(MockMvcRequestBuilders.post(URL + BISON_ID + "/dishes/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(newDish)))
                .andExpect(status().isCreated())
                .andExpect(jsonMatcher(newDish, Dish.class, JsonMatchers::assertNoIdEquals));
    }

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(BISON_SOUP_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getWithUser() throws Exception {
        perform(MockMvcRequestBuilders.get(BISON_SOUP_URL))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAllForRestaurant() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + GOLDEN_ID + "/dishes/"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(JsonMatchers.jsonMatcher(goldenMenu, Dish.class, (actual, expected) -> assertThat(actual).hasSameElementsAs(expected)));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        Dish updated = DishTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(BISON_SOUP_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andExpect(status().isNoContent());
        DishTestData.assertNoRestaurantEquals(updated, repository.findById(START_SEQ + 5).orElseThrow());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(BISON_SOUP_URL))
                .andExpect(status().isNoContent());
        Assertions.assertFalse(repository.findById(START_SEQ + 5).isPresent());
    }
}
