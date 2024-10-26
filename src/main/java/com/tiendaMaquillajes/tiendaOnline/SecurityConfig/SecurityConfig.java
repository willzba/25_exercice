package com.tiendaMaquillajes.tiendaOnline.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //.csrf(AbstractHttpConfigurer::disable) // Desactivar CSRF
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/login").permitAll() // Permitir acceso sin autenticación
                        .requestMatchers("/usuarios/productos", "/productos/ver","usuarios/compra").hasRole("USER") // Requiere rol USER para usuarios
                        .requestMatchers("productos/agregar").hasRole("ADMIN") // Requiere rol ADMIN para productos
                        .anyRequest().authenticated() // Requiere autenticación para otras rutas
                );
        return http.build(); // Devuelve el SecurityFilterChain
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usar BCrypt para codificar contraseñas
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .inMemoryAuthentication()
                .withUser("usuario").password(passwordEncoder().encode("passwordUsuario")).roles("USER") // Rol USER
                .and()
                .withUser("admin").password(passwordEncoder().encode("passwordAdmin")).roles("ADMIN"); // Rol ADMIN

        return authenticationManagerBuilder.build();
    }

}
