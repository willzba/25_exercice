package com.tiendaMaquillajes.tiendaOnline.entities;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class userEntity {

    private UUID id;
    private String nameUser;
    private String emailUser;
    private String contraseña;
    private List<roleUser> roles;
    private List<productEntity> productos; // productos "propios"
    private List<buysEntity> compras;     // lista de compras
}
