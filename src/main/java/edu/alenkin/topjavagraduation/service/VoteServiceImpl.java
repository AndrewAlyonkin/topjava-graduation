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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    @Value("#{T(java.time.LocalTime).parse('${time.expiration}')}")
    private LocalTime voteTimeExpiration;

    private final VoteRepository voteRepo;
    private final RestaurantRepository restRepo;
    private final UserRepository userRepo;

    @Override
    @Transactional
    public Vote create(int userId, int restaurantId) {
        LocalDate now = LocalDate.now();
        Optional<Vote> vote = voteRepo.getForUserInDate(userId, now);
        Vote newVote = vote.map(value -> updateExisted(value, restaurantId))
                .orElseGet(() -> new Vote(userRepo.getById(userId), restRepo.getById(restaurantId), now));
        return voteRepo.save(newVote);
    }

    @Override
    public VoteTo getByUserAndDate(int userId, LocalDate date) {
        return VoteUtil.asTo(voteRepo.getForUserInDate(userId, date).orElse(null));
    }

    @Override
    public List<VoteTo> getByDateAndRestaurantId(LocalDate date, int restaurantId) {
        return VoteUtil.asTo(voteRepo.getAllForRestaurantInDate(date, restaurantId));
    }

    @Override
    public List<VoteTo> getAllInDate(LocalDate date) {
        return VoteUtil.asTo(voteRepo.getAllInDate(date));
    }

    private Vote updateExisted(Vote vote, int restaurantId) {
        ValidationUtil.checkTimeExpiration(LocalTime.now(), voteTimeExpiration);
        vote.setRestaurant(restRepo.getById(restaurantId));
        return vote;
    }

    public void setVoteTimeExpiration(LocalTime VOTE_TIME_EXPIRATION) {
        this.voteTimeExpiration = VOTE_TIME_EXPIRATION;
    }

    public LocalTime getVoteTimeExpiration() {
        return voteTimeExpiration;
    }
}
