package com.francodavyd.project.service;

import com.francodavyd.project.dto.SueldoDTO;
import com.francodavyd.project.model.Sueldo;

import java.util.List;

public interface ISueldoService {
    public Sueldo crearSueldo(SueldoDTO sueldoDTO);
    public List<Sueldo> obtenerSueldos();
    public Sueldo buscarSueldo(Long id);
}
