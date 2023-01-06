/*

 */
package com.portfolio.argprograma.controller;

import com.portfolio.argprograma.entity.Habilidades;
import com.portfolio.argprograma.service.SHabilidades;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("habilidad")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CHabilidades {
    @Autowired
    private SHabilidades habilServ;
    
      
    @GetMapping ("/ver")
    @ResponseBody
    public List<Habilidades> verHabilidad(){
        return habilServ.verHabilidad();
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping ("/new")
    public String agregarHabilidad (@RequestBody Habilidades habil){
        habilServ.crearHabilidad(habil);
        return "La habilidad fue creada correctamente";
    }
    
    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping ("/delete/{id}")
    public String eliminarHabilidad(@PathVariable int id){
        habilServ.borrarHabilidad(id);
        return "La habilidad fue borrada correctamente";
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping ("/editar/{id}")
    public Habilidades editHabilidad (@PathVariable Long id,
            @RequestBody Habilidades habil){
            habilServ.saveHabilidad(habil);
            return habil;
    }
    
    /*@PutMapping ("/editar/{id}")
    public Habilidades editHabilidad (@PathVariable Long id,
                                @RequestParam ("habilidad") String nuevaHabilidad,
                                @RequestParam ("porcentaje") int nuevoPorcentaje,
                                @RequestParam ("color") String nuevoColor){
        //Busca habilidad
        Habilidades habil = habilServ.buscarHabilidad(id);
        
        habil.setHabilidad(nuevaHabilidad);
        habil.setPorcentaje(nuevoPorcentaje);
        habil.setColor(nuevoColor);
            
        habilServ.crearHabilidad(habil);

        return habil;

    }*/
    @GetMapping("/detail/{id}")
    public ResponseEntity<Habilidades> getById(@PathVariable int id) {
        if (habilServ.existById(id)) {
            Habilidades habilidades = habilServ.buscarHabilidad(id); //getOne
            return new ResponseEntity(habilidades, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
 
    }
}
