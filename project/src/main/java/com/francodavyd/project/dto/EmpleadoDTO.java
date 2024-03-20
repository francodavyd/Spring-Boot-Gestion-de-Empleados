package com.francodavyd.project.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class EmpleadoDTO {
    private String nombre;
    private String apellido;
    private String cargo;
    @Email
    private String email;
}
