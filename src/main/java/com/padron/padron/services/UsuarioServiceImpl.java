package com.padron.padron.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.padron.padron.entities.Usuario;
import com.padron.padron.repository.UsuarioRepository;

@Service
@Repository

public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository repository;

    @Override
    public List<Usuario> listUsuario() {
        return repository.findAll();
    }

    @Override

    public Usuario saveUsuario(Usuario usuario){
        return repository.save(usuario);
    }

    @Override
    public Usuario leeIdUsuario(Long id){
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteUsuario(Long id){
        repository.deleteById(id);
    }

    @Override

    public Usuario leeLogin(String dni , String clave){
        return repository.leeLogin(dni, clave);
    }

}
