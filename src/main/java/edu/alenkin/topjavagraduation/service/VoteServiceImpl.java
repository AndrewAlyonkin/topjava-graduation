package edu.alenkin.topjavagraduation.service;

import edu.alenkin.topjavagraduation.entity.Restaurant;
import edu.alenkin.topjavagraduation.entity.Vote;
import edu.alenkin.topjavagraduation.repository.RestaurantRepository;
import edu.alenkin.topjavagraduation.repository.UserRepository;
import edu.alenkin.topjavagraduation.repository.VoteRepository;
import edu.alenkin.topjavagraduation.transferobject.VoteTo;
import edu.alenkin.topjavagraduation.util.ValidationUtil;
import edu.alenkin.topjavagraduation.util.VoteUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepo;
    private final RestaurantRepository restRepo;
    private final UserRepository userRepo;

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
        return VoteUtil.asTo(voteRepo.getAllForRestaurantInDate(startDate.atTime(LocalTime.MIN),
                endDate.atTime(LocalTime.MAX), restaurantId));
    }

    @Override
    public List<VoteTo> getAllByUserId(int userId) {
        return VoteUtil.asTo(voteRepo.getAllForUser(userId));
    }

    @Override
    public List<VoteTo> getByDateAndRestaurantId(LocalDate date, int restaurantId) {
        return VoteUtil.asTo(voteRepo.getAllForRestaurantInDate(date.atStartOfDay(),
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
    public Map<Restaurant, List<VoteTo>> getAllInDateGroupByRestaurant(LocalDate date) {
        return getAndGroup(restaurant -> restaurant, restaurant -> getByDateAndRestaurantId(date, restaurant.id()));
    }

    @Override
    public Map<Restaurant, List<VoteTo>> getAllGroupByRestaurant() {
        return getAndGroup(restaurant -> restaurant, restaurant -> getByRestaurantId(restaurant.id()));
    }

    @Override
    public Map<String, Integer> getRatingInDateGroupByRestaurantName(LocalDate date) {
        return getAndGroup(Restaurant::getName, restaurant -> getByDateAndRestaurantId(date, restaurant.id()).size());
    }

    @Override
    public Map<String, Integer> getRatingsGroupByRestaurantName() {
        return getAndGroup(Restaurant::getName, restaurant -> getByRestaurantId(restaurant.id()).size());
    }

    private <K, V> Map<K, V> getAndGroup(Function<Restaurant, K> keyFunc, Function<Restaurant, V> valueFunc) {
        Map<K, V> voteResult = new ConcurrentHashMap<>();
        restRepo
                .findAll()
                .forEach(restaurant -> voteResult.put(keyFunc.apply(restaurant), valueFunc.apply(restaurant)));
        return voteResult;
    }

    @Override
    public List<VoteTo> getAllInDate(LocalDate date) {
        LocalDateTime dateTime = date.atStartOfDay();
        return VoteUtil.asTo(voteRepo.getAllByDate(dateTime.with(LocalTime.MIN), dateTime.with(LocalTime.MAX)));
    }

    private Vote updateExisted(Vote vote, int restaurantId, LocalDateTime dateTime) {
        ValidationUtil.checkTimeExpiration(dateTime.toLocalTime());
        vote.setRestaurant(restRepo.getById(restaurantId));
        vote.setVoteDateTime(dateTime);
        return vote;
    }
}
