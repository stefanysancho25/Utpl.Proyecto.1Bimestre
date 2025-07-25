package com.tecnologiainventario.api_inventario.controladores;

import com.tecnologiainventario.api_inventario.dtos.InventaroDto;
import com.tecnologiainventario.api_inventario.entidades.inventario;
import com.tecnologiainventario.api_inventario.servicios.InventarioService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/inventario")

public class ApiInventarioRestController {

@Autowired
    private InventarioService inventarioService;
    @GetMapping("/inventarios")
public String obtenerInventarios() {
    return " lista de inventario ";

}
    private final List<InventaroDto> inventario = new ArrayList<>();

    public ApiInventarioRestController() {
        inventario.add(new InventaroDto(1, "Cafini Parlante Bluetooth", "Parlante inalámbrico con Bluetooth", 10));
        inventario.add(new InventaroDto(2, "Cafini Auriculares", "Auriculares con micrófono", 15));
        inventario.add(new InventaroDto(3, "Ledimi Lámpara LED", "Lámpara LED de escritorio", 8));
        inventario.add(new InventaroDto(4, "Ledimi Tira LED", "Tira de luces LED multicolor", 12));
        inventario.add(new InventaroDto(5, "LDNIO Cargador USB", "Cargador USB múltiple", 20));
        inventario.add(new InventaroDto(6, "LDNIO Power Bank", "Batería portátil 10000mAh", 7));
    }

    @GetMapping("/hola")
    public String holaMundo() {
        return "Hola Mundo";
    }

    @GetMapping("/")
    public List<InventaroDto> listarProductos() {
        return inventario;
    }

    @PostMapping("/nuevo")
    public InventaroDto agregarProducto(@RequestBody InventaroDto nuevoProducto) {
        // Asigna un ID nuevo (puedes mejorar la lógica si lo deseas)
        long nuevoId = inventario.size() + 1;
        nuevoProducto.setId(nuevoId);
        inventario.add(nuevoProducto);
        return nuevoProducto;
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable long id) {
        boolean eliminado = inventario.removeIf(producto -> producto.getId() == id);
        return eliminado ? "Producto eliminado" : "Producto no encontrado";
    }

    @GetMapping("/producto{id}")
    public InventaroDto obtenerProducto(@PathVariable long id) {
        System.out.println("Buscando producto con ID: " + id);

        for (InventaroDto item : inventario) {
            if (item.getId() == id) {
                return item;

            }

        }

        return null;
    }

    // Obtener un ordenes por correo
    @GetMapping("/busqueda/{correo}")
    public List<inventario> getListaInventarioByCorreo(@PathVariable String correo) {
        var inventarios = inventarioService.BuscarPorCorreo(correo);
        if (inventarios.isEmpty()) {
            return null; // O lanzar una excepción si no se encuentra
        }
        System.out.println("Obteniendo inventarios por el correo: " + correo);
        // Retornar el primer inventario encontrado
        return inventarios;
    }

}
