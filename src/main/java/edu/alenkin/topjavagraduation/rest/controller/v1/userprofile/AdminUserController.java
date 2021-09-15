package edu.alenkin.topjavagraduation.rest.controller.v1.userprofile;

import edu.alenkin.topjavagraduation.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static edu.alenkin.topjavagraduation.rest.controller.v1.userprofile.AdminUserController.REST_URL;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Tag(name = "Admin user controller", description = "Admin operations with users")
@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUserController extends AbstractUserController {

    static final String REST_URL = "/rest/v1/admin/users";

    @Operation(summary = "Create new user and save it to storage")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createWithLocation(@RequestBody @Valid User user) {
        var created = super.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Operation(summary = "Get user with required ID")
    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Operation(summary = "Get all users from storage")
    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return super.getAll();
    }

    @Operation(summary = "Get user with required email")
    @Override
    @GetMapping("/email")
    public User getByEmail(@RequestParam String email) {
        return super.getByEmail(email);
    }

    @Operation(summary = "Update existing user")
    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public User update(@RequestBody @Valid User user, @PathVariable int id) {
        return super.update(user, id);
    }

    @Operation(summary = "Delete user with required ID")
    @Override
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Operation(summary = "Enable or disable user access to application")
    @Override
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enable(@PathVariable int id, @RequestParam boolean enabled) {
        super.enable(id, enabled);
    }

}
