package edu.alenkin.topjavagraduation.rest.controller.v1.vote;

import edu.alenkin.topjavagraduation.dto.VoteTo;
import edu.alenkin.topjavagraduation.service.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static edu.alenkin.topjavagraduation.rest.controller.v1.vote.AdminVoteController.REST_URL;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Tag(name = "Admin vote controller", description = "Getting statistics of votes for the administrator")
@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class AdminVoteController {

    static final String REST_URL = "/rest/v1/admin/votes";

    private final VoteService service;

    @Operation(summary = "Get all votes for restaurant in current day")
    @GetMapping("/restaurant")
    public List<VoteTo> getByRestaurantInDate(@RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> date,
                                              @RequestParam int restId) {
        LocalDate currentDate = date.orElse(LocalDate.now());

        log.info("Get votes for restaurant {} in {}", restId, currentDate);
        return service.getByDateAndRestaurantId(currentDate, restId);
    }

    @Operation(summary = "Get all votes for all restaurants in current date")
    @GetMapping("/date")
    public List<VoteTo> getAllInDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("Get all votes in {}", date);
        return service.getAllInDate(date);
    }
}

