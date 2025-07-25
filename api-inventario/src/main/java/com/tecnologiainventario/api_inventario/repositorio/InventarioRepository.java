package com.tecnologiainventario.api_inventario.repositorio;

import com.tecnologiainventario.api_inventario.entidades.inventario;
import java.util.List;


@Repository

public interface InventarioRepository extends JpaRepository<inventario, Integer> {

      public List<inventario> findByemail(String email);
      public List<inventario> findBynameUsuario(String nameUsuario);
      public List<inventario> findByapellido(String apellido);
      public List<inventario> findBycedula(String cedula);
      public List<inventario> findBytelefono(String telefono);
      public List<inventario> findByciudad(String ciudad);

}
