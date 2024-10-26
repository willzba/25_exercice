package com.tiendaMaquillajes.tiendaOnline.services;


import com.tiendaMaquillajes.tiendaOnline.entities.RolNombre;
import com.tiendaMaquillajes.tiendaOnline.entities.roleUser;
import com.tiendaMaquillajes.tiendaOnline.entities.userEntity;
import org.springframework.stereotype.Service;

import java.util.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AuthService {

    private Map<String, userEntity> usuariosRegistrados = new HashMap<>();
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService() {
        // Crear roles y usuarios con contraseñas cifradas
        roleUser rolUser = new roleUser(UUID.randomUUID(), RolNombre.USER);
        roleUser rolAdmin = new roleUser(UUID.randomUUID(), RolNombre.ADMIN);

        userEntity usuario = new userEntity(
                UUID.randomUUID(),
                "usuario",
                "usuario@example.com",
                passwordEncoder.encode("passwordUsuario"), // Contraseña cifrada
                List.of(rolUser),
                new ArrayList<>(),
                new ArrayList<>()
        );

        userEntity admin = new userEntity(
                UUID.randomUUID(),
                "admin",
                "admin@example.com",
                passwordEncoder.encode("passwordAdmin"),   // Contraseña cifrada
                List.of(rolAdmin),
                new ArrayList<>(),
                new ArrayList<>()
        );

        // Guardar usuarios en memoria
        usuariosRegistrados.put("usuario", usuario);
        usuariosRegistrados.put("admin", admin);
    }

    public userEntity login(String username, String password) {
        userEntity usuario = usuariosRegistrados.get(username);
        if (usuario != null && passwordEncoder.matches(password, usuario.getContraseña())) {
            return usuario; // Retornar el usuario autenticado
        }
        return null; // Si no se encuentra el usuario o la contraseña es incorrecta
    }
}
