/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca.servicios;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.entidades.Libro;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositorio.AutorRepositorio;
import com.egg.biblioteca.repositorio.EditorialRepositorio;
import com.egg.biblioteca.repositorio.LibroRepositorio;
import java.util.ArrayList;
import java.util.Date;
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

public class libroServicio {
    
    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer Ejemplares, String IdAutor, String IdEditorial)throws MiException{
          validar(isbn, titulo, Ejemplares, IdAutor, IdEditorial);
        Autor autor = autorRepositorio.findById(IdAutor).get();
        Editorial editorial = editorialRepositorio.findById(IdAutor).get();
        Libro libro = new Libro();
        
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(Ejemplares);
        libro.setAlta(new Date());
        
        libro.setAutor(autor);
        libro.setEditorial(editorial);
         libroRepositorio.save(libro);
         
        
    }
    public List<Libro> Listarlibros(){
        List <Libro> libros = new ArrayList();
        
        libros = libroRepositorio.findAll();
     return libros;
    }
     public void modificarLibro( long isbn, String titulo,String IdAutor, String IdEditorial, Integer ejemplares) throws MiException{
         validar(isbn, titulo, ejemplares, IdAutor, IdEditorial);
     Optional<Libro> respuesta = libroRepositorio.findById(isbn);
     Optional<Autor> respuestaAutor = autorRepositorio.findById(IdAutor);
     Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(IdEditorial);
     
     Autor autor = new Autor();
     Editorial editorial = new Editorial();
     
     if (respuestaAutor.isPresent()){
        autor = respuestaAutor.get();
     }
     
     if(respuestaEditorial.isPresent()){
        editorial = respuestaEditorial.get();
     }
     
     if(respuesta.isPresent()){
         Libro libro = respuesta.get();
         
         
     libro.setTitulo(titulo);
     libro.setAutor(autor);
     libro.setEditorial(editorial);
     libro.setEjemplares(ejemplares);
     libroRepositorio.save(libro);
     
     }
     }
     private void validar (Long isbn, String titulo, Integer ejemplares, String IdAutor, String IdEditorial)throws MiException{
         
         if(isbn==null){
             throw new MiException (" el ismb no puede ser nulo");
         }
         if (titulo.isEmpty()|| titulo==null){
             throw new MiException ("el titulo es nulo o esta vacio");
         }
          if (IdAutor.isEmpty()|| IdAutor==null){
             throw new MiException ("el autor es nulo o esta vacio");
          }
          
         if (IdEditorial.isEmpty()|| IdEditorial==null){
             throw new MiException ("la editorial es nulo o esta vacio");
         }
     }
}
