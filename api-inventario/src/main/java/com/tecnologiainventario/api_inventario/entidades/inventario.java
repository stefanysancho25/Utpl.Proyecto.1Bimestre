package com.tecnologiainventario.api_inventario.entidades;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;


@Entity
@Table(name = "inventarioregistroclientes")
@AllArgsConstructor
@Getter @Setter @NoArgsConstructor

public class inventario  implements Serializable{

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


