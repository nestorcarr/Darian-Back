/*

 */
package com.portfolio.argprograma.controller;

import com.portfolio.argprograma.entity.Laboral;
import com.portfolio.argprograma.service.SLaboral;
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
@RequestMapping("laboral")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CLaboral {
    @Autowired
    SLaboral trabaServ;

    @GetMapping ("/ver")
    @ResponseBody
    public List<Laboral> verTrabajos(){
        return trabaServ.verTrabajos();
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping ("/new")
    public String agregarTrabajo (@RequestBody Laboral traba){
        trabaServ.crearTrabajo(traba);
        return "El trabajo fue creado correctamente";
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping ("/delete/{id}")
    public String eliminarTrabajo(@PathVariable int id){
        trabaServ.borrarTrabajo(id);
        return "El trabajo fue borrado correctamente";
    }
    
    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping ("/editar/{id}")
    
    public Laboral editLaboral(@PathVariable int id, @RequestBody Laboral traba){
    trabaServ.saveLaboral(traba);
       return traba;
    }

    //@PreAuthorize("hasRole('ADMIN')")
   /* @PutMapping ("/editar/{id}")
    public Laboral editTrabajo (@PathVariable Long id,
                                @RequestParam ("empresa") String nuevaEmpresa,
                                @RequestParam ("logo") String nuevoLogo,
                                @RequestParam ("cargo") String nuevoCargo,
                                @RequestParam ("inicio") Date nuevoInicio,
                                @RequestParam ("finalizado") Date nuevoFinalizacion,
                                @RequestParam ("tareas") String nuevaTareas
    ){
        //Busca 
        Laboral traba=trabaServ.buscarTrabajo(id);
        
        traba.setEmpresa(nuevaEmpresa);
        traba.setLogo(nuevoLogo);
        traba.setCargo(nuevoCargo);
        traba.setInicio(nuevoInicio);
        traba.setFinalizacion(nuevoFinalizacion);
        traba.setTareas(nuevaTareas);
        
        trabaServ.crearTrabajo(traba);
        
        return traba;

    }*/
    @GetMapping("/detail/{id}")
    public ResponseEntity<Laboral> getById(@PathVariable int id) {
        if (trabaServ.existById(id)) {
            Laboral laboral = trabaServ.buscarTrabajo(id); //getOne
            return new ResponseEntity(laboral, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
 
    }
    
}
