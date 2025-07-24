package com.tecnologiainventario.api_inventario.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecnologiainventario.api_inventario.entidades.inventario;


@Repository
public interface InventarioRepository extends JpaRepository<inventario, Integer> {
      public List<inventario> findByemail(String email);

}
