package edu.alenkin.topjavagraduation.util;

import edu.alenkin.topjavagraduation.entity.Vote;
import edu.alenkin.topjavagraduation.transferobject.VoteTo;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@UtilityClass
public class VoteUtil {
    public static VoteTo asTo(Vote vote) {
        return new VoteTo(vote);
    }

    public static List<VoteTo> asTo(List<Vote> votes) {
        return votes.stream().map(VoteTo::new).collect(Collectors.toList());
    }
}
