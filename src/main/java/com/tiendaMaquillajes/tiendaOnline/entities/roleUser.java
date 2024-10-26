package com.tiendaMaquillajes.tiendaOnline.entities;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class roleUser {

    UUID id;
    RolNombre role;

}


