package edu.alenkin.topjavagraduation.rest.controller.v1.vote;

import edu.alenkin.topjavagraduation.service.VoteService;
import edu.alenkin.topjavagraduation.transferobject.VoteTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static edu.alenkin.topjavagraduation.rest.controller.v1.vote.AdminVoteController.REST_URL;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Api(value = "Admin vote controller", tags = {"Admin CRUD operations with votes"})
@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class AdminVoteController {

    static final String REST_URL = "/rest/v1/admin/votes";

    private final VoteService service;

    @ApiOperation(value = "Get all votes for required restaurant between current start and end dates", response = Iterable.class)
    @GetMapping("/restaurant/{restId}/between")
    //https://www.baeldung.com/spring-date-parameters#convert-date-parameters-on-request-level
    public List<VoteTo> getByRestaurantBetween(@RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                               @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                               @PathVariable int restId) {
        log.info("Get votes for restaurant {} between {} and {}", restId, startDate, endDate);
        return service.getByRestaurantBetween(startDate, endDate, restId);
    }

    @ApiOperation(value = "Get all votes for restaurant in current day", response = Iterable.class)
    @GetMapping("/restaurant/{restId}/date")
    public List<VoteTo> getByRestaurantInDate(@RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                              @PathVariable int restId) {
        LocalDate currentDate = (date == null) ? LocalDate.now() : date;

        log.info("Get votes for restaurant {} in {}", restId, currentDate);
        return service.getByDateAndRestaurantId(currentDate, restId);
    }

    @ApiOperation(value = "Get all votes for restaurant with required ID", response = Iterable.class)
    @GetMapping("/restaurant/{restId}")
    public List<VoteTo> getForRestaurant(@PathVariable int restId) {
        log.info("Get votes for restaurant with id {}", restId);
        return service.getByRestaurantId(restId);
    }

    @ApiOperation(value = "Get all votes for all restaurants in current date", response = Iterable.class)
    @GetMapping("/date")
    public List<VoteTo> getAllInDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("Get all votes in {}", date);
        return service.getAllInDate(date);
    }

    @ApiOperation(value = "Get all votes for all restaurants", response = Iterable.class)
    @GetMapping
    public List<VoteTo> getAll() {
        log.info("Get all votes");
        return service.getAll();
    }
}

