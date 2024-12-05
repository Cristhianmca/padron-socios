package com.padron.padron.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.padron.padron.entities.BeneficioPorSocio;
import com.padron.padron.entities.ExcelExporter;
import com.padron.padron.entities.Socios;
import com.padron.padron.entities.SociosDto;
import com.padron.padron.services.BeneficiosPorSocioService;
import com.padron.padron.services.SociosService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/socios")
public class SociosController {

    @Autowired
    private SociosService service;
    @Autowired
    private BeneficiosPorSocioService beneficioPorSocioService;

    @GetMapping({ "", "/" })
    public String getSocios(Model model, HttpSession session) {
        if (session.getAttribute("tiposession") == null) {
            return "redirect:/usuario/login";
        }
        Long idSocio = (Long) session.getAttribute("idsession");
        Integer tipoSession = (Integer) session.getAttribute("tiposession");
        if (tipoSession == 1) {
            var socios = service.listarSocios();
        model.addAttribute("socios", socios);
        }else if (tipoSession == 2) {
            Socios socio = service.leeIdSocios(idSocio);
            model.addAttribute("socios", List.of(socio));
            
        }
        

        return "socios/index";
    }

    @GetMapping("/perfil")
    public String mostrarPerfil(Model model, HttpSession session) {
        Long idSocio = (Long) session.getAttribute("idsession");
        if (idSocio == null) {
            return "redirect:/usuario/login";
        }
        Socios socio = service.leeIdSocios(idSocio);
        List<BeneficioPorSocio> beneficios = beneficioPorSocioService.obtenerBeneficiosPorSocio(idSocio);
    
        // Log para depuración
        System.out.println("Socio: " + socio);
        System.out.println("Beneficios: " + beneficios);
    
        model.addAttribute("socio", socio);
        model.addAttribute("beneficios", beneficios);
        return "socios/perfil";
    }
    

    @PostMapping("/login")
    public String procesarLogin(@ModelAttribute("socio") SociosDto socio, HttpSession session) {
        Socios socioValido = service.leeLogin(socio.getDni(), socio.getClave());

        if (socioValido != null) {
            session.setAttribute("tiposession", socioValido.getTipo());
            session.setAttribute("usuario", socioValido);
            return "redirect:/socios"; 
        } else {
            return "redirect:/usuario/login?error"; 
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/usuario/login"; 
    }

    @GetMapping("/create")
    public String createSocios(Model model, HttpSession session) {
        if (session.getAttribute("tiposession") == null) {
            return "redirect:/usuario/login";
        }
        SociosDto dto = new SociosDto();
        model.addAttribute("dto", dto);
        return "socios/create";
    }

    @PostMapping("/create")
    public String createSocios(@Valid @ModelAttribute("dto") SociosDto dto) {
        Socios socio = new Socios();
        socio.setDni(dto.getDni());
        socio.setNombre(dto.getNombre());
        socio.setApellidoP(dto.getApellidoP());
        socio.setApellidoM(dto.getApellidoM());
        socio.setCorreo(dto.getCorreo());
        socio.setTelefono(dto.getTelefono());
        socio.setDireccion(dto.getDireccion());
        socio.setFechaNacimiento(dto.getFechaNacimiento());
        socio.setOcupacion(dto.getOcupacion());
        socio.setGenero(dto.getGenero());
        socio.setFechaAfiliacion(dto.getFechaAfiliacion());
        socio.setClave(dto.getClave());
        socio.setEstado(dto.getEstado());
        socio.setTipo(dto.getTipo());

        service.guardarSocios(socio);
        return "redirect:/socios";
    }

    @GetMapping("/edit")
    public String editarSocios(Model model, @RequestParam Long id, HttpSession session) {
        if (session.getAttribute("tiposession") == null) {
            return "redirect:/usuario/login";
        }
        Socios socio = service.leeIdSocios(id);
        if (socio == null) {
            return "redirect:/socios";
        }
        SociosDto dto = new SociosDto();
        dto.setDni(socio.getDni());
        dto.setNombre(socio.getNombre());
        dto.setApellidoP(socio.getApellidoP());
        dto.setApellidoM(socio.getApellidoM());
        dto.setCorreo(socio.getCorreo());
        dto.setTelefono(socio.getTelefono());
        dto.setDireccion(socio.getDireccion());
        dto.setFechaNacimiento(socio.getFechaNacimiento());
        dto.setOcupacion(socio.getOcupacion());
        dto.setGenero(socio.getGenero());
        dto.setFechaAfiliacion(socio.getFechaAfiliacion());
        dto.setEstado(socio.getEstado());
        dto.setTipo(socio.getTipo());
        model.addAttribute("dto", dto);
        model.addAttribute("socio", socio);
        return "socios/edit";
    }

    @PostMapping("/edit")
    public String editarSocio(@RequestParam Long id, @ModelAttribute("dto") SociosDto dto) {
        Socios socio = service.leeIdSocios(id);
        socio.setDni(dto.getDni());
        socio.setNombre(dto.getNombre());
        socio.setApellidoP(dto.getApellidoP());
        socio.setApellidoM(dto.getApellidoM());
        socio.setCorreo(dto.getCorreo());
        socio.setTelefono(dto.getTelefono());
        socio.setDireccion(dto.getDireccion());
        socio.setFechaNacimiento(dto.getFechaNacimiento());
        socio.setOcupacion(dto.getOcupacion());
        socio.setGenero(dto.getGenero());
        socio.setFechaAfiliacion(dto.getFechaAfiliacion());
        socio.setEstado(dto.getEstado());
        socio.setTipo(dto.getTipo());
        service.guardarSocios(socio);
        return "redirect:/socios";
    }

    @GetMapping("/delete")
    public String eliminarSocio(@RequestParam Long id, HttpSession session) {
        if (session.getAttribute("tiposession") == null) {
            return "redirect:/usuario/login";
        }
        service.eliminarSocio(id);
        return "redirect:/socios";
    }
    
    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=socios.xlsx";
        response.setHeader(headerKey, headerValue);
    
        List<Socios> listSocios = service.listarSocios();
    
        ExcelExporter excelExporter = new ExcelExporter(listSocios);
        excelExporter.export(response);
    }
@GetMapping("/reportes")
public String mostrarReportes(Model model) {
    List<Socios> listSocios = service.listarSocios();
    model.addAttribute("socios", listSocios);
    return "socios/reportes";
}
}