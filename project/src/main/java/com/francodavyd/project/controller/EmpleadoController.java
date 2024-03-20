package com.francodavyd.project.controller;

import com.francodavyd.project.dto.EmpleadoDTO;
import com.francodavyd.project.model.Empleado;
import com.francodavyd.project.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/empleado")
public class EmpleadoController {
    @Autowired
    private IEmpleadoService service;

    @PostMapping("/crear")
    public ResponseEntity<?> crearEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
        try {
            service.crearEmpleado(empleadoDTO);
            return new ResponseEntity<>("Empleado registrado correctamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/obtenerLista")
    public ResponseEntity<?> obtenerEmpleados() {
        try {
            return new ResponseEntity<>(service.obtenerEmpleados(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarEmpleado/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.buscarEmpleado(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminarEmpleado/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable Long id) {
        try {
            service.eliminarEmpleado(id);
            return new ResponseEntity<>("Empleado eliminado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/editarEmpleado")
    public ResponseEntity<?> editarEmpleado(@RequestBody Empleado empleado) {
        try {
            Empleado empleadoBuscado = service.buscarEmpleado(empleado.getId());

            empleadoBuscado.setNombre(empleado.getNombre());
            empleadoBuscado.setApellido(empleado.getApellido());

            service.editarEmpleado(empleadoBuscado);
            return new ResponseEntity<>(empleadoBuscado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
