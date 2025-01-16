package ForoHub.foro;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()  // Deshabilita CSRF si no es necesario, útil para APIs RESTful.
                .authorizeRequests()
                .antMatchers("/topicos/**").authenticated()  // Las rutas /topicos/** requieren autenticación.
                .antMatchers("/login").permitAll()  // Permite el acceso a todos a la página de login.
                .and()
                .formLogin()  // Habilita la autenticación basada en formularios.
                .permitAll();  // Permite a todos acceder a la página de login.
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();  // Exponer AuthenticationManager como bean.
    }
}



