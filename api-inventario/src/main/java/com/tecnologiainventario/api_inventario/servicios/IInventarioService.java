package com.tecnologiainventario.api_inventario.servicios;

import java.util.List;

import com.tecnologiainventario.api_inventario.entidades.inventario;

public interface IInventarioService {

public List<inventario> BuscarPorCorreo(String correo);

}