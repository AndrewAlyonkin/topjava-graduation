package edu.alenkin.topjavagraduation.rest.controller.v1.restaurant;

import edu.alenkin.topjavagraduation.entity.Restaurant;
import edu.alenkin.topjavagraduation.service.RestaurantService;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static edu.alenkin.topjavagraduation.rest.controller.v1.restaurant.UserRestaurantController.REST_URL;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantController extends AbstractRestaurantController {

    static final String REST_URL = "/rest/v1/restaurants";

    public UserRestaurantController(RestaurantService service) {
        super(service);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @GetMapping(value = "/today",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllWithMenuForToday() {
        return super.getAllWithDishes(LocalDate.now());
    }

    @GetMapping(value = "/date",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllWithMenuForDate(@RequestParam @Nullable LocalDate date) {
        return super.getAllWithDishes(date == null ? LocalDate.now() : date);
    }
}

