package edu.alenkin.topjavagraduation.rest.controller.v1.vote;

import edu.alenkin.topjavagraduation.security.AuthorizedUser;
import edu.alenkin.topjavagraduation.service.VoteService;
import edu.alenkin.topjavagraduation.transferobject.VoteTo;
import edu.alenkin.topjavagraduation.util.VoteUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static edu.alenkin.topjavagraduation.rest.controller.v1.vote.UserVoteController.REST_URL;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserVoteController {

    static final String REST_URL = "/rest/v1/votes";

    private final VoteService service;

    @PostMapping
    public ResponseEntity<VoteTo> create(@RequestParam("restaurantId") int restaurantId,
                                         @AuthenticationPrincipal AuthorizedUser authorizedUser){
        VoteTo created = VoteUtil.asTo(service.create(authorizedUser.getId(),restaurantId));
        URI uriOfNewResource = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
    @PutMapping
    public VoteTo update(@RequestParam ("restaurantId") int restaurantId,
                          @AuthenticationPrincipal AuthorizedUser authorizedUser){
        return VoteUtil.asTo(service.create(authorizedUser.getId(),restaurantId));
    }

    @GetMapping
    public List<VoteTo> getOwnVotes(@AuthenticationPrincipal AuthorizedUser authorizedUser,
                                    @RequestParam(required = false) LocalDate date){
        if(date == null) {
            return service.getAllByUserId(authorizedUser.getId());
        } else return service.getByUserIdAndDate(authorizedUser.getId(), date);
    }

    @GetMapping("/rating-on-date")
    public Map<String, Integer> getRatingBy(@RequestParam LocalDate date){
        return service.getRatingInDateGroupByRestaurantName(date);
    }

    @GetMapping("/rating")
    public Map<String, Integer> getRating(){
        return service.getRatingsGroupByRestaurantName();
    }
}
