package com.tiendaMaquillajes.tiendaOnline.controllers;

import com.tiendaMaquillajes.tiendaOnline.entities.userEntity;
import com.tiendaMaquillajes.tiendaOnline.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        userEntity usuario = authService.login(username, password);
        if (usuario != null) {
            return "Autenticación exitosa ¡Bienvenido a la tienda de maquillajes!"; // Retorna mensaje de éxito
        } else {
            return "Error de autenticación"; // Retorna mensaje de error
        }
    }
}
