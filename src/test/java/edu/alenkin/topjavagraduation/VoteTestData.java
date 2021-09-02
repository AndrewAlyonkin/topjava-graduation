package edu.alenkin.topjavagraduation;

import edu.alenkin.topjavagraduation.entity.Vote;
import edu.alenkin.topjavagraduation.transferobject.VoteTo;

import java.time.LocalDateTime;
import java.util.List;

import static edu.alenkin.topjavagraduation.RestaurantTestData.*;
import static edu.alenkin.topjavagraduation.UserTestData.admin;
import static edu.alenkin.topjavagraduation.UserTestData.user;
import static edu.alenkin.topjavagraduation.entity.AbstractBaseEntity.START_SEQ;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class VoteTestData {
    public static final int USER_VOTE1_ID = START_SEQ + 29;
    public static final int USER_VOTE2_ID = START_SEQ + 30;
    public static final int ADMIN_VOTE1_ID = START_SEQ + 31;
    public static final int ADMIN_VOTE2_ID = START_SEQ + 32;

    public final static LocalDateTime august18_10am = LocalDateTime.of(2021, 8, 18, 10, 00, 00);
    public final static LocalDateTime august19_10am = LocalDateTime.of(2021, 8, 19, 10, 00, 00);

    public static final Vote USER_VOTE1 = new Vote(USER_VOTE1_ID, user, BISON, august18_10am);
    public static final Vote USER_VOTE2 = new Vote(USER_VOTE2_ID, user, GOLDEN, august19_10am);
    public static final Vote ADMIN_VOTE1 = new Vote(ADMIN_VOTE1_ID, admin, PRESTIGE, august18_10am);
    public static final Vote ADMIN_VOTE2 = new Vote(ADMIN_VOTE2_ID, admin, GOLDEN, august19_10am);

    public static final VoteTo USER_VOTE_TO1 = new VoteTo(USER_VOTE1_ID, august18_10am, BISON_ID, BISON.getName(), user.getName());
    public static final VoteTo USER_VOTE_TO2 = new VoteTo(USER_VOTE1_ID, august19_10am, GOLDEN_ID, GOLDEN.getName(), user.getName());
    public static final VoteTo ADMIN_VOTE_TO1 = new VoteTo(ADMIN_VOTE1_ID, august18_10am, PRESTIGE_ID, PRESTIGE.getName(), admin.getName());
    public static final VoteTo ADMIN_VOTE_TO2 = new VoteTo(ADMIN_VOTE1_ID, august19_10am, GOLDEN_ID, GOLDEN.getName(), admin.getName());

    public static final List<VoteTo> allVotes = List.of(ADMIN_VOTE_TO2, USER_VOTE_TO2,  ADMIN_VOTE_TO1, USER_VOTE_TO1);

}
