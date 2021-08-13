package edu.alenkin.topjavagraduation.transferobject;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serial;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Getter
@Setter
@ToString(includeFieldNames = false)
@AllArgsConstructor
@NoArgsConstructor
public class UserTo extends BaseTo {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(min = 5, max = 32, message = "length must be between 5 and 32 characters")
    private String password;

    public UserTo(Integer id, String name, String email, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }

}
