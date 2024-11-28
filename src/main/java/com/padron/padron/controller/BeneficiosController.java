package com.padron.padron.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.padron.padron.entities.BeneficioPorSocio;
import com.padron.padron.entities.BeneficioSocio;
import com.padron.padron.entities.Socios;
import com.padron.padron.services.BeneficioSocioService;
import com.padron.padron.services.BeneficiosPorSocioService;
import com.padron.padron.services.SociosService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/beneficios")
public class BeneficiosController {

    @Autowired
    private SociosService sociosService;

    @Autowired
    private BeneficioSocioService beneficioService;

    @Autowired
    private BeneficiosPorSocioService beneficiosPorSocioService;

    @GetMapping("")
    public String listarBeneficios(Model model, HttpSession session) {
        if (session.getAttribute("tiposession") == null) {
            return "redirect:/usuario/login";
        }
        model.addAttribute("beneficios", beneficioService.obtenerTodosLosBeneficios());
        return "beneficios/listar-beneficios";
    }

    @GetMapping("/asignar")
    public String mostrarFormularioAsignar(Model model) {
        model.addAttribute("socios", sociosService.listarSocios());
        model.addAttribute("beneficios", beneficioService.obtenerTodosLosBeneficios());
        model.addAttribute("beneficioPorSocio", new BeneficioPorSocio());
        return "beneficios/asignar-beneficio";
    }

    @PostMapping("/asignar-beneficio")
    public String asignarBeneficio(@RequestParam("socioId") Long socioId, @RequestParam("beneficioId") Long beneficioId,
            RedirectAttributes redirectAttributes) {
        Socios socio = sociosService.leeIdSocios(socioId);
        BeneficioSocio beneficio = beneficioService.obtenerBeneficioPorId(beneficioId);

        if (socio == null || beneficio == null) {
            throw new RuntimeException("Socio o Beneficio no encontrado");
        }

        BeneficioPorSocio beneficioPorSocio = new BeneficioPorSocio();
        beneficioPorSocio.setSocio(socio);
        beneficioPorSocio.setBeneficio(beneficio);
        beneficioPorSocio.setEstado(1);
        beneficioPorSocio.setFechaAsignacion(LocalDate.now());
        beneficioPorSocio.setFechaFin(LocalDate.now().plusMonths(1));
        beneficiosPorSocioService.guardarBeneficioPorSocio(beneficioPorSocio);

        redirectAttributes.addFlashAttribute("message", "Beneficio asignado correctamente");
        return "redirect:/beneficios/asignar";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("beneficio", new BeneficioSocio());
        return "beneficios/agregar-beneficio";
    }

    @PostMapping("/guardar")
    public String guardarBeneficio(BeneficioSocio beneficio, RedirectAttributes redirectAttributes) {
        if (beneficio.getId() != null) {
            BeneficioSocio beneficioExistente = beneficioService.obtenerBeneficioPorId(beneficio.getId());
            if (beneficioExistente != null) {
                beneficioExistente.setNombreBeneficio(beneficio.getNombreBeneficio());
                beneficioExistente.setDescripcion(beneficio.getDescripcion());
                beneficioExistente.setFechaInicio(beneficio.getFechaInicio());
                beneficioExistente.setFechaFin(beneficio.getFechaFin());
                beneficioService.guardarBeneficio(beneficioExistente);
                redirectAttributes.addFlashAttribute("message", "Beneficio actualizado correctamente");
                return "redirect:/beneficios";
            }
        }
        beneficioService.guardarBeneficio(beneficio);
        redirectAttributes.addFlashAttribute("message", "Beneficio guardado correctamente");
        return "redirect:/beneficios";
    }

    @GetMapping("/editar")
    public String editarBeneficioSocio(@RequestParam("id") Long id, Model model) {
        BeneficioSocio beneficio = beneficioService.obtenerBeneficioPorId(id);
        model.addAttribute("beneficio", beneficio);
        return "beneficios/editar-beneficio";
    }

