package com.padron.padron.services;

import java.util.List;

import com.padron.padron.entities.Socios;


public interface SociosService {

    List<Socios> listarSocios();
    Socios guardarSocios(Socios socio);
    Socios leeIdSocios(Long id);
    void eliminarSocio(Long id);
    Socios leeLogin(String dni, String clave);

}
