package com.tiendaMaquillajes.tiendaOnline.entities;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class buysEntity {

    private UUID id;
    private userEntity usuario;
    private List<productEntity> productosComprados;


    public Double calcularTotalCompra() {
        return productosComprados.stream()
                .mapToDouble(productEntity::getPrice)
                .sum();
    }
}
