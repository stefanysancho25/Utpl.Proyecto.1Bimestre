package com.tecnologiainventario.api_inventario.entidades;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "inventario de tecnologia")
@AllArgsConstructor
@Getter @Setter @NoArgsConstructor

public class inventario  implements Serializable{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

private String firstName;

private String lastName;

private String identification;

private String email;

private String phone;

}


