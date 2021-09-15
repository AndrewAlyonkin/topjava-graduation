package edu.alenkin.topjavagraduation.rest.controller.v1.restaurant;

import edu.alenkin.topjavagraduation.model.Restaurant;
import edu.alenkin.topjavagraduation.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static edu.alenkin.topjavagraduation.rest.controller.v1.restaurant.AdminRestaurantController.REST_URL;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Tag(name = "Admin restaurant controller", description = "Admin operations with restaurants")
@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantController extends AbstractRestaurantController {
    static final String REST_URL = "/rest/v1/admin/restaurants";

    public AdminRestaurantController(RestaurantService service) {
        super(service);
    }

    @Operation(summary = "Create new restaurant and save it to storage")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@RequestBody @Valid Restaurant restaurant) {
        var created = super.doCreate(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Operation(summary = "Update existing restaurant")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Restaurant update(@RequestBody @Valid Restaurant restaurant, @PathVariable int id) {
        return super.update(restaurant, id);
    }

    @Operation(summary = "Delete restaurant with required ID from storage")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
