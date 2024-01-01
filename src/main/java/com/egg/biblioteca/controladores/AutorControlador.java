/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca.controladores;


import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.servicios.AutorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author danie
 */
@Controller
@RequestMapping("/autor")
public class AutorControlador {
    
    @Autowired
    private AutorServicio autorServicio;
    
    
    @GetMapping("/registrar")
    public String registrar (){
        return "autor_form.html";
    }
    @PostMapping("/registro")
    
    public String registro (@RequestParam String nombre, ModelMap modelo){
        System.out.println("nombre:"+nombre);
        
             autorServicio.crearAutor(nombre);
       
        return "index.html";
  }
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<Autor>autores= autorServicio.ListarAutores();
        modelo.addAttribute("autores", autores);
        return "autor_lista.html";
    }
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
        modelo.put("autor", autorServicio.getOne(id));
        return "autor_modif.html";
        
    }
    @PostMapping("/modificar/{id}")
    public String modificar (@PathVariable String id, String nombre, ModelMap modelo){
        autorServicio.modificarAutor(nombre, id);
        return "redirect:../lista";
        
        
}
    
}