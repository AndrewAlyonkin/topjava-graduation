package edu.alenkin.topjavagraduation.service;

import edu.alenkin.topjavagraduation.entity.Restaurant;
import edu.alenkin.topjavagraduation.entity.Vote;
import edu.alenkin.topjavagraduation.transferobject.VoteTo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static edu.alenkin.topjavagraduation.RestaurantTestData.*;
import static edu.alenkin.topjavagraduation.UserTestData.USER_ID;
import static edu.alenkin.topjavagraduation.UserTestData.user;
import static edu.alenkin.topjavagraduation.VoteTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;


/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class VoteServiceImplTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    void create() {
        Vote created = service.create(USER_ID, BISON_ID);
        int newId = created.id();
        Vote newVote = new Vote(newId, user, BISON, created.getVoteDateTime());
        assertEquals(newVote, created);
    }

    @Test
    void getByUserIdAndDate() {
        var actual = service.getByUserIdAndDate(USER_ID, LocalDate.of(2021, 8, 18));
        assertIterableEquals(List.of(USER_VOTE_TO1), actual);
    }

    @Test
    void getByRestaurantBetween() {
        var actual = service.getByRestaurantBetween(august18_10am.toLocalDate(), august19_10am.toLocalDate(), GOLDEN_ID);
        assertIterableEquals(List.of(USER_VOTE_TO2, ADMIN_VOTE_TO2), actual);
    }

    @Test
    void getAllByUserId() {
        var actual = service.getAllByUserId(USER_ID);
        assertIterableEquals(List.of(USER_VOTE_TO2, USER_VOTE_TO1), actual);
    }

    @Test
    void getByRestaurantIdAndDate() {
        var actual = service.getByDateAndRestaurantId(august19_10am.toLocalDate(), GOLDEN_ID);
        assertIterableEquals(List.of(USER_VOTE_TO2, ADMIN_VOTE_TO2), actual);
    }

    @Test
    void getByRestaurantId() {
        var actual = service.getByRestaurantId(GOLDEN_ID);
        assertIterableEquals(List.of(USER_VOTE_TO2, ADMIN_VOTE_TO2), actual);
    }

    @Test
    void getAll() {
        var actual = service.getAll();
        assertThat(actual).hasSameElementsAs(allVotes);
    }

    @Test
    void getAllInDateGroupByRestaurant() {
        var actual = service.getAllInDateGroupByRestaurant(august18_10am.toLocalDate());
        Map<Restaurant, List<VoteTo>> expected = Map.of(
                BISON, List.of(USER_VOTE_TO1),
                PRESTIGE, List.of(ADMIN_VOTE_TO1),
                GOLDEN, Collections.emptyList());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getAllGroupByRestaurantId() {
        var actual = service.getAllGroupByRestaurant();
        Map<Restaurant, List<VoteTo>> expected = Map.of(
                BISON, List.of(USER_VOTE_TO1),
                PRESTIGE, List.of(ADMIN_VOTE_TO1),
                GOLDEN, List.of(USER_VOTE_TO2, ADMIN_VOTE_TO2));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getRatingInDateGroupByRestaurantName() {
        var actual = service.getRatingInDateGroupByRestaurantName(august18_10am.toLocalDate());
        Map<String, Integer> expected = Map.of(
                BISON.getName(), 1,
                PRESTIGE.getName(), 1,
                GOLDEN.getName(), 0);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getRatingsGroupByRestaurantName() {
        var actual = service.getRatingsGroupByRestaurantName();
        Map<String, Integer> expected = Map.of(
                BISON.getName(), 1,
                PRESTIGE.getName(), 1,
                GOLDEN.getName(), 2);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getAllInDate() {
        var actual = service.getAllInDate(august19_10am.toLocalDate());
        List<VoteTo> expected = List.of(USER_VOTE_TO2, ADMIN_VOTE_TO2);
        assertThat(actual).hasSameElementsAs(expected);
    }
}
