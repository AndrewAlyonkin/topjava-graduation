package edu.alenkin.topjavagraduation.rest.controller.v1.dish;

import edu.alenkin.topjavagraduation.model.Dish;
import edu.alenkin.topjavagraduation.service.DishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static edu.alenkin.topjavagraduation.rest.controller.v1.dish.AdminDishController.REST_URL;
import static edu.alenkin.topjavagraduation.util.ValidationUtil.assureIdConsistent;
import static edu.alenkin.topjavagraduation.util.ValidationUtil.checkNew;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Tag(name = "Admin dish controller", description = "Admin operations with dish.")
@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class AdminDishController {
    public static final String REST_URL = "/rest/v1/admin/restaurants";

    private final DishService dishService;

    @Operation(summary = "Create new dish and save to storage")
    @PostMapping(value = "/{restId}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> save(@RequestBody @Valid Dish dish, @PathVariable("restId") int restId) {
        checkNew(dish);
        log.info("Creating a new dish {}", dish);
        Dish created = dishService.create(dish, restId);
        URI uriOfNewResource = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(REST_URL + "/" + "dishes/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Operation(summary = "Get from storage dish with required ID")
    @GetMapping(value = "{restId}/dishes/{dishId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Dish get(@PathVariable("restId") int restId, @PathVariable("dishId") int id) {
        log.info("Get dish with id {} for restaurant {}", id, restId);
        return dishService.get(id, restId);
    }

    @Operation(summary = "Get from storage all dishes for required restaurant by restaurant ID")
    @GetMapping(value = "/{restId}/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Dish> getAllForRestaurant(@PathVariable("restId") int restaurantId) {
        log.info("Get all dishes for restaurant {}", restaurantId);
        return dishService.getAllForRestaurant(restaurantId);
    }

    @Operation(summary = "Update existing dish")
    @PutMapping(value = "/{restId}/dishes/{dishId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Dish update(@RequestBody @Valid Dish dish, @PathVariable("restId") int restId, @PathVariable("dishId") int dishId) {
        assureIdConsistent(dish, dishId);
        log.info("Update dish with id {} for restaurant {}", dishId, restId);
        return dishService.update(dish, restId);
    }

    @Operation(summary = "Delete dish with required ID from storage")
    @DeleteMapping("{restId}/dishes/{dishId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("restId") int restId, @PathVariable("dishId") int dishId) {
        log.info("Delete dish with id {} for restaurant {}", dishId, restId);
        dishService.delete(dishId, restId);
    }
}
