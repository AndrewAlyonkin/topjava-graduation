package edu.alenkin.topjavagraduation.util;

import edu.alenkin.topjavagraduation.model.HasId;
import edu.alenkin.topjavagraduation.exception.ExpiredVoteTimeException;
import edu.alenkin.topjavagraduation.exception.IllegalRequestDataException;
import edu.alenkin.topjavagraduation.exception.NotFoundException;
import lombok.experimental.UtilityClass;

import java.time.LocalTime;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@UtilityClass
public class ValidationUtil {

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id = " + id);
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id = " + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String message) {
        if (!found) {
            throw new NotFoundException(message);
        }
    }

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean + " must be new (id = null)");
        }
    }

    public static void assureIdConsistent(HasId bean, int id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.getId() != id) {
            throw new IllegalRequestDataException(bean + " must be with id = " + id);
        }
    }

    public static void checkTimeExpiration(LocalTime time, LocalTime expiration) {
        if (time.isAfter(expiration)) {
            throw new ExpiredVoteTimeException("You can not change your vote");
        }
    }
}
