package com.padron.padron.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.padron.padron.entities.Usuario;
import com.padron.padron.entities.UsuarioDto;
import com.padron.padron.services.UsuarioService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService servicio;

    @GetMapping({ "", "/" })
    public String getUsuario(Model model, HttpSession session) {
        if (session.getAttribute("tiposession") == null) {
            return "redirect:/usuario/login";
        }
        var usuario = servicio.listUsuario();
        model.addAttribute("usuario", usuario);
        return "usuario/index";
    }

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        UsuarioDto dto = new UsuarioDto(); // Crear un nuevo DTO vacío para el formulario de login
        model.addAttribute("usuario", dto); // Añadir el objeto 'usuario' al modelo
        return "usuario/login"; // Renderizar la vista 'login.html'
    }

    @PostMapping("/login")
    public String procesarLogin(@ModelAttribute("usuario") UsuarioDto usuario, HttpSession session) {
        // Aquí valida el usuario y guarda la información en la sesión si es válido
        Usuario usuarioValido = servicio.leeLogin(usuario.getDni(), usuario.getClave());

        if (usuarioValido != null) {
            session.setAttribute("tiposession", usuarioValido.getTipo());
            return "redirect:/usuario"; // Redirige a una página segura
        } else {
            return "redirect:/usuario/login?error"; // Redirige al formulario de login con un mensaje de error
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalida la sesión
        return "redirect:/usuario/login"; // Redirige al login
    }

    @GetMapping("/create")
    public String createUsuario(Model model, HttpSession session) {
        if (session.getAttribute("tiposession") == null) {
            return "redirect:/usuario/login";
        }
        UsuarioDto dto = new UsuarioDto();
        model.addAttribute("dto", dto);
        return "usuario/create";
    }

    @PostMapping("/create")
public String createUsuario(@Valid @RequestBody @ModelAttribute UsuarioDto dto) {
    

    Usuario usuario = new Usuario();
    usuario.setDni(dto.getDni());
    usuario.setApellidos(dto.getApellidos());
    usuario.setNombres(dto.getNombres());
    usuario.setClave(dto.getClave());
    usuario.setTipo(dto.getTipo());
    usuario.setEstado(dto.getEstado());

    servicio.saveUsuario(usuario);
    return "redirect:/usuario";
}


    @GetMapping("/edit")
    public String editUsuario(Model model, @RequestParam Long id, HttpSession session) {
        if (session.getAttribute("tiposession") == null) {
            return "redirect:/usuario/login";
        }
        Usuario usuario = servicio.leeIdUsuario(id);
        if (usuario == null) {
            return "redirect:/usuario";
        }
        UsuarioDto dto = new UsuarioDto();
        dto.setDni(usuario.getDni());
        dto.setApellidos(usuario.getApellidos());
        dto.setNombres(usuario.getNombres());
        dto.setClave(usuario.getClave());
        dto.setTipo(usuario.getTipo());
        dto.setEstado(usuario.getEstado());
        model.addAttribute("dto", dto);
        model.addAttribute("usuario", usuario);
        return "usuario/edit";
    }

    @PostMapping("/edit")
    public String editUsuario(@RequestParam Long id, @ModelAttribute("dto") UsuarioDto dto) {
        Usuario usuario = servicio.leeIdUsuario(id);
        usuario.setDni(dto.getDni());
        usuario.setApellidos(dto.getApellidos());
        usuario.setNombres(dto.getNombres());
        usuario.setClave(dto.getClave());
        usuario.setTipo(dto.getTipo());
        usuario.setEstado(dto.getEstado());
        servicio.saveUsuario(usuario);
        return "redirect:/usuario";
    }

    @GetMapping("/delete")
    public String deleteUsuario(@RequestParam Long id, HttpSession session) {
        if (session.getAttribute("tiposession") == null) {
            return "redirect:/usuario/login";
        }
        Usuario usuario = servicio.leeIdUsuario(id);
        if (usuario != null) {
            servicio.deleteUsuario(id);
        }
        return "redirect:/usuario";
    }
}
