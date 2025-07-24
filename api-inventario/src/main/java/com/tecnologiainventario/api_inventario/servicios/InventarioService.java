package com.tecnologiainventario.api_inventario.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnologiainventario.api_inventario.entidades.inventario;
import com.tecnologiainventario.api_inventario.repositorio.InventarioRepository;
@Service
public class InventarioService implements IInventarioService {
    @Autowired
    private InventarioRepository inventarioRepository;

    @Override
    public List<inventario> BuscarPorCorreo(String correo) {
    return inventarioRepository.findByemail(correo);
    }

}
