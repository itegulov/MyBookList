package ru.mybooklist.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import ru.mybooklist.validation.EmailIsUnique;
import ru.mybooklist.validation.PasswordMatches;
import ru.mybooklist.validation.UsernameIsUnique;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Daniyar Itegulov
 */
@PasswordMatches
public class UserDTO {

    @NotNull
    @Pattern(message = "{Pattern.user.name}", regexp="^[a-zA-Z0-9]+$")
    @Length(message = "{Length.user.name}", min = 3, max=20)
    @UsernameIsUnique
    private String username;

    @NotNull
    @Length(message = "{Length.user.password}", min = 6, max=20)
    private String password;

    @NotNull
    private String passwordConfirm;

    @NotNull
    @Email(message = "{Email.user.email}")
    @Pattern(regexp = ".+@.+\\..+", message = "{Email.user.email}")
    @EmailIsUnique
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
