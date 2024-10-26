package com.tiendaMaquillajes.tiendaOnline.services;

import com.tiendaMaquillajes.tiendaOnline.entities.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioService {

    private Map<UUID, userEntity> usuarios = new HashMap<>();
    private Map<UUID, productEntity> productos = new HashMap<>();
    private Map<UUID, roleUser> roles = new HashMap<>();
    private Map<UUID, buysEntity> compras = new HashMap<>();
    private UUID compraIdCounter;
    private UUID productoIdCounter;

    public void agregarUsuario(userEntity usuario) {
        usuarios.put(usuario.getId(), usuario);
    }

    public void agregarProducto(UUID userId, productEntity producto) {
        userEntity usuario = usuarios.get(userId);
        if (usuario == null || !tieneRol(usuario, RolNombre.ADMIN)) {
            throw new IllegalArgumentException("Acceso denegado. Solo el ADMIN puede agregar productos.");
        }
        producto.setId(productoIdCounter);
        productos.put(producto.getId(), producto);
    }

    public void actualizarProducto(UUID userId, UUID productoId, productEntity productoActualizado) {
        // Verificar que el usuario existe y tiene el rol de ADMIN
        userEntity usuario = usuarios.get(userId);
        if (usuario == null || !tieneRol(usuario, RolNombre.ADMIN)) {
            throw new IllegalArgumentException("Acceso denegado. Solo el ADMIN puede actualizar productos.");
        }

        // Buscar el producto existente
        productEntity productoExistente = productos.get(productoId);
        if (productoExistente != null) {
            // Actualizar las propiedades del producto existente
            productoExistente.setNameProduct(productoActualizado.getNameProduct());
            productoExistente.setPrice(productoActualizado.getPrice());
            // También puedes actualizar otros atributos si es necesario
        } else {
            throw new IllegalArgumentException("Producto no encontrado.");
        }
    }


    public void eliminarProducto(UUID userId, UUID productoId) {
        userEntity usuario = usuarios.get(userId);
        if (usuario == null || !tieneRol(usuario, RolNombre.ADMIN)) {
            throw new IllegalArgumentException("Acceso denegado. Solo el ADMIN puede eliminar productos.");
        }
        productos.remove(productoId);
    }

    public List<productEntity> verProductosDisponibles() {
        return new ArrayList<>(productos.values());
    }

    public buysEntity registrarCompra(UUID userId, List<UUID> productoIds) {
        userEntity usuario = usuarios.get(userId);
        if (usuario == null || !tieneRol(usuario, RolNombre.USER)) {
            throw new IllegalArgumentException("Acceso denegado. Solo el usuario con rol USER puede realizar compras.");
        }

        List<productEntity> productosComprados = new ArrayList<>();
        for (UUID id : productoIds) {
            productEntity producto = productos.get(id);
            if (producto != null) {
                productosComprados.add(producto);
            }
        }

        buysEntity compra = new buysEntity(compraIdCounter, usuario, productosComprados);
        usuario.getCompras().add(compra);
        compras.put(compra.getId(), compra);

        return compra;
    }

    public Double obtenerTotalCompra(UUID compraId) {
        buysEntity compra = compras.get(compraId);
        return compra != null ? compra.calcularTotalCompra() : 0.0;
    }

    private boolean tieneRol(userEntity usuario, RolNombre rolNombre) {
        return usuario.getRoles().stream().anyMatch(rol -> rol.getRole() == rolNombre);
    }

}
