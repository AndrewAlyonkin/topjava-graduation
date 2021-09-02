package edu.alenkin.topjavagraduation.rest.controller.v1.vote;

import edu.alenkin.topjavagraduation.entity.Restaurant;
import edu.alenkin.topjavagraduation.service.VoteService;
import edu.alenkin.topjavagraduation.transferobject.VoteTo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
public class AdminVoteController {

    static final String REST_URL = "/rest/v1/admin/votes";

    private final VoteService service;

    @GetMapping("/restaurant-by")
    public List<VoteTo> getByRestaurant(@RequestParam(required = false) LocalDate start,
                                        @RequestParam(required = false) LocalDate end,
                                        @RequestParam(required = false) LocalDate date,
                                        @RequestParam int restaurantId){
        if(start != null && end != null){
            return service.getByRestaurantBetween(start, end, restaurantId);
        } else return service.getByDateAndRestaurantId(date, restaurantId);
    }
    @GetMapping("/restaurant")
    public List<VoteTo> getByRestaurant(@RequestParam int restaurantId){
        return service.getByRestaurantId(restaurantId);
    }

    @GetMapping("/by-date")
    public List<VoteTo> getAllByDate(@RequestParam LocalDate date){
        return service.getAllInDate(date);
    }
    @GetMapping("/all")
    public List<VoteTo> getAll(){
        return service.getAll();
    }

    @GetMapping("/summary-by")
    public Map<Restaurant, List<VoteTo>> getSummary(@RequestParam LocalDate date){
        return service.getAllInDateGroupByRestaurant(date);
    }
    @GetMapping("/summary")
    public Map<Restaurant, List<VoteTo>> getAllVotes(){
        return service.getAllGroupByRestaurant();
    }
}

