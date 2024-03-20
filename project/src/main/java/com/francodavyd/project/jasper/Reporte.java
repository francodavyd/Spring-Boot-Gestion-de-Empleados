package com.francodavyd.project.jasper;

import com.francodavyd.project.model.Sueldo;
import net.sf.jasperreports.engine.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Reporte {
    public static File generarReporte(Sueldo sueldo) {

        Random rand = new Random();
        int numero = rand.nextInt(10000, 100000);

        String destino = "src" + File.separator +
                "main" + File.separator +
                "resources" + File.separator +
                "static" + File.separator +
                "reports" + File.separator +
                sueldo.getEmpleado().getNombre() +
                "_" + sueldo.getEmpleado().getApellido() +
                numero +
                ".pdf";

        String rutaJasper = "src" + File.separator +
                "main" + File.separator +
                "resources" + File.separator +
                "templates" + File.separator +
                "reports" + File.separator +
                "report.jrxml";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("nombre_empleado", sueldo.getEmpleado().getNombre() + " " + sueldo.getEmpleado().getApellido());
        parameters.put("cargo", sueldo.getEmpleado().getCargo());
        parameters.put("ingreso", sueldo.getEmpleado().getFecha_ingreso());
        parameters.put("emision", sueldo.getFecha_emision());
        parameters.put("dias_trabajados", sueldo.getDiasTrabajados());
        parameters.put("horas_diarias", sueldo.getHorasDiarias());
        parameters.put("valor_hora", sueldo.getPrecioHora());
        parameters.put("horas_extra", sueldo.getHorasExtras());
        parameters.put("valor_extra", sueldo.getPrecioHoraExtra());
        parameters.put("refrigerio", sueldo.getRefrigerio());
        parameters.put("total", sueldo.getSueldoTotal());

        try {
            JasperReport report = JasperCompileManager.compileReport(rutaJasper);
            JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(print, destino);
        } catch (JRException e) {
            e.printStackTrace();
        }
        return new File(destino);
    }
}

