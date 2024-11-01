package com.tiendaMaquillajes.tiendaOnline.entities;

import lombok.*;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class makeUpEntity {
    private UUID id;
    private String nombreArticulo;
    private String marca;
    private String tipoArticulo;
    private double price;
    private int stock;
}
