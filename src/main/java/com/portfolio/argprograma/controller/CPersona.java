/*

 */
package com.portfolio.argprograma.controller;

import com.portfolio.argprograma.entity.Persona;
import com.portfolio.argprograma.service.SPersona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RequestMapping("persona")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CPersona {
    @Autowired
    private SPersona persoServ;

    @GetMapping ("/ver")
    @ResponseBody
    public List<Persona> verPersonas(){
        return persoServ.verPersonas();
    }

    
    @PostMapping ("/new")

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    //@PreAuthorize("hasRole('Admin')")
    public String agregarPersona (@RequestBody Persona perso){
        persoServ.crearPersona(perso);
        return "La persona fue creada correctamente";
    }

   
    @DeleteMapping ("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String eliminarPersona(@PathVariable int id){
        persoServ.borrarPersona(id);
        return "La persona fue borrada correctamente";
    }
    
  
    @PutMapping ("/editar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Persona editPersona(@PathVariable int id, 
            @RequestBody Persona perso){
            persoServ.savePersona(perso);
            return perso;
    }

    /*
    public Persona editPersona (@PathVariable Long id,
                                @RequestParam ("nombre") String nuevoNombre,
                                @RequestParam ("apellido") String nuevoApellido,
                                @RequestParam ("profesion") String nuevoProfesion,
                                @RequestParam ("acerca_de_mi") String nuevoAcerca_de_mi,
                                @RequestParam ("acercademi") String nuevoAcercademi,
                                @RequestParam ("imageprincipal") String nuevaImageprincipal){
        
        //Busca persona
        Persona perso = persoServ.buscarPersona(id);

        perso.setNombre(nuevoNombre);
        perso.setApellido(nuevoApellido);
        perso.setProfesion(nuevoProfesion);
        perso.setAcerca_de_mi(nuevoAcerca_de_mi);
        perso.setAcercademi(nuevoAcercademi);     
        perso.setImageprincipal(nuevaImageprincipal);
        
        persoServ.crearPersona(perso);

        return perso;

    }*/
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable int id) {
        if (persoServ.existById(id)) {
            Persona persona = persoServ.buscarPersona(id); //getOne
            return new ResponseEntity(persona, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
 
    }

    
}
