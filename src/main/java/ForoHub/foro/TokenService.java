package ForoHub.foro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    private static final String SECRET_KEY = "mySecretKey";  // Tu clave secreta para JWT

    // Método para generar un token JWT
    public String generarToken(String username) {
        // Usamos HS256 como algoritmo de firma
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        return JWT.create()
                .withSubject(username)  // Establecer el sujeto (usuario)
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400000)) // Establecer la fecha de expiración (1 día)
                .sign(algorithm);  // Firmar el token
    }

    // Método para obtener el usuario desde el token
    public String obtenerUsuarioDesdeToken(String token) {
        // Usamos el mismo algoritmo para decodificar
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        // Decodificamos el token y obtenemos el sujeto
        DecodedJWT decodedJWT = JWT.require(algorithm)
                .build()
                .verify(token);  // Verifica y decodifica el token

        return decodedJWT.getSubject();  // Retorna el sujeto (usuario)
    }

    // Método para validar si un token es válido
    public boolean validarToken(String token) {
        try {
            // Usamos el mismo algoritmo para validar
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

            // Intentamos verificar el token
            JWT.require(algorithm)
                    .build()
                    .verify(token);

            return true;  // El token es válido
        } catch (Exception e) {
            return false;  // El token no es válido
        }
    }
}


