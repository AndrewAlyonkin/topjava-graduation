package edu.alenkin.topjavagraduation.model;

import org.springframework.util.Assert;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 * <p>
 * An interface for a persistent entity. Declares methods to check for the existence of an ID
 * and check if an entity is new.
 */

public interface HasId {
    Integer getId();

    void setId(Integer id);

    default boolean isNew() {
        return getId() == null;
    }

    default int id() {
        Assert.notNull(getId(), "Entity must has id");
        return getId();
    }
}
