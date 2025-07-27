package com.tecnologiainventario.api_inventario.configuraciones;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod; // Importado para especificar el método HTTP
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                        // 1. Protege el endpoint de LISTAR productos (GET /api/inventario/)
                        // Este endpoint requerirá autenticación para acceder a la lista.
                        .requestMatchers(HttpMethod.GET, "/api/inventario/").authenticated()
                        // 2. El endpoint de ELIMINAR producto (DELETE /api/inventario/eliminar/{id})
                        // requiere el rol ADMIN.
                        .requestMatchers(HttpMethod.DELETE, "/api/inventario/eliminar/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults()) // Habilita autenticación Basic
                .csrf(csrf -> csrf.disable()); // Swagger necesita que CSRF esté deshabilitado

        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
