package com.francodavyd.project.service;

import com.francodavyd.project.dto.EmpleadoDTO;
import com.francodavyd.project.model.Empleado;
import com.francodavyd.project.repository.IEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService{
    @Autowired
    private IEmpleadoRepository repository;

    @Override
    public void crearEmpleado(EmpleadoDTO empleadoDTO) {
        Empleado emp = new Empleado();
        emp.setNombre(empleadoDTO.getNombre());
        emp.setCargo(empleadoDTO.getCargo());
        emp.setEmail(empleadoDTO.getEmail());
        emp.setApellido(empleadoDTO.getApellido());
        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        emp.setFecha_ingreso(fecha);

        repository.save(emp);
    }

    @Override
    public List<Empleado> obtenerEmpleados() {
        return repository.findAll();
    }

    @Override
    public Empleado buscarEmpleado(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void eliminarEmpleado(Long id) {
     repository.deleteById(id);
    }

    @Override
    public void editarEmpleado(Empleado empleado) {
     repository.save(empleado);
    }


}
