package com.padron.padron.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.padron.padron.entities.Socios;
import com.padron.padron.entities.SociosDto;
import com.padron.padron.services.SociosService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/socios")

public class SociosController {


    @Autowired
   private SociosService service;

    @GetMapping({ "", "/" })
    public String getSocios(Model model, HttpSession session) {
        if(session.getAttribute("tiposession")== null ){
            return "redirect:/usuario/login";
        }
        var Socios = service.listarSocios();
        model.addAttribute("socios", Socios);
        return "socios/index";
    }

    @GetMapping("/create")
    public String createSocios(Model model, HttpSession session){
         if(session.getAttribute("tiposession")== null ){
            return "redirect:/usuario/login";
        }
        SociosDto dto = new SociosDto();
        model.addAttribute("dto", dto);
        return "socios/create";
    }

    @PostMapping("/create")
    public String createSocios(@ModelAttribute("dto") SociosDto dto){
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
        socio.setEstado(dto.getEstado());
        socio.setTipo(dto.getTipo());

        service.guardarSocios(socio);
        return "redirect:/socios";

       
    }   

    @GetMapping("/edit")
    public String editarSocios(Model model,@RequestParam Long id , HttpSession session){
        if(session.getAttribute("tiposession")== null ){
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
    public String editarSocio(@RequestParam Long id,@ModelAttribute("dto") SociosDto dto){
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
    public String eliminarSocio(@RequestParam Long id, HttpSession session){
        if(session.getAttribute("tiposession")== null ){
            return "redirect:/usuario/login";
        }
        service.eliminarSocio(id);
        return "redirect:/socios";
    }
}
