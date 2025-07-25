package com.tecnologiainventario.api_inventario.repositorio;

import com.tecnologiainventario.api_inventario.entidades.Inventario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface InventarioRepository extends JpaRepository<Inventario, Integer> {

      public List<Inventario> findByemail(String email);

      public List<Inventario> findBynameUsuario(String nameUsuario);

      public List<Inventario> findByapellido(String apellido);

      public List<Inventario> findBycedula(String cedula);

      public List<Inventario> findBytelefono(String telefono);

      public List<Inventario> findByciudad(String ciudad);

}
