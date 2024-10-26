package com.tiendaMaquillajes.tiendaOnline.services;

import com.tiendaMaquillajes.tiendaOnline.entities.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UsuarioService {

    private Map<UUID, userEntity> usuarios = new HashMap<>();
    private Map<UUID, productEntity> productos = new HashMap<>();
    private Map<UUID, roleUser> roles = new HashMap<>();
    private Map<UUID, buysEntity> compras = new HashMap<>();
    private Long compraIdCounter = 1L;

    public void agregarUsuario(userEntity usuario) {
        usuarios.put(usuario.getId(), usuario);
    }

    public void agregarProducto(UUID userId, productEntity producto) {
        userEntity usuario = usuarios.get(userId);
        if (usuario == null || !tieneRol(usuario, RolNombre.ADMIN)) {
            throw new IllegalArgumentException("Acceso denegado. Solo el ADMIN puede agregar productos.");
        }
        producto.setId(productoIdCounter++);
        productos.put(producto.getId(), producto);
    }

    public void actualizarProducto(Long userId, Long productoId, Producto productoActualizado) {
        Usuario usuario = usuarios.get(userId);
        if (usuario == null || !tieneRol(usuario, RolNombre.ADMIN)) {
            throw new IllegalArgumentException("Acceso denegado. Solo el ADMIN puede actualizar productos.");
        }
        Producto productoExistente = productos.get(productoId);
        if (productoExistente != null) {
            productoExistente.setNombre(productoActualizado.getNombre());
            productoExistente.setPrecio(productoActualizado.getPrecio());
        }
    }

    public void eliminarProducto(Long userId, Long productoId) {
        Usuario usuario = usuarios.get(userId);
        if (usuario == null || !tieneRol(usuario, RolNombre.ADMIN)) {
            throw new IllegalArgumentException("Acceso denegado. Solo el ADMIN puede eliminar productos.");
        }
        productos.remove(productoId);
    }

    public List<Producto> verProductosDisponibles() {
        return new ArrayList<>(productos.values());
    }

    public Compra registrarCompra(Long userId, List<Long> productoIds) {
        Usuario usuario = usuarios.get(userId);
        if (usuario == null || !tieneRol(usuario, RolNombre.USER)) {
            throw new IllegalArgumentException("Acceso denegado. Solo el usuario con rol USER puede realizar compras.");
        }

        List<Producto> productosComprados = new ArrayList<>();
        for (Long id : productoIds) {
            Producto producto = productos.get(id);
            if (producto != null) {
                productosComprados.add(producto);
            }
        }

        Compra compra = new Compra(compraIdCounter++, usuario, productosComprados);
        usuario.getCompras().add(compra);
        compras.put(compra.getId(), compra);

        return compra;
    }

    private boolean tieneRol(Usuario usuario, RolNombre rolNombre) {
        return usuario.getRoles().stream().anyMatch(rol -> rol.getNombre() == rolNombre);
    }
}
