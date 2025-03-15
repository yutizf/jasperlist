package com.fernandoyutiz.jasperlist.controllers;

import com.fernandoyutiz.jasperlist.Persona;
import com.fernandoyutiz.jasperlist.PruebaJasper;
import com.fernandoyutiz.jasperlist.graficos.Graficos;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/")
public class PrimaryController {
    @GetMapping("/hola")
    @ResponseBody
    public String saludo() throws JRException {
        String jasperFile="src/main/resources/ListaValores.jasper";
        Map<String,Object> parametros = new HashMap<>();
        parametros.put("generador","Fernando Yutiz");
        parametros.put("java", new PruebaJasper());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String[] textLines = {
                "EMITIDO",
                dateFormat.format(new Date()),
                "Empresa S.A.",
        };
        parametros.put("imagen", Graficos.generaSello(textLines));

        List<String> profesionesPersona1 = new ArrayList<>();
        List<String> profesionesPersona2 = new ArrayList<>();
        profesionesPersona1.add("Abogado");
        profesionesPersona1.add("Diputado");
        profesionesPersona2.add("Programador");
        profesionesPersona2.add("Tecnico electronico");

        Persona persona1 = new Persona();
        persona1.setNombre("Nombres");
        persona1.setApellido("Apellidos");
        persona1.setEdad(50);
        persona1.setProfesiones(profesionesPersona1);

        Persona persona2 = new Persona();
        persona2.setNombre("Fernando");
        persona2.setApellido("Yutiz");
        persona2.setEdad(54);
        persona2.setProfesiones(profesionesPersona2);

        List<Persona> campo= new ArrayList<>();
        campo.add(persona1);
        campo.add(persona2);
        campo.add(persona1);
        campo.add(persona2);
        campo.add(persona1);
        campo.add(persona2);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(campo);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFile, parametros, dataSource);

        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("listavalores.pdf"));
        exporter.exportReport();


        return "Hola Fernando, como estas?";
    }
    @GetMapping("/api")
    @ResponseBody
    public String api(){
        String url="https://fernandoyutiz.com/platzi/api/tiempo.html";
        System.out.println("Ok");
        return new RestTemplate().getForObject(url,String.class);
    }
}
