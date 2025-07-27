package com.tecnologiainventario.api_inventario.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class InventaroDtov2 {
    private long id;
    private String nombreProducto; // NUEVO
    private String marca;
    private double costoProducto;
    private String descripcion;
    private int cantidad;
}
