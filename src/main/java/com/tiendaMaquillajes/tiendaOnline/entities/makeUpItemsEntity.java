package com.tiendaMaquillajes.tiendaOnline.entities;


import lombok.*;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class makeUpItemsEntity {
    private UUID id;
    private String nombreArticulo;
    private String piel;
    private String detalles;
    private String resistencia;
    private int price;
    private int stock;
}
