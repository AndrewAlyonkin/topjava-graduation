package edu.alenkin.topjavagraduation.service;

import edu.alenkin.topjavagraduation.dto.VoteTo;
import edu.alenkin.topjavagraduation.model.Vote;
import edu.alenkin.topjavagraduation.repository.RestaurantRepository;
import edu.alenkin.topjavagraduation.repository.UserRepository;
import edu.alenkin.topjavagraduation.repository.VoteRepository;
import edu.alenkin.topjavagraduation.util.ValidationUtil;
import edu.alenkin.topjavagraduation.util.VoteUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    @Value("#{T(java.time.LocalTime).parse('${time.expiration}')}")
    private LocalTime VOTE_TIME_EXPIRATION;

    private final VoteRepository voteRepo;
    private final RestaurantRepository restRepo;
    private final UserRepository userRepo;

    // H2 database does not support LocalDateTime.MAX and LocalDateTime.MIN
    private final LocalDateTime maxLocalDateTime = LocalDateTime.of(2500, 1, 1, 23, 59);
    private final LocalDateTime minLocalDateTime = LocalDateTime.of(2020, 1, 1, 0, 0);

    @Override
    public Vote create(int userId, int restaurantId) {
        LocalDateTime now = LocalDateTime.now();
        List<Vote> votes = voteRepo.getAllForUserBetween(userId, now.with(LocalTime.MIN), now.with(LocalTime.MAX));
        Vote vote = votes.isEmpty()
                ? new Vote(userRepo.getById(userId), restRepo.getById(restaurantId), now)
                : updateExisted(votes.get(0), restaurantId, now);
        return voteRepo.save(vote);
    }

    @Override
    public List<VoteTo> getByUserIdAndDate(int userId, LocalDate date) {
        LocalDateTime currentDate = date.atStartOfDay();
        return VoteUtil.asTo(voteRepo.getAllForUserBetween(userId,
                currentDate.with(LocalTime.MIN), currentDate.with(LocalTime.MAX)));
    }

    @Override
    public List<VoteTo> getByRestaurantBetween(LocalDate startDate, LocalDate endDate, int restaurantId) {

        // H2 database does not supported LocalDateTime.MAX and LocalDateTime.MIN
        LocalDateTime startDateTime = (startDate == null)
                ? minLocalDateTime
                : startDate.atTime(LocalTime.MIN);

        LocalDateTime endDateTime = (endDate == null)
                ? maxLocalDateTime
                : endDate.atTime(LocalTime.MAX);

        return VoteUtil.asTo(voteRepo.getAllForRestaurantInBetween(startDateTime, endDateTime, restaurantId));
    }

    @Override
    public List<VoteTo> getAllByUserId(int userId) {
        return VoteUtil.asTo(voteRepo.getAllForUser(userId));
    }

    @Override
    public List<VoteTo> getByDateAndRestaurantId(LocalDate date, int restaurantId) {
        return VoteUtil.asTo(voteRepo.getAllForRestaurantInBetween(date.atStartOfDay(),
                date.atStartOfDay().with(LocalTime.MAX), restaurantId));
    }

    @Override
    public List<VoteTo> getByRestaurantId(int restaurantId) {
        return VoteUtil.asTo(voteRepo.getAllForRestaurant(restaurantId));
    }

    @Override
    public List<VoteTo> getAll() {
        return VoteUtil.asTo(voteRepo.findAll());
    }

    @Override
    public List<VoteTo> getAllInDate(LocalDate date) {
        LocalDateTime dateTime = date.atStartOfDay();
        return VoteUtil.asTo(voteRepo.getAllByDate(dateTime.with(LocalTime.MIN), dateTime.with(LocalTime.MAX)));
    }

    private Vote updateExisted(Vote vote, int restaurantId, LocalDateTime dateTime) {
        ValidationUtil.checkTimeExpiration(dateTime.toLocalTime(), VOTE_TIME_EXPIRATION);
        vote.setRestaurant(restRepo.getById(restaurantId));
        vote.setVoteDateTime(dateTime);
        return vote;
    }

    public void setVoteTimeExpiration(LocalTime VOTE_TIME_EXPIRATION) {
        this.VOTE_TIME_EXPIRATION = VOTE_TIME_EXPIRATION;
    }

    public LocalTime getVoteTimeExpiration() {
        return VOTE_TIME_EXPIRATION;
    }
}
