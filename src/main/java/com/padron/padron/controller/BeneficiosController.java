package com.padron.padron.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.padron.padron.services.BeneficiosPorSocioService;
import com.padron.padron.services.SociosService;
import com.padron.padron.services.BeneficioSocioService;
import com.padron.padron.entities.BeneficioPorSocio;
import com.padron.padron.entities.BeneficioSocio;
import com.padron.padron.entities.Socios;

@Controller
@RequestMapping("/beneficios")
public class BeneficiosController {

    @Autowired
    private SociosService sociosService;

    @Autowired
    private BeneficioSocioService beneficioService;

    @Autowired
    private BeneficiosPorSocioService beneficiosPorSocioService;

    // Método para listar los beneficios existentes
    @GetMapping("")
    public String listarBeneficios(Model model) {
        model.addAttribute("beneficios", beneficioService.obtenerTodosLosBeneficios());
        return "beneficios/listar-beneficios"; // Nombre de la vista Thymeleaf para listar beneficios
    }

    // Método para mostrar el formulario de asignar beneficios a socios
    @GetMapping("/asignar")
    public String mostrarFormularioAsignar(Model model) {
        model.addAttribute("socios", sociosService.listarSocios());
        model.addAttribute("beneficios", beneficioService.obtenerTodosLosBeneficios());
        model.addAttribute("beneficioPorSocio", new BeneficioPorSocio()); // Esto asegura que el objeto está en el contexto
        return "beneficios/asignar-beneficio"; // Nombre de la vista Thymeleaf para asignar beneficios
    }

    // Método para procesar la asignación de beneficios
    @PostMapping("/asignar-beneficio")
public String asignarBeneficio(@RequestParam("socioId") Long socioId, @RequestParam("beneficioId") Long beneficioId, RedirectAttributes redirectAttributes) {
    Socios socio = sociosService.leeIdSocios(socioId);
    BeneficioSocio beneficio = beneficioService.obtenerBeneficioPorId(beneficioId);

    if (socio == null || beneficio == null) {
        throw new RuntimeException("Socio o Beneficio no encontrado");
    }

    // Verificar que socio y beneficio no sean null
    BeneficioPorSocio beneficioPorSocio = new BeneficioPorSocio();
    beneficioPorSocio.setSocio(socio);
    beneficioPorSocio.setBeneficio(beneficio);
    beneficioPorSocio.setEstado(1);
    beneficioPorSocio.setFechaAsignacion(LocalDate.now());

    beneficiosPorSocioService.guardarBeneficioPorSocio(beneficioPorSocio);

    redirectAttributes.addFlashAttribute("message", "Beneficio asignado correctamente");
    return "redirect:/beneficios/asignar";
}

}
