package com.francodavyd.project.controller;

import com.francodavyd.project.dto.SueldoDTO;
import com.francodavyd.project.dto.SueldoEmpleadoDTO;
import com.francodavyd.project.jasper.Reporte;
import com.francodavyd.project.model.Empleado;
import com.francodavyd.project.model.Sueldo;
import com.francodavyd.project.service.IEmailService;
import com.francodavyd.project.service.IEmpleadoService;
import com.francodavyd.project.service.ISueldoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/sueldo")

public class SueldoController {
    @Autowired
    private ISueldoService service;
    @Autowired
    private IEmpleadoService empleadoService;
    @Autowired
    private IEmailService emailService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearSueldo(@RequestBody SueldoDTO sueldoDTO){
        try {
            Empleado empleado = empleadoService.buscarEmpleado(sueldoDTO.getEmpleado().getId());
            if (empleado != null){
                if (comprobarFecha(empleado)){
                    agregarSueldo(service.crearSueldo(sueldoDTO), empleado);
                } else {
                    throw new RuntimeException("El sueldo del mes actual ya ha sido calculado, regrese el próximo pago");
                }

            } else {
                throw new RuntimeException("El empleado con id " + sueldoDTO.getEmpleado().getId() + " no ha sido encontrado");
            }
            return new ResponseEntity<>("Sueldo registrado correctamente", HttpStatus.CREATED);
        } catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    private void agregarSueldo(Sueldo sueldo, Empleado empleado) {
        List<Sueldo> list = empleado.getListaSueldos();
        list.add(sueldo);
        empleado.setListaSueldos(list);
        empleadoService.editarEmpleado(empleado);
    }
    private boolean comprobarFecha(Empleado emp){
        LocalDate fechaActual = LocalDate.now();
        String mesActual = fechaActual.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
        int anioActual = fechaActual.getYear();

       if (emp.getListaSueldos() == null || emp.getListaSueldos().isEmpty()){
           return true;
       } else {
           Sueldo ultimoSueldo = emp.getListaSueldos().get(emp.getListaSueldos().size() - 1);
           LocalDate fechaSueldo = LocalDate.parse(ultimoSueldo.getFecha_emision(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
           String mesSueldo = fechaSueldo.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
           int anioSueldo = fechaSueldo.getYear();

           if (mesActual.equals(mesSueldo) && anioActual == anioSueldo){
               return false;
           } else {
               return true;
           }
       }

    }

    @GetMapping("/enviarReporte")
    public ResponseEntity<?> enviarReporte(@RequestBody SueldoEmpleadoDTO dto){
        try {
            Empleado emp = empleadoService.buscarEmpleado(dto.getIdEmpleado());
            Sueldo sd = service.buscarSueldo(dto.getIdSueldo());
            if (emp != null && sd != null){
                String asunto = "Bono de sueldo. Periodo " + sd.getFecha_emision();
                String mensaje = " Estimado empleado, nos comunicamos para dejar adjunto su bono de sueldo";
                File file =   Reporte.generarReporte(sd);
                emailService.enviar(emp.getEmail(), asunto, mensaje, file);

            } else {
                throw new RuntimeException("Los datos ingresados son invalidos");
            }
            return new ResponseEntity<>("Reporte generado y enviado correctamente", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/obtenerLista")
    public ResponseEntity<?> obtenerLista(){
        try {
            return new ResponseEntity<>(service.obtenerSueldos(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
   }
   @GetMapping("/obtenerSueldo/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id){
        try {
            return new ResponseEntity<>(service.buscarSueldo(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
   }

}
