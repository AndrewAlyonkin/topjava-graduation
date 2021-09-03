package edu.alenkin.topjavagraduation.rest.controller.v1.vote;

import edu.alenkin.topjavagraduation.entity.Restaurant;
import edu.alenkin.topjavagraduation.service.VoteService;
import edu.alenkin.topjavagraduation.transferobject.VoteTo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static edu.alenkin.topjavagraduation.rest.controller.v1.vote.AdminVoteController.REST_URL;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class AdminVoteController {

    static final String REST_URL = "/rest/v1/admin/votes";

    private final VoteService service;

    @GetMapping("/restaurant/{restId}/between")
    //https://www.baeldung.com/spring-date-parameters#convert-date-parameters-on-request-level
    public List<VoteTo> getByRestaurantBetween(@RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                               @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                               @PathVariable int restId) {
        log.info("Get votes for restaurant {} between {} and {}", restId, startDate, endDate);
        return service.getByRestaurantBetween(startDate, endDate, restId);
    }

    @GetMapping("/restaurant/{restId}/date")
    public List<VoteTo> getByRestaurantInDate(@RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                              @PathVariable int restId) {
        date = date == null ? LocalDate.now() : date;

        log.info("Get votes for restaurant {} in {}", restId, date);
        return service.getByDateAndRestaurantId(date, restId);
    }

    @GetMapping("/restaurant/{restId}")
    public List<VoteTo> getForRestaurant(@PathVariable int restId) {
        log.info("Get votes for restaurant with id {}", restId);
        return service.getByRestaurantId(restId);
    }

    @GetMapping("/date")
    public List<VoteTo> getAllInDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("Get all votes in {}", date);
        return service.getAllInDate(date);
    }

    @GetMapping
    public List<VoteTo> getAll() {
        log.info("Get all votes");
        return service.getAll();
    }
}

