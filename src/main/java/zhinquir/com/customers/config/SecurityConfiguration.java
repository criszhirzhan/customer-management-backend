package zhinquir.com.customers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Configuration indica que esta se inicialice apenas se levante el proyecto
 * @EnableWebSecurity busca un metodo bajo la condicion que devuelva
 * SecurityFilterChain y recibir un HttpSecurity
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final CustomAccessFilter customAccessFilter;

    public SecurityConfiguration(CustomAccessFilter customAccessFilter) {
        this.customAccessFilter = customAccessFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /**
         * Metodo que permite cofigurar las reglas de la API, a que partes puede acceder el usuario.
         * @param http
         * @return
         */

        http.csrf(AbstractHttpConfigurer::disable); // csrf: para que no puedan acceder desde dominio a nuestro sitio

        /*http.authorizeHttpRequests(request -> {
                    request.requestMatchers("/api/register","/api/auth/login").permitAll(); // solo estas request permite todo
                    request.requestMatchers("/api/*").authenticated(); // para las demas request necesita estar autenticado
                });*/


        http.addFilterBefore(customAccessFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
