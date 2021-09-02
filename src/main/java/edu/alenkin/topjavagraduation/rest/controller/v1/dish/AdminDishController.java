package edu.alenkin.topjavagraduation.rest.controller.v1.dish;

import edu.alenkin.topjavagraduation.entity.Dish;
import edu.alenkin.topjavagraduation.service.DishService;
import lombok.AllArgsConstructor;
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
@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AdminDishController {
    public static final String REST_URL = "/rest/v1/admin/restaurants";

    private DishService dishService;

    @PostMapping(value = "/{restId}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> save(@RequestBody @Valid Dish dish, @PathVariable("restId") int restId) {
        checkNew(dish);
        Dish created = dishService.create(dish, restId);
        URI uriOfNewResource = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(REST_URL + "/" + "dishes/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value = "{restId}/dishes/{dishId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Dish get(@PathVariable("restId") int restId, @PathVariable("dishId") int id) {
        return dishService.get(id, restId);
    }

    @GetMapping(value = "/{restId}/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Dish> getAll(@PathVariable("restId") int restaurantId) {
        return dishService.getAllForRestaurant(restaurantId);
    }

    @PutMapping(value = "/{restId}/dishes/{dishId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Dish update(@RequestBody @Valid Dish dish, @PathVariable("restId") int restId, @PathVariable("dishId") int dishId) {
        assureIdConsistent(dish, dishId);
        return dishService.update(dish, restId);
    }

    @DeleteMapping("{restId}/dishes/{dishId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("restId") int restId, @PathVariable("dishId") int dishId) {
        dishService.delete(dishId, restId);
    }
}
