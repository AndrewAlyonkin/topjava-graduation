package edu.alenkin.topjavagraduation.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.alenkin.topjavagraduation.modelview.View;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 * <p>
 * The base class, which represents an entity with an name. Contains validation for name field.
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractNamedEntity extends AbstractBaseEntity {

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "name", nullable = false)
    @JsonView(View.RestaurantWithoutMenu.class)
    protected String name;

    protected AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + '(' + name + ')';
    }
}
