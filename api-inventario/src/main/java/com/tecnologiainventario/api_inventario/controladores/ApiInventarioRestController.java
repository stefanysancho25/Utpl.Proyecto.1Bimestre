package com.tecnologiainventario.api_inventario.controladores;

import com.tecnologiainventario.api_inventario.dtos.InventaroDto;
import com.tecnologiainventario.api_inventario.entidades.Inventario;
import com.tecnologiainventario.api_inventario.servicios.EmailService;
import com.tecnologiainventario.api_inventario.servicios.InventarioService;

import io.swagger.v3.oas.annotations.Operation;

import java.util.Optional;
import java.util.stream.Collectors;
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

    @Operation(summary = "saludo")
    @GetMapping("/hola")
    public String holaMundo() {
        return "Hola Mundo desde el API de Inventario!";
    }

    @Operation(summary = "listar de productos")
    @GetMapping("/")
    public ResponseEntity<List<InventaroDto>> listarProductos() {
        List<Inventario> entidades = inventarioService.obtenerTodosLosInventarios();
        // Mapear entidades a DTOs
        List<InventaroDto> dtos = entidades.stream()
                .map(entidad -> new InventaroDto(
                        (long) entidad.getId(),
                        entidad.getNameUsuario(),
                        entidad.getApellido(),
                        entidad.getCedula(),
                        entidad.getTelefono(),
                        entidad.getEmail(),
                        entidad.getNombreProducto(),
                        entidad.getMarca(),
                        entidad.getCostoProducto(),
                        entidad.getDescripcion(),
                        entidad.getCantidad()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Operation(summary = "identificacion de correo")
    @PostMapping("/nuevo-enviar-identificacion-correo")
    public ResponseEntity<InventaroDto> agregarProducto(@RequestBody InventaroDto nuevoProductoDto) {
        // Mapear DTO a entidadnuevoProductoDto
        Inventario nuevaEntidad = new Inventario();
        // Asigna propiedades del DTO a la entidad
        nuevaEntidad.setNameUsuario(nuevoProductoDto.getNameUsuario());
        nuevaEntidad.setApellido(nuevoProductoDto.getApellido());
        nuevaEntidad.setCedula(nuevoProductoDto.getCedula());
        nuevaEntidad.setTelefono(nuevoProductoDto.getTelefono());
        nuevaEntidad.setEmail(nuevoProductoDto.getEmail());
        nuevaEntidad.setNombreProducto(nuevoProductoDto.getNombreProducto());
        nuevaEntidad.setMarca(nuevoProductoDto.getMarca());
        nuevaEntidad.setCostoProducto(nuevoProductoDto.getCostoProducto());
        nuevaEntidad.setDescripcion(nuevoProductoDto.getDescripcion());
        nuevaEntidad.setCantidad(nuevoProductoDto.getCantidad());

        // Guardar la entidad en la base de datos
        Inventario inventarioGuardado = inventarioService.guardarInventario(nuevaEntidad);

        // Mapear la entidad guardada de nuevo a un DTO para la respuesta
        InventaroDto respuestaDto = new InventaroDto(
                (long) inventarioGuardado.getId(),
                inventarioGuardado.getNameUsuario(),
                inventarioGuardado.getApellido(),
                inventarioGuardado.getCedula(),
                inventarioGuardado.getTelefono(),
                inventarioGuardado.getEmail(),
                inventarioGuardado.getNombreProducto(),
                inventarioGuardado.getMarca(),
                inventarioGuardado.getCostoProducto(),
                inventarioGuardado.getDescripcion(),
                inventarioGuardado.getCantidad());

        // Enviar correo de notificación
        String correoDestino = inventarioGuardado.getEmail();
        if (correoDestino != null && !correoDestino.isEmpty()) {
            emailService.enviarCorreo(correoDestino, "Nuevo Producto Agregado",
                    "Se ha agregado un nuevo producto: " + inventarioGuardado.getNombreProducto() + ". Marca: "
                            + inventarioGuardado.getMarca()
                            + ". Costo: $" + inventarioGuardado.getCostoProducto() + ". Descripción: "
                            + inventarioGuardado.getDescripcion() +
                            ". Cantidad: " + inventarioGuardado.getCantidad() + ". Registrado por: "
                            + inventarioGuardado.getNameUsuario() + " " + inventarioGuardado.getApellido() +
                            " (" + inventarioGuardado.getCedula() + ", " + inventarioGuardado.getTelefono() + ")");
        } else {
            System.out.println("No se proporcionó un correo para enviar la notificación.");
        }

        return new ResponseEntity<>(respuestaDto, HttpStatus.CREATED);
    }

    @Operation(summary = "eliminar producto")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Integer id) { // ID como Integer
        boolean eliminado = inventarioService.eliminarInventario(id);
        if (eliminado) {
            return new ResponseEntity<>("Producto eliminado con ID: " + id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Producto no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    // obtener todo los productos vercion 1

    @Operation(summary = "obtener producto")
    @GetMapping("/producto-v1/{id}")
    public ResponseEntity<InventaroDto> obtenerProductov1(@PathVariable Integer id) { // ID como Integer
        System.out.println("Buscando producto con ID: " + id);
        Optional<Inventario> inventarioOptional = inventarioService.buscarPorId(id);

        if (inventarioOptional.isPresent()) {
            Inventario entidad = inventarioOptional.get();
            InventaroDto dto = new InventaroDto(
                    (long) entidad.getId(),
                    entidad.getNameUsuario(),
                    entidad.getApellido(),
                    entidad.getCedula(),
                    entidad.getTelefono(),
                    entidad.getEmail(),
                    entidad.getNombreProducto(),
                    entidad.getMarca(),
                    entidad.getCostoProducto(),
                    entidad.getDescripcion(),
                    entidad.getCantidad());

            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // obtener todo los productos vercion 2

    @Operation(summary = "obtener producto")
    @GetMapping("/producto-v2/{id}")
    public ResponseEntity<InventaroDto> obtenerProductov2(@PathVariable Integer id) { // ID como Integer
        System.out.println("Buscando producto con ID: " + id);
        Optional<Inventario> inventarioOptional = inventarioService.buscarPorId(id);

        if (inventarioOptional.isPresent()) {
            Inventario entidad = inventarioOptional.get();
            InventaroDto dto = new InventaroDto(
                    (long) entidad.getId(),
                    entidad.getNameUsuario(),
                    entidad.getApellido(),
                    entidad.getCedula(),
                    entidad.getTelefono(),
                    entidad.getEmail(),
                    entidad.getNombreProducto(),
                    entidad.getMarca(),
                    entidad.getCostoProducto(),
                    entidad.getDescripcion(),
                    entidad.getCantidad());

            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Obtener un ordenes por correo
    @Operation(summary = "Obtener un ordenes por correo")
    @GetMapping("/busqueda/{email}")
    public ResponseEntity<List<InventaroDto>> getListaInventarioByCorreo(@PathVariable String email) { // Cambiado a
                                                                                                       // email
        List<Inventario> entidadesInventario = inventarioService.buscarPorEmail(email); // Usar buscarPorEmail

        if (entidadesInventario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<InventaroDto> dtos = entidadesInventario.stream()
                .map(entidad -> new InventaroDto(
                        (long) entidad.getId(),
                        entidad.getNameUsuario(),
                        entidad.getApellido(),
                        entidad.getCedula(),
                        entidad.getTelefono(),
                        entidad.getEmail(),
                        entidad.getNombreProducto(),
                        entidad.getMarca(),
                        entidad.getCostoProducto(),
                        entidad.getDescripcion(),
                        entidad.getCantidad()))

                .collect(Collectors.toList());

        System.out.println("Obteniendo inventarios por el correo: " + email);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

}
