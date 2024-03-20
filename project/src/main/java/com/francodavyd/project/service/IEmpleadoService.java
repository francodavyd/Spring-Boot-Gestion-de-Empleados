package com.francodavyd.project.service;

import com.francodavyd.project.dto.EmpleadoDTO;
import com.francodavyd.project.dto.SueldoEmpleadoDTO;
import com.francodavyd.project.model.Empleado;
import com.francodavyd.project.model.Sueldo;

import java.util.List;

public interface IEmpleadoService {
    public void crearEmpleado(EmpleadoDTO empleadoDTO);
    public List<Empleado> obtenerEmpleados();
    public Empleado buscarEmpleado(Long id);
    public void eliminarEmpleado(Long id);
    public void editarEmpleado(Empleado empleado);
}
