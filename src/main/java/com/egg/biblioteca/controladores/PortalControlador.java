/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca.controladores;

import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author danie
 */
@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @Autowired
    private UsuarioServicio usuarioservicio;
    
      @GetMapping("/")
    public String index(){
        
        return "index.html";
    }
    @GetMapping("/registrar")
    public String registrar(){
        return "registro.html";
    }
    @PostMapping("/registro")
    public String registro (@RequestParam String nombre, @RequestParam String email, @RequestParam String password, String password2, ModelMap modelo){
        try {
            usuarioservicio.registrar(nombre, email, password, password2);
            modelo.put("exito", "Usuario registrado correctamente");
            return "index.html";
        } catch (MiException ex) {
           modelo.put("error", ex.getMessage());
           modelo.put("nombre", nombre);
           modelo.put("email", email);
           return "registro.html";
        }
    }
    
    @GetMapping("/login")
    public String login(){
        return "login.html";
}
}