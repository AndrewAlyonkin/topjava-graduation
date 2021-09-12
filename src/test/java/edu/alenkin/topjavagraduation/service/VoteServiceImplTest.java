package edu.alenkin.topjavagraduation.service;

import edu.alenkin.topjavagraduation.VoteTestData;
import edu.alenkin.topjavagraduation.dto.VoteTo;
import edu.alenkin.topjavagraduation.exception.ExpiredVoteTimeException;
import edu.alenkin.topjavagraduation.model.Vote;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static edu.alenkin.topjavagraduation.MatcherUtils.assertMatch;
import static edu.alenkin.topjavagraduation.RestaurantTestData.GOLDEN;
import static edu.alenkin.topjavagraduation.RestaurantTestData.GOLDEN_ID;
import static edu.alenkin.topjavagraduation.UserTestData.USER_ID;
import static edu.alenkin.topjavagraduation.UserTestData.user;
import static edu.alenkin.topjavagraduation.VoteTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class VoteServiceImplTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    void create() {
        LocalTime expiration = service.getVoteTimeExpiration();
        service.setVoteTimeExpiration(LocalTime.MAX);
        Vote created = service.create(USER_ID, GOLDEN_ID);
        service.setVoteTimeExpiration(expiration);

        int newId = created.id();
        Vote newVote = new Vote(newId, user, GOLDEN, created.getVoteDate());
        assertMatchNoUserAndRestaurantMenu(newVote, created);
    }

    @Test
    void createExpired() {
        LocalTime expiration = service.getVoteTimeExpiration();
        service.setVoteTimeExpiration(LocalTime.MIN);
        assertThrows(ExpiredVoteTimeException.class, () -> service.create(USER_ID, GOLDEN_ID));
        service.setVoteTimeExpiration(expiration);
    }

    @Test
    void getByUserIdAndDate() {
        var actual = service.getByUserAndDate(USER_ID, LocalDate.of(2021, 8, 18));
        assertMatchNoUserAndRestaurantMenu(USER_VOTE_TO1, actual);
    }

    @Test
    void getByRestaurantIdAndDate() {
        var actual = service.getByDateAndRestaurantId(august19, GOLDEN_ID);
        assertMatch(List.of(USER_VOTE_TO2, ADMIN_VOTE_TO2), actual, VoteTestData::assertMatchNoUserAndRestaurantMenu);
    }

    @Test
    void getAllInDate() {
        var actual = service.getAllInDate(august19);
        List<VoteTo> expected = List.of(USER_VOTE_TO2, ADMIN_VOTE_TO2);
        assertMatch(expected, actual, VoteTestData::assertMatchNoUserAndRestaurantMenu);
    }
}
