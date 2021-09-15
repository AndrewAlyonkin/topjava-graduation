package edu.alenkin.topjavagraduation.rest.controller.v1.restaurant;

import com.fasterxml.jackson.annotation.JsonView;
import edu.alenkin.topjavagraduation.model.Restaurant;
import edu.alenkin.topjavagraduation.modelview.View;
import edu.alenkin.topjavagraduation.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User restaurant controller", description = "User operations with restaurants")
@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantController extends AbstractRestaurantController {

    static final String REST_URL = "/rest/v1/restaurants";

    public UserRestaurantController(RestaurantService service) {
        super(service);
    }

    @Operation(summary = "Get restaurant with required ID")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }

    @Operation(summary = "Get all restaurants from storage")
    @JsonView(View.RestaurantWithoutMenu.class)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @Operation(summary = "Get all restaurants with its menu for today")
    @GetMapping(value = "/today", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllWithMenuForToday() {
        return super.getAllWithDishes(LocalDate.now());
    }

    @Operation(summary = "Get all restaurants with its menu for current date")
    @GetMapping(value = "/date", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllWithMenuForDate(@RequestParam @Nullable LocalDate date) {
        return super.getAllWithDishes(date == null ? LocalDate.now() : date);
    }
}

