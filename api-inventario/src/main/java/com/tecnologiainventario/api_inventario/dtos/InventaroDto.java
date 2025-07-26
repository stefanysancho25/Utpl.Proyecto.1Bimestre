package com.tecnologiainventario.api_inventario.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class InventaroDto {
    private long id;
    private String nameUsuario;
    private String descripcion;
    private int cantidad;
    private String email;
}
