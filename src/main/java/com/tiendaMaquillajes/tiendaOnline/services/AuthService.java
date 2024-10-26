package com.tiendaMaquillajes.tiendaOnline.services;

import com.tiendaMaquillajes.tiendaOnline.entities.RolNombre;
import com.tiendaMaquillajes.tiendaOnline.entities.roleUser;
import com.tiendaMaquillajes.tiendaOnline.entities.userEntity;

import java.util.*;

public class AuthService {

    private Map<String, userEntity> usuariosRegistrados = new HashMap<>();

    public AuthService() {
        // Crear usuarios en memoria para simular autenticación
        roleUser rolUser = new roleUser(UUID.randomUUID(), RolNombre.USER);
        roleUser rolAdmin = new roleUser(UUID.randomUUID(), RolNombre.ADMIN);

        userEntity usuario = new userEntity(UUID.randomUUID(), "usuario", "usuario@example.com", List.of(rolUser), new ArrayList<>(), new ArrayList<>());
        userEntity admin = new userEntity(UUID.randomUUID(), "admin", "admin@example.com", List.of(rolAdmin), new ArrayList<>(), new ArrayList<>());

        // Guardar usuarios en memoria
        usuariosRegistrados.put("usuario", usuario);
        usuariosRegistrados.put("admin", admin);
    }

    public userEntity login(String nombreUsuario) {
        return usuariosRegistrados.get(nombreUsuario);
    }
}
