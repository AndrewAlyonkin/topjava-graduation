package edu.alenkin.topjavagraduation.rest.controller.v1.vote;

import edu.alenkin.topjavagraduation.security.AuthorizedUser;
import edu.alenkin.topjavagraduation.service.VoteService;
import edu.alenkin.topjavagraduation.transferobject.VoteTo;
import edu.alenkin.topjavagraduation.util.VoteUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

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

    @PostMapping(value = "/{restId}")
    public ResponseEntity<VoteTo> create(@PathVariable int restId,
                                         @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        VoteTo created = VoteUtil.asTo(service.create(authorizedUser.getId(), restId));
        URI uriOfNewResource = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{restId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public VoteTo update(@PathVariable int restId,
                         @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        return VoteUtil.asTo(service.create(authorizedUser.getId(), restId));
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<VoteTo> getOwnVotes(@AuthenticationPrincipal AuthorizedUser authorizedUser,
                                    @RequestParam(required = false) LocalDate date) {
        if (date == null) {
            return service.getAllByUserId(authorizedUser.getId());
        } else return service.getByUserIdAndDate(authorizedUser.getId(), date);
    }
}
