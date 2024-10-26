package com.tiendaMaquillajes.tiendaOnline.controllers;

import com.tiendaMaquillajes.tiendaOnline.entities.RolNombre;
import com.tiendaMaquillajes.tiendaOnline.entities.productEntity;
import com.tiendaMaquillajes.tiendaOnline.entities.userEntity;
import com.tiendaMaquillajes.tiendaOnline.services.AuthService;
import com.tiendaMaquillajes.tiendaOnline.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthService authService;

    // Método para verificar autenticación
    private userEntity verificarAutenticacion(String username, String password, RolNombre rolRequerido) throws AccessDeniedException {
        // Intentar autenticar al usuario
        userEntity usuario = authService.login(username, password);

        // Verificar que el usuario esté autenticado y tenga el rol requerido
        if (usuario == null || usuario.getRoles().stream().noneMatch(r -> r.getRole() == rolRequerido)) {
            throw new AccessDeniedException("Acceso denegado. Permisos insuficientes.");
        }

        return usuario; // Retorna el usuario autenticado
    }

    @PostMapping("/agregar")
    public void agregarProducto(@RequestParam String username, @RequestParam String password, @RequestParam UUID userId, @RequestBody productEntity producto) throws AccessDeniedException {
        verificarAutenticacion(username, password, RolNombre.ADMIN);
        usuarioService.agregarProducto(userId, producto); // Asumiendo que el método en usuarioService acepta un UUID y un productEntity
    }


    @PutMapping("/actualizar/{userId}/{productoId}")
    public void actualizarProducto(
            @PathVariable UUID userId,  // Aquí se recibe el userId
            @RequestParam String username,
            @RequestParam String password,
            @PathVariable UUID productoId,
            @RequestBody productEntity producto) throws AccessDeniedException {

        // Verificar autenticación antes de proceder a la actualización
        verificarAutenticacion(username, password, RolNombre.ADMIN);

        // Llamar al servicio para actualizar el producto
        usuarioService.actualizarProducto(userId, productoId, producto);
    }

    @DeleteMapping("/eliminar/{productoId}")
    public void eliminarProducto(@RequestParam String username, @RequestParam String password, @PathVariable UUID productoId, @RequestParam UUID userId) throws AccessDeniedException {
        verificarAutenticacion(username, password, RolNombre.ADMIN);
        usuarioService.eliminarProducto(userId, productoId); // Asegúrate de que esto esté bien implementado
    }


    @GetMapping("/ver")
    public List<productEntity> verProductosDisponibles(@RequestParam String username, @RequestParam String password) throws AccessDeniedException {
        // Verificar que el usuario esté autenticado
        userEntity usuario = verificarAutenticacion(username, password, RolNombre.USER);// Puedes cambiar el rol si quieres

        return usuarioService.verProductosDisponibles();
    }
}

