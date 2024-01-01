
package com.egg.biblioteca.servicios;

import com.egg.biblioteca.entidades.Usuario;
import com.egg.biblioteca.enumeraciones.Rol;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositorio.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class UsuarioServicio implements UserDetailsService{
     @Autowired
    private  UsuarioRepositorio usuariorepositorio;
     
     
      @Transactional
      public void registrar (String nombre, String email, String password, String password2)throws MiException{
          validar (nombre, email, password, password2);
          
          Usuario usuario = new Usuario ();
          usuario.setNombre(nombre);
          usuario.setEmail(email);
          usuario.setPassword(password);
          usuario.setRol(Rol.USER);
          usuariorepositorio.save(usuario);
          
          
          
          
      }
    private void validar (String nombre, String email, String password, String password2) throws MiException{
        
        if (nombre.isEmpty ()|| nombre==null){
            
                throw new MiException ("el nombre es nulo o esta vacio");
            
            }
        
            if (email.isEmpty() || email==null){
           
                throw new MiException ( "el mail no puede ser nulo o estar vacio");
            }
            
        if (password.isEmpty() || password==null || password.length()<=5){
            
                throw new MiException (" la contrasena de ser correcta o mayor a 5 digitos");
                       }
        
        if (!password2.equals(password2)){
        
                throw new MiException ("la contrasena debe ser igual");
            
            
            
        }
        
        
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuariorepositorio.buscarporEmail(email);
        
        if(usuario!=null){
            
            List<GrantedAuthority> permisos = new ArrayList ();
            
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
            permisos.add(p);
        
        
        return new User(usuario.getEmail(), usuario.getPassword(), permisos);
        }else {
            return null;
        }                    
    
   }
}

