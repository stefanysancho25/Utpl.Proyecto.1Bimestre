package com.tecnologiainventario.api_inventario.dtos;

public class InventaroDto {
    private long id;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private String email;

    // Constructor
    public InventaroDto(long id, String nombre, String descripcion, int cantidad, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.email = email;
    }

    // Constructor sin 'id' (útil para crear nuevos registros sin asignar ID
    // manualmente)
    public InventaroDto(String nombre, String descripcion, int cantidad, String email) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.email = email; // Inicialización del nuevo campo
    }

    // Constructor vacío (necesario para Spring)
    public InventarioDto() {
    }

    // Constructor con nombre, descripcion, cantidad (si es necesario para casos
    // específicos)
    public InventarioDto(String nombre, String descripcion, int cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    // Getters y setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // ¡Getters y setters para el nuevo campo 'email'!
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
