package edu.alenkin.topjavagraduation.rest.controller.v1.vote;

import edu.alenkin.topjavagraduation.JsonMatchers;
import edu.alenkin.topjavagraduation.VoteTestData;
import edu.alenkin.topjavagraduation.dto.VoteTo;
import edu.alenkin.topjavagraduation.rest.controller.v1.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static edu.alenkin.topjavagraduation.RestaurantTestData.PRESTIGE_ID;
import static edu.alenkin.topjavagraduation.UserTestData.ADMIN_MAIL;
import static edu.alenkin.topjavagraduation.UserTestData.USER_MAIL;
import static edu.alenkin.topjavagraduation.VoteTestData.ADMIN_VOTE_TO1;
import static edu.alenkin.topjavagraduation.VoteTestData.USER_VOTE_TO1;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class AdminVoteControllerTest extends AbstractControllerTest {

    static final String URL = "/rest/v1/admin/votes/";

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getByRestaurantInDate() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + "/restaurant")
                .param("restId", String.valueOf(PRESTIGE_ID))
                .param("date", "2021-08-18"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(JsonMatchers.jsonMatcher(List.of(ADMIN_VOTE_TO1),
                        VoteTo.class, VoteTestData::assertMatchNoUserAndRestaurantMenu));
    }

    @Test
    void getByRestaurantUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + "/restaurant"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getByRestaurantWithUser() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + "/restaurant"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAllInDate() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + "/date")
                .param("date", "2021-08-18"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(JsonMatchers.jsonMatcher(List.of(USER_VOTE_TO1, ADMIN_VOTE_TO1),
                        VoteTo.class, VoteTestData::assertMatchNoUserAndRestaurantMenu));
    }
}
