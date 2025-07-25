package com.tecnologiainventario.api_inventario.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "inventarioregistroclientes")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class Inventario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private String nameUsuario;

    private String apellido;

    private String ciudad;

    private String cedula;

    private String telefono;

    private String email;

}
