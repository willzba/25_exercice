package com.tiendaMaquillajes.tiendaOnline.entities;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class productEntity {

    UUID id;
    String nameProduct;
    String category;
    String description;
    double price;
    int amount;
    boolean active;

}
