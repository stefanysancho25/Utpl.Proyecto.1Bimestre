package com.tecnologiainventario.api_inventario.servicios;

import com.tecnologiainventario.api_inventario.entidades.Inventario;
import com.tecnologiainventario.api_inventario.repositorio.InventarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventarioService implements IInventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Override
    public List<Inventario> buscarPorEmail(String email) {
        return inventarioRepository.findByEmail(email);
    }

    @Override
    public Inventario guardarInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    @Override
    public Optional<Inventario> buscarPorId(Integer id) {
        return inventarioRepository.findById(id);
    }

    @Override
    public boolean eliminarInventario(Integer id) {
        if (inventarioRepository.existsById(id)) {
            inventarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Inventario> obtenerTodosLosInventarios() {
        return inventarioRepository.findAll();

    }

    @Override
    public List<Inventario> BuscarPorEmail(String email) {
        // Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'BuscarPorEmail'");
    }
}