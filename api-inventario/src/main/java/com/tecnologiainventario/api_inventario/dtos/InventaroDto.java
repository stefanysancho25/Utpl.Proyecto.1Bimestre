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
    private String apellido; // NUEVO
    private String cedula; // NUEVO
    private String telefono;
    private String email;
    private String nombreProducto; // NUEVO
    private String marca;
    private double costoProducto;
    private String descripcion;
    private int cantidad;
}
