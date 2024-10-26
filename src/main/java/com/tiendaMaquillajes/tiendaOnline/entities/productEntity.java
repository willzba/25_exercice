package com.tiendaMaquillajes.tiendaOnline.entities;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class productEntity {

    private UUID id;
    private String nameProduct;
    private String category;
    private String description;
    private double price;
    private int amount;
    private boolean active;

}
