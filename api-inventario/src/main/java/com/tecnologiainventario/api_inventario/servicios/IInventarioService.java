package com.tecnologiainventario.api_inventario.servicios;

import java.util.List;
import java.util.Optional;
import com.tecnologiainventario.api_inventario.entidades.Inventario;

public interface IInventarioService {

    public List<Inventario> BuscarPorEmail(String email);

    Inventario guardarInventario(Inventario inventario); // Para guardar un nuevo inventario

    Optional<Inventario> buscarPorId(Integer id); // Para buscar por ID

    boolean eliminarInventario(Integer id); // Para eliminar por ID

    List<Inventario> obtenerTodosLosInventarios(); // Para listar todos
}