    @GetMapping("/editar-beneficio-por-socio")
    public String editarBeneficioPorSocio(@RequestParam("id") Long id, Model model) {
        BeneficioPorSocio beneficioPorSocio = beneficiosPorSocioService.obtenerBeneficioPorId(id);
        model.addAttribute("beneficioPorSocio", beneficioPorSocio);
        return "beneficios/editar-beneficio-por-socio";
    }

    @PostMapping("/guardar-beneficio-por-socio")
    public String guardarBeneficioPorSocio(@ModelAttribute BeneficioPorSocio beneficioPorSocio,
            RedirectAttributes redirectAttributes) {
        try {
            BeneficioPorSocio beneficioExistente = beneficiosPorSocioService
                    .obtenerBeneficioPorId(beneficioPorSocio.getId());
            if (beneficioExistente != null) {
                beneficioExistente.setEstado(beneficioPorSocio.getEstado());
                beneficioExistente.setFechaAsignacion(beneficioPorSocio.getFechaAsignacion());
                beneficioExistente.setFechaFin(beneficioPorSocio.getFechaFin());
                beneficiosPorSocioService.guardarBeneficioPorSocio(beneficioExistente);
                redirectAttributes.addFlashAttribute("message", "Beneficio por socio actualizado correctamente");
            }
            return "redirect:/beneficios/listar-por-socio?socioId=" + beneficioPorSocio.getSocio().getIdsocio();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el beneficio: " + e.getMessage());
            return "redirect:/beneficios";
        }
    }

   @GetMapping("/listar-por-socio")
public String listarBeneficiosPorSocio(@RequestParam("socioId") Long socioId, Model model, HttpSession session) {
    if (session.getAttribute("tiposession") == null) {
        return "redirect:/usuario/login";
    }
    Socios socio = sociosService.leeIdSocios(socioId);
    if (socio == null) {
        throw new RuntimeException("Socio no encontrado");
    }
    List<BeneficioPorSocio> beneficiosPorSocio = beneficiosPorSocioService.obtenerBeneficiosPorSocio(socioId);
    model.addAttribute("socio", socio);
    model.addAttribute("beneficiosPorSocio", beneficiosPorSocio);
    model.addAttribute("tiposession", session.getAttribute("tiposession"));
    return "beneficios/listar-beneficios-por-socio";
}
    


   

    @PostMapping("/desactivar-beneficio-por-socio")
    public String desactivarBeneficioPorSocio(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        BeneficioPorSocio beneficioPorSocio = null;
        try {
            beneficioPorSocio = beneficiosPorSocioService.obtenerBeneficioPorId(id);
            if (beneficioPorSocio != null) {
                beneficioPorSocio.setEstado(0); // Cambia el estado a desactivado
                beneficiosPorSocioService.guardarBeneficioPorSocio(beneficioPorSocio);
                redirectAttributes.addFlashAttribute("message", "Beneficio por socio desactivado correctamente");
            } else {
                redirectAttributes.addFlashAttribute("error", "Beneficio por socio no encontrado");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al desactivar el beneficio: " + e.getMessage());
        }
        return "redirect:/beneficios/listar-por-socio?socioId=" + (beneficioPorSocio != null ? beneficioPorSocio.getSocio().getIdsocio() : "");
    }
    @PostMapping("/activar-beneficio-por-socio")
public String activarBeneficioPorSocio(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
    BeneficioPorSocio beneficioPorSocio = null;
    try {
        beneficioPorSocio = beneficiosPorSocioService.obtenerBeneficioPorId(id);
        if (beneficioPorSocio != null) {
            beneficioPorSocio.setEstado(1); // Cambia el estado a activado
            beneficiosPorSocioService.guardarBeneficioPorSocio(beneficioPorSocio);
            redirectAttributes.addFlashAttribute("message", "Beneficio por socio activado correctamente");
        } else {
            redirectAttributes.addFlashAttribute("error", "Beneficio por socio no encontrado");
        }
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("error", "Error al activar el beneficio: " + e.getMessage());
    }
    return "redirect:/beneficios/listar-por-socio?socioId=" + (beneficioPorSocio != null ? beneficioPorSocio.getSocio().getIdsocio() : "");
}

}
