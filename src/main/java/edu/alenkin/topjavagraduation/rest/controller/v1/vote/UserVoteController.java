package edu.alenkin.topjavagraduation.rest.controller.v1.vote;

import edu.alenkin.topjavagraduation.security.AuthorizedUser;
import edu.alenkin.topjavagraduation.service.VoteService;
import edu.alenkin.topjavagraduation.transferobject.VoteTo;
import edu.alenkin.topjavagraduation.util.VoteUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static edu.alenkin.topjavagraduation.rest.controller.v1.vote.UserVoteController.REST_URL;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Api(value = "User vote controller", tags = {"For user voting"})
@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class UserVoteController {

    static final String REST_URL = "/rest/v1/votes";

    private final VoteService service;

    @ApiOperation(value = "Take a vote for some restaurant with required ID", response = VoteTo.class)
    @PostMapping(value = "/{restId}")
    public ResponseEntity<VoteTo> create(@PathVariable int restId,
                                         @AuthenticationPrincipal @ApiIgnore AuthorizedUser authorizedUser) {
        int authUserId = authorizedUser.getId();
        log.info("User {} votes for {}", authUserId, restId);

        VoteTo created = VoteUtil.asTo(service.create(authUserId, restId));
        URI uriOfNewResource = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @ApiOperation(value = "Change your vote. You cant change vote after vote expiration time", response = VoteTo.class)
    @PutMapping(value = "/{restId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public VoteTo update(@PathVariable int restId,
                         @AuthenticationPrincipal @ApiIgnore AuthorizedUser authorizedUser) {
        int authUserId = authorizedUser.getId();
        log.info("User {} updates vote and votes for {}", authUserId, restId);
        return VoteUtil.asTo(service.create(authUserId, restId));
    }

    @ApiOperation(value = "Get all own votes in current date", response = Iterable.class)
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<VoteTo> getOwnVotes(@AuthenticationPrincipal @ApiIgnore AuthorizedUser authorizedUser,
                                    @RequestParam(required = false) LocalDate date) {
        int authUserId = authorizedUser.getId();
        log.info("Get all votes for user {} in {}", authUserId, date);
        if (date == null) {
            return service.getAllByUserId(authUserId);
        } else return service.getByUserIdAndDate(authUserId, date);
    }
}
