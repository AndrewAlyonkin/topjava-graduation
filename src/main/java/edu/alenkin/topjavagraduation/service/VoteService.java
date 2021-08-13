package edu.alenkin.topjavagraduation.service;

import edu.alenkin.topjavagraduation.entity.Vote;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface VoteService {

    List<Vote> get();

    List<Vote> getAllByUserId(int userId);

    Vote getById(int id);

    Map<String, Boolean> delete(int id);

    Vote create(int restaurantId, int userId, LocalDateTime localDateTime);
}
