package com.tecnologiainventario.api_inventario.dtos;

public class InventaroDto {
    private long id;
    private String nombre;
    private String descripcion;
    private int cantidad; 
    private String correo; 

    // Constructor
    public InventaroDto(long id, String nombre, String descripcion, int cantidad, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.correo = correo;
    }

   // Constructor sin 'id' (útil para crear nuevos registros sin asignar ID manualmente)
    public InventaroDto(String nombre, String descripcion, int cantidad, String correo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.correo = correo; // Inicialización del nuevo campo
    }


    // Getters y setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public InventaroDto() {
    }

    public InventaroDto(String nombre, String descripcion, int cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
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
    // ¡Getters y setters para el nuevo campo 'correo'!
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
