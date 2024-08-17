package gt.edu.miumg.crud.controller;


import gt.edu.miumg.crud.model.Persona;
import gt.edu.miumg.crud.service.personaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/personas")
public class personaController {

    @Autowired
    private personaService personaService;


    @GetMapping
    public List<Persona> listarPersonas() {
        return personaService.listarPersonas();
    }


    @PostMapping
    public ResponseEntity<Persona> guardarPersona(@RequestBody Persona persona) {
        Persona nuevaPersona = personaService.guardarPersona(persona);
        return ResponseEntity.ok(nuevaPersona);
    }

   
    @PutMapping("/{index}")
    public ResponseEntity<Persona> modificarPersona(@PathVariable int index, @RequestBody Persona persona) {
        Persona personaModificada = personaService.modificarPersona(index, persona);
        if (personaModificada != null) {
            return ResponseEntity.ok(personaModificada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    @DeleteMapping("/{index}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable int index) {
        boolean eliminado = personaService.eliminarPersona(index);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
