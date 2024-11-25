package com.padron.padron.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.padron.padron.entities.Usuario;
import com.padron.padron.entities.Socios;
import com.padron.padron.services.UsuarioService;
import com.padron.padron.services.SociosService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SociosService sociosService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "/usuario/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String dni, @RequestParam String clave, Model model, HttpSession session) {
        Usuario usuario = usuarioService.leeLogin(dni, clave);
        Socios socio = sociosService.leeLogin(dni, clave);

        if (usuario != null) {
            // Inicia sesión como usuario
            session.setAttribute("idsession", usuario.getIdusuario());
            session.setAttribute("tiposession", usuario.getTipo());
            return "redirect:/usuario/index";
        } else if (socio != null) {
            // Inicia sesión como socio
            session.setAttribute("idsession", socio.getIdsocio());
            session.setAttribute("tiposession", socio.getTipo());
            return "redirect:/socios/index";
        } else {
            // Credenciales inválidas
            model.addAttribute("usuario", new Usuario());
            model.addAttribute("loginError", "DNI o contraseña incorrectos, inténtalo nuevamente");
            return "/usuario/login";
        }
    }

    @GetMapping("/usuario/index")
    public String mostrarUsuarioIndex(HttpSession session, Model model) {
        if (session.getAttribute("idsession") == null) {
            return "redirect:/login"; // Si no hay sesión, redirige al login
        }
        return "/usuario/index"; // Muestra la vista index de la carpeta usuario
    }

    @GetMapping("/socios/index")
    public String mostrarSociosIndex(HttpSession session, Model model) {
        if (session.getAttribute("idsession") == null) {
            return "redirect:/login"; // Si no hay sesión, redirige al login
        }
        return "/socios/index"; // Muestra la vista index de la carpeta socios
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}