package com.tecnologiainventario.api_inventario.controladores;

import com.tecnologiainventario.api_inventario.dtos.InventaroDto;
import com.tecnologiainventario.api_inventario.entidades.Inventario;
import com.tecnologiainventario.api_inventario.servicios.EmailService;
import com.tecnologiainventario.api_inventario.servicios.InventarioService;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventario")

public class ApiInventarioRestController {

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private EmailService emailService;

    public ApiInventarioRestController() {

    }

    @GetMapping("/hola")
    public String holaMundo() {
        return "Hola Mundo desde el API de Inventario!";
    }

    @GetMapping("/")
    publicResponseEntity<List<InventarioDto>> listarProductos() {
        List<Inventario> entidades = inventarioService.obtenerTodosLosInventarios();
        // Mapear entidades a DTOs
        List<InventarioDto> dtos = entidades.stream()
                .map(entidad -> new InventarioDto(
                        (long) entidad.getId(),
                        entidad.getNameUsuario(),
                        entidad.getDescripcion(),
                        entidad.getCantidad(),
                        entidad.getEmail()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<InventarioDto> agregarProducto(@RequestBody InventarioDto nuevoProductoDto) {
        // Mapear DTO a entidad
        Inventario nuevaEntidad = new Inventario();
        // Asigna propiedades del DTO a la entidad
        nuevaEntidad.setNameUsuario(nuevoProductoDto.getNombre());
        nuevaEntidad.setDescripcion(nuevoProductoDto.getDescripcion());
        nuevaEntidad.setCantidad(nuevoProductoDto.getCantidad());
        nuevaEntidad.setEmail(nuevoProductoDto.getEmail());

        // Guardar la entidad en la base de datos
        Inventario inventarioGuardado = inventarioService.guardarInventario(nuevaEntidad);

        // Mapear la entidad guardada de nuevo a un DTO para la respuesta
        InventarioDto respuestaDto = new InventarioDto(
                (long) inventarioGuardado.getId(),
                inventarioGuardado.getNameUsuario(),
                inventarioGuardado.getDescripcion(),
                inventarioGuardado.getCantidad(),
                inventarioGuardado.getEmail());

        // Enviar correo de notificación
        String correoDestino = inventarioGuardado.getEmail();
        if (correoDestino != null && !correoDestino.isEmpty()) {
            emailService.enviarCorreo(correoDestino, "Nuevo Producto Agregado",
                    "Se ha agregado un nuevo producto: " + inventarioGuardado.getNameUsuario()
                            + ". Descripción: " + inventarioGuardado.getDescripcion() + ". Cantidad: "
                            + inventarioGuardado.getCantidad());
        } else {
            System.out.println("No se proporcionó un correo para enviar la notificación.");
        }

        return new ResponseEntity<>(respuestaDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Integer id) { // ID como Integer
        boolean eliminado = inventarioService.eliminarInventario(id);
        if (eliminado) {
            return new ResponseEntity<>("Producto eliminado con ID: " + id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Producto no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/producto/id}")
    public ResponseEntity<InventarioDto> obtenerProducto(@PathVariable Integer id) { // ID como Integer
        System.out.println("Buscando producto con ID: " + id);
        Optional<Inventario> inventarioOptional = inventarioService.buscarPorId(id);

        if (inventarioOptional.isPresent()) {
            Inventario entidad = inventarioOptional.get();
            InventarioDto dto = new InventarioDto(
                    (long) entidad.getId(),
                    entidad.getNameUsuario(),
                    entidad.getDescripcion(),
                    entidad.getCantidad(),
                    entidad.getEmail());
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Obtener un ordenes por correo
    @GetMapping("/busqueda/{email}")
    public ResponseEntity<List<InventarioDto>> getListaInventarioByCorreo(@PathVariable String email) { // Cambiado a
                                                                                                        // email
        List<Inventario> entidadesInventario = inventarioService.buscarPorEmail(email); // Usar buscarPorEmail

        if (entidadesInventario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<InventarioDto> dtos = entidadesInventario.stream()
                .map(entidad -> new InventarioDto(
                        (long) entidad.getId(),
                        entidad.getNameUsuario(),
                        entidad.getDescripcion(),
                        entidad.getCantidad(),
                        entidad.getEmail()))
                .collect(Collectors.toList());

        System.out.println("Obteniendo inventarios por el correo: " + email);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

}
