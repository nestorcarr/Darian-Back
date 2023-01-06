/*

 */
package com.portfolio.argprograma.controller;

import com.portfolio.argprograma.entity.Proyecto;
import com.portfolio.argprograma.service.SProyecto;
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
@RequestMapping("proyecto")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CProyecto {
    @Autowired
    private SProyecto proyeServ;

    @GetMapping ("/ver")
    @ResponseBody
    public List<Proyecto> verProyecto(){
        return proyeServ.verProyecto();
    }
    
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping ("/new")
    public String agregarProyecto (@RequestBody Proyecto proye){
        proyeServ.crearProyecto(proye);
        return "El proyecto fue creado correctamente";
    }
    
    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping ("/delete/{id}")
    public String eliminarProyecto(@PathVariable int id){
        proyeServ.borrarProyecto(id);
        return "El proyecto fue borrado correctamente";
    }
    
    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping ("/editar/{id}")
    public Proyecto editProyecto (@PathVariable int id,
            @RequestBody Proyecto proye){
            proyeServ.saveProyecto(proye);
            return proye;
    }

    //@PreAuthorize("hasRole('ADMIN')")
    /*@PutMapping ("/editar/{id}")
    public Proyecto editProyecto (@PathVariable Long id,
                                @RequestParam ("institucion") String nuevaInstitucion,
                                @RequestParam ("proyecto") String nuevoProyecto,
                                @RequestParam ("profesion") String nuevaProfesion,
                                @RequestParam ("logoproyecto") String nuevoLogoproyecto,
                                @RequestParam ("tema") String nuevoTema,
                                @RequestParam ("temauno") String nuevoTemauno,
                                @RequestParam ("anio") Date nuevoAnio,
                                @RequestParam ("estado") String nuevoEstado){
        //Busca proyecto
        Proyecto proye = proyeServ.buscarProyecto(id);

        //proye.setInstitucion(nuevaInstitucion);
        proye.setInstitucion(nuevaInstitucion);
        proye.setProyecto(nuevoProyecto);
        proye.setProfesion(nuevaProfesion);
        proye.setLogoproyecto(nuevoLogoproyecto);
        proye.setTema(nuevoTema);
        proye.setTemauno(nuevoTemauno);
        proye.setAnio(nuevoAnio);
        proye.setEstado(nuevoEstado);
            
        proyeServ.crearProyecto(proye);
      
        return proye;
    
    }*/
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable int id) {
        if (proyeServ.existById(id)) {
            Proyecto proyecto = proyeServ.buscarProyecto(id); //getOne
            return new ResponseEntity(proyecto, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
 
    }
}
