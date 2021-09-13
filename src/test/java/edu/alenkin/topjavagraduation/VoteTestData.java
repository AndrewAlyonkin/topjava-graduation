package edu.alenkin.topjavagraduation;

import edu.alenkin.topjavagraduation.dto.VoteTo;
import edu.alenkin.topjavagraduation.model.Vote;

import java.time.LocalDate;
import java.util.List;

import static edu.alenkin.topjavagraduation.RestaurantTestData.*;
import static edu.alenkin.topjavagraduation.UserTestData.admin;
import static edu.alenkin.topjavagraduation.UserTestData.user;
import static edu.alenkin.topjavagraduation.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class VoteTestData {
    public static final int USER_VOTE1_ID = START_SEQ + 29;
    public static final int USER_VOTE2_ID = START_SEQ + 30;
    public static final int USER_VOTE_NOW_ID = START_SEQ + 33;
    public static final int ADMIN_VOTE1_ID = START_SEQ + 31;
    public static final int ADMIN_VOTE2_ID = START_SEQ + 32;

    public final static LocalDate august18 = LocalDate.of(2021, 8, 18);
    public final static LocalDate august19 = LocalDate.of(2021, 8, 19);

    public static final VoteTo USER_VOTE_TO1 = new VoteTo(USER_VOTE1_ID, august18, BISON_ID, BISON.getName(), user.getName());
    public static final VoteTo USER_VOTE_TO2 = new VoteTo(USER_VOTE2_ID, august19, GOLDEN_ID, GOLDEN.getName(), user.getName());
    public static final VoteTo ADMIN_VOTE_TO1 = new VoteTo(ADMIN_VOTE1_ID, august18, PRESTIGE_ID, PRESTIGE.getName(), admin.getName());
    public static final VoteTo ADMIN_VOTE_TO2 = new VoteTo(ADMIN_VOTE2_ID, august19, GOLDEN_ID, GOLDEN.getName(), admin.getName());

    public static final VoteTo USER_VOTE_TO_NOW = new VoteTo(USER_VOTE_NOW_ID, LocalDate.now(), BISON_ID, BISON.getName(), user.getName());

    public static void assertMatchNoId(VoteTo actual, VoteTo expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);
    }

    public static <T> void assertMatchNoUserAndRestaurantMenu(T actual, T expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("user", "restaurant.menu").isEqualTo(expected);
    }

    public static VoteTo getNewVoteTo() {
        return new VoteTo(null, LocalDate.now(), BISON_ID, BISON.getName(), user.getName());
    }

    public static VoteTo getUpdated() {
        return new VoteTo(USER_VOTE1_ID, LocalDate.now(), BISON_ID, BISON.getName(), user.getName());
    }
}
