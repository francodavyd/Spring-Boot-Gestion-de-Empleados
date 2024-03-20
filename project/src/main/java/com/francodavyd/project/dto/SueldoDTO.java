package com.francodavyd.project.dto;

import com.francodavyd.project.model.Empleado;
import lombok.Getter;

@Getter
public class SueldoDTO {
    private Empleado empleado;
    private int diasTrabajados;
    private int horasDiarias;
    private double precioHora;
    private int horasExtras;
    private double precioHoraExtra;
    private double refrigerio;
}
