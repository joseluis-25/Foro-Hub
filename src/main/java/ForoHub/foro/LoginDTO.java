package ForoHub.foro;

import javax.validation.constraints.NotEmpty;

public class LoginDTO {

    @NotEmpty(message = "El nombre de usuario es obligatorio.")
    private String username;

    @NotEmpty(message = "La contraseña es obligatoria.")
    private String password;

    // Getters y Setters

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
}
