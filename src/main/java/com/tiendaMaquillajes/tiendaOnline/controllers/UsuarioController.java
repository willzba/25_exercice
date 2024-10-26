package com.tiendaMaquillajes.tiendaOnline.controllers;

import com.tiendaMaquillajes.tiendaOnline.entities.buysEntity;
import com.tiendaMaquillajes.tiendaOnline.entities.productEntity;
import com.tiendaMaquillajes.tiendaOnline.entities.userEntity;
import com.tiendaMaquillajes.tiendaOnline.services.AuthService;
import com.tiendaMaquillajes.tiendaOnline.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthService authService;

    // Endpoint para registrar una compra
    @PostMapping("/{userId}/compra")
    public buysEntity registrarCompra(@PathVariable UUID userId, @RequestBody List<UUID> productoIds) {
        return usuarioService.registrarCompra(userId, productoIds);
    }

    // Endpoint para obtener el total de una compra
    @GetMapping("/compra/{compraId}/total")
    public Double obtenerTotalCompra(@PathVariable UUID compraId) {
        return usuarioService.obtenerTotalCompra(compraId);
    }

    // Nuevo endpoint para ver todos los productos disponibles
    @GetMapping("/productos")
    public List<productEntity> verProductosDisponibles(@RequestParam String username, @RequestParam String password) {
        // Verificar que el usuario esté autenticado
        userEntity usuario = authService.login(username, password);

        if (usuario == null) {
            throw new IllegalArgumentException("Acceso denegado. Credenciales inválidas.");
        }

        // Retornar la lista de productos disponibles
        return usuarioService.verProductosDisponibles();
    }
}

