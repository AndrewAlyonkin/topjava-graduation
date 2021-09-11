package edu.alenkin.topjavagraduation.rest.controller.v1.vote;

import edu.alenkin.topjavagraduation.JsonMatchers;
import edu.alenkin.topjavagraduation.VoteTestData;
import edu.alenkin.topjavagraduation.dto.VoteTo;
import edu.alenkin.topjavagraduation.rest.controller.v1.AbstractControllerTest;
import edu.alenkin.topjavagraduation.service.VoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

import static edu.alenkin.topjavagraduation.JsonMatchers.jsonMatcher;
import static edu.alenkin.topjavagraduation.RestaurantTestData.BISON_ID;
import static edu.alenkin.topjavagraduation.UserTestData.USER_MAIL;
import static edu.alenkin.topjavagraduation.VoteTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class UserVoteControllerTest extends AbstractControllerTest {

    static final String URL = "/rest/v1/votes/";

    @Autowired
    private VoteService service;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void create() throws Exception {
        LocalTime expiration = service.getVoteTimeExpiration();
        service.setVoteTimeExpiration(LocalTime.MAX);

        perform(MockMvcRequestBuilders.post(URL).param("restId",String.valueOf(BISON_ID))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonMatcher(getNewVoteTo(), VoteTo.class, VoteTestData::assertEqualsNoIdAndDateTime));

        service.setVoteTimeExpiration(expiration);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void createExpired() throws Exception {
        LocalTime expiration = service.getVoteTimeExpiration();
        service.setVoteTimeExpiration(LocalTime.MIN);

        perform(MockMvcRequestBuilders.post(URL).param("restId",String.valueOf(BISON_ID))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());

        service.setVoteTimeExpiration(expiration);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void update() throws Exception {
        LocalTime expiration = service.getVoteTimeExpiration();
        service.setVoteTimeExpiration(LocalTime.MAX);

        perform(MockMvcRequestBuilders.put(URL).param("restId",String.valueOf(BISON_ID))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonMatcher(getUpdated(), VoteTo.class, VoteTestData::assertEqualsNoIdAndDateTime));

        service.setVoteTimeExpiration(expiration);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateExpired() throws Exception {
        LocalTime expiration = service.getVoteTimeExpiration();
        service.setVoteTimeExpiration(LocalTime.MIN);

        perform(MockMvcRequestBuilders.put(URL).param("restId",String.valueOf(BISON_ID))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());

        service.setVoteTimeExpiration(expiration);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getOwnVotes() throws Exception {
        perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(JsonMatchers.jsonMatcher(List.of(USER_VOTE_TO_NOW, USER_VOTE_TO2, USER_VOTE_TO1),
                        VoteTo.class,
                        (actual, expected) -> {
                            actual.sort(Comparator.comparing(VoteTo::getDateTime).reversed());
                            assertEqualsNoDateTime(actual, expected);
                        }));
    }
}
