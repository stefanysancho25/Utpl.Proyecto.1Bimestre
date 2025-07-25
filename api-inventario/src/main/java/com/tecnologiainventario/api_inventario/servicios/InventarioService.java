package com.tecnologiainventario.api_inventario.servicios;

import com.tecnologiainventario.api_inventario.entidades.inventario;
import com.tecnologiainventario.api_inventario.repositorio.InventarioRepository;
import java.util.List;

import org.assertj.core.configuration.Services;
import org.springframework.beans.factory.annotation.Autowired;



Services
public class InventarioService implements IInventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public List<inventario> BuscarPorCorreo(String correo) {
    return inventarioRepository.findByemail(correo);
    }

}
