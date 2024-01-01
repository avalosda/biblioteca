/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca.controladores;


import com.egg.biblioteca.entidades.Libro;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.servicios.AutorServicio;
import com.egg.biblioteca.servicios.EditorialServicio;
import com.egg.biblioteca.servicios.libroServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@RequestMapping("/libro")
public class LibroControlador {
    
    
    @Autowired
    private libroServicio libroservicio;
    @Autowired
     private EditorialServicio editorialServicio;
    @Autowired
    private AutorServicio autorServicio;
    
           @GetMapping("/registrar")
    public String registrar (){
        return "libro_form.html";
    }
    @PostMapping("/registro")
    public String registro (@RequestParam long isbn, @RequestParam String titulo, 
                            @RequestParam Integer ejemplares,  @RequestParam String IdAutor, 
                            @RequestParam String IdEditorial){
        try {
            libroservicio.crearLibro(isbn, titulo, ejemplares, IdAutor, IdEditorial);
        } catch (MiException ex) {
            Logger.getLogger(LibroControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "libro_form.html";
        }
        return "index.html";
    }
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<Libro>libros= libroservicio.Listarlibros();
        modelo.addAttribute("libro", libros);
        return "libro_lista.html";
    }
}
