package com.tecnologiainventario.api_inventario.controladores;

import com.tecnologiainventario.api_inventario.dtos.InventaroDto;
import com.tecnologiainventario.api_inventario.entidades.inventario;
import com.tecnologiainventario.api_inventario.servicios.EmailService;
import com.tecnologiainventario.api_inventario.servicios.InventarioService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/inventario")

public class ApiInventarioRestController {
    private final List<InventaroDto> inventario = new ArrayList<>();

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/inventarios")
    public String obtenerInventarios() {
        return " lista de inventario ";

    }

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
        // Enviar correo de notificación
        String correoDestino = nuevoProducto.getCorreo(); // ¡Obteniendo el correo del DTO!
        if (correoDestino != null && !correoDestino.isEmpty()) {
            emailService.enviarCorreo(correoDestino, "Nuevo Producto Agregado",
                    "Se ha agregado un nuevo producto: " + nuevoProducto.getNombre()
                            + ". Descripción: " + nuevoProducto.getDescripcion() + ". Cantidad: "
                            + nuevoProducto.getCantidad());
        } else {
            System.out.println("No se proporcionó un correo para enviar la notificación.");
        }

        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable long id) {
        boolean eliminado = inventarioService.eliminarInventario(id); // Asumiendo que el servicio tiene un método para
                                                                      // eliminar por ID
        if (eliminado) {
            return new ResponseEntity<>("Producto eliminado", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/producto/id}")
    public ResponseEntity<InventaroDto> obtenerProducto(@PathVariable long id) {
        System.out.println("Buscando producto con ID: " + id)

        for (InventaroDto item : inventario) {
            if (item.getId() == id) {
                return item;

            }

        }

        return null;
    }

    // Obtener un ordenes por correo
    @GetMapping("/busqueda/{correo}")
    public ResponseEntity<List<InventaroDto>> getListaInventarioByCorreo(@PathVariable String correo) {
        List<inventario> entidadesInventario = inventarioService.BuscarPorCorreo(correo); // Esto devuelve entidades

        if (entidadesInventario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 si no se encuentran
        }

        // Mapear la lista de entidades a una lista de DTOs
        List<InventaroDto> dtos = new ArrayList<>();
        for (inventario entidad : entidadesInventario) {
            dtos.add(new InventaroDto(
                    entidad.getId(),
                    entidad.getNombre(),
                    entidad.getDescripcion(),
                    entidad.getCantidad(),
                    entidad.getEmail()));
        }

        System.out.println("Obteniendo inventarios por el correo: " + correo);
        // Retornar el primer inventario encontrado
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

}
