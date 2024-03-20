package com.francodavyd.project.service;

import com.francodavyd.project.dto.SueldoDTO;
import com.francodavyd.project.model.Sueldo;
import com.francodavyd.project.repository.ISueldoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class SueldoServiceImpl implements ISueldoService{
    @Autowired
    private ISueldoRepository repository;
    @Override
    public Sueldo crearSueldo(SueldoDTO sueldoDTO) {
        double sueldoParcial1 = sueldoDTO.getDiasTrabajados() * (sueldoDTO.getHorasDiarias() * sueldoDTO.getPrecioHora());
        double sueldoParcial2 = sueldoDTO.getHorasExtras() * sueldoDTO.getPrecioHoraExtra();
        double descuentos = sueldoDTO.getRefrigerio();
        double total = sueldoParcial1 + sueldoParcial2 - descuentos;
        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Sueldo sd = new Sueldo();
        sd.setEmpleado(sueldoDTO.getEmpleado());
        sd.setFecha_emision(fecha);
        sd.setDiasTrabajados(sueldoDTO.getDiasTrabajados());
        sd.setHorasDiarias(sueldoDTO.getHorasDiarias());
        sd.setPrecioHora(sueldoDTO.getPrecioHora());
        sd.setHorasExtras(sueldoDTO.getHorasExtras());
        sd.setPrecioHoraExtra(sueldoDTO.getPrecioHoraExtra());
        sd.setRefrigerio(sueldoDTO.getRefrigerio());
        sd.setSueldoTotal(total);
        repository.save(sd);
        return sd;
    }


    @Override
    public List<Sueldo> obtenerSueldos() {
        return repository.findAll();
    }

    @Override
    public Sueldo buscarSueldo(Long id) {
        return repository.findById(id).orElse(null);
    }



}
