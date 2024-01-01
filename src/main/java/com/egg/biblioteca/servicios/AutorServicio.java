/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca.servicios;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.repositorio.AutorRepositorio;
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
public class AutorServicio {
    @Autowired
    AutorRepositorio autorRepositorio;
    
    @Transactional
    public void crearAutor (String nombre){
    Autor autor = new Autor ();
    
    autor.setNombre(nombre);
    autorRepositorio.save(autor);
    
    
    }
    public List <Autor> ListarAutores (){
    
    List <Autor> autores = new ArrayList();
    autores = autorRepositorio.findAll();
    return autores;
    }
    public void modificarAutor (String nombre, String Id){
        Optional<Autor> respuesta = autorRepositorio.findById(Id);
        if( respuesta.isPresent()){
            Autor autor = respuesta.get();
            autor.setNombre(nombre);
            autorRepositorio.save(autor);
            
        }
        
            
        }
        public Autor getOne(String Id){
            return autorRepositorio.getOne(Id);
    }
}

