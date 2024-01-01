/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca.servicios;

import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.repositorio.EditorialRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author danie
 */
@Service
public class EditorialServicio {
    @Autowired
    EditorialRepositorio editorialRepositorio;
    
    @Transactional
    public void crearEditorial ( String nombre){
        
        Editorial editorial = new Editorial();
        
    editorial.setNombre(nombre);
    editorialRepositorio.save(editorial);
    
    
    }
    
    public List <Editorial> ListarEditorial (){
        
        List<Editorial> editoriales = new ArrayList();
        editoriales = editorialRepositorio.findAll();
        return editoriales;
        
    
    }
    public void modificarEditorial (String nombre, String Id){
        Optional<Editorial> respuesta = editorialRepositorio.findById(Id);
        if (respuesta.isPresent()){
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);
            editorialRepositorio.save(editorial);
            
        }
        
    }
    
}
