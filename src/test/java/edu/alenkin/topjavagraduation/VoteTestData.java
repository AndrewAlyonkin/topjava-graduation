package edu.alenkin.topjavagraduation;

import edu.alenkin.topjavagraduation.dto.VoteTo;

import java.time.LocalDateTime;
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

    public final static LocalDateTime august18_10am = LocalDateTime.of(2021, 8, 18, 10, 0, 0);
    public final static LocalDateTime august19_10am = LocalDateTime.of(2021, 8, 19, 10, 0, 0);

    public static final VoteTo USER_VOTE_TO1 = new VoteTo(USER_VOTE1_ID, august18_10am, BISON_ID, BISON.getName(), user.getName());
    public static final VoteTo USER_VOTE_TO2 = new VoteTo(USER_VOTE2_ID, august19_10am, GOLDEN_ID, GOLDEN.getName(), user.getName());
    public static final VoteTo ADMIN_VOTE_TO1 = new VoteTo(ADMIN_VOTE1_ID, august18_10am, PRESTIGE_ID, PRESTIGE.getName(), admin.getName());
    public static final VoteTo ADMIN_VOTE_TO2 = new VoteTo(ADMIN_VOTE2_ID, august19_10am, GOLDEN_ID, GOLDEN.getName(), admin.getName());

    public static final VoteTo USER_VOTE_TO_NOW = new VoteTo(USER_VOTE_NOW_ID, LocalDateTime.now(), BISON_ID, BISON.getName(), user.getName());

    public static final List<VoteTo> allVotes = List.of(USER_VOTE_TO_NOW, USER_VOTE_TO2, ADMIN_VOTE_TO2, USER_VOTE_TO1, ADMIN_VOTE_TO1);

    public static void assertEqualsNoIdAndDateTime(VoteTo actual, VoteTo expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("dateTime", "id").isEqualTo(expected);
    }

    public static void assertEqualsNoDateTime(List<VoteTo> actual, List<VoteTo> expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("dateTime").isEqualTo(expected);
    }

    public static VoteTo getNewVoteTo() {
        return new VoteTo(null, LocalDateTime.now(), BISON_ID, BISON.getName(), user.getName());
    }

    public static VoteTo getUpdated() {
        return new VoteTo(USER_VOTE1_ID, LocalDateTime.now(), BISON_ID, BISON.getName(), user.getName());
    }

}
