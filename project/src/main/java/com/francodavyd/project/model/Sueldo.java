package com.francodavyd.project.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sueldo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;
    private String fecha_emision;
    @Column(name = "dias_trabajados")
    private int diasTrabajados;
    @Column(name = "horas_diarias")
    private int horasDiarias;
    @Column(name = "precio_hora")
    private double precioHora;
    @Column(name = "horas_extras")
    private int horasExtras;
    @Column(name = "precio_hora_extra")
    private double precioHoraExtra;
    private double refrigerio;
    @Column(name = "sueldo_total")
    private double sueldoTotal;

}
