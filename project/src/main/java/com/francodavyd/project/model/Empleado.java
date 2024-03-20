package com.francodavyd.project.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String cargo;
    @Email
    private String email;
    private String fecha_ingreso;
    @ElementCollection
    @CollectionTable(name = "lista_sueldos")
    @OneToMany
    private List<Sueldo> listaSueldos;

}
