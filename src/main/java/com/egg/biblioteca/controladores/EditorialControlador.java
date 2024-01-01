/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca.controladores;

import com.egg.biblioteca.servicios.EditorialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author danie
 */
@Controller
@RequestMapping("/editorial")
public class EditorialControlador {
    @Autowired
    private EditorialServicio editorialServicio;
    
       @GetMapping("/registrar")
    public String registrar (){
        return "editorial_form.html";
    }
    @PostMapping("/registro")
    
    public String registro (@RequestParam String nombre){
        System.out.println("nombre:"+nombre);
        
             editorialServicio.crearEditorial(nombre);
       
        return "index.html";
  }
    
}
