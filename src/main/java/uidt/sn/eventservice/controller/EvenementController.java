package uidt.sn.eventservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uidt.sn.eventservice.model.Evenement;
import uidt.sn.eventservice.service.EvenementService;
import java.util.Map;

@RestController
@RequestMapping("/events")
public class EvenementController {
    @Autowired
    EvenementService evenementService;

     @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody Evenement evenement) {
        evenementService.createEvenement(evenement);
        return ResponseEntity.ok(Map.of("message", "Événement créé avec succès"));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getEvenementById(@PathVariable long id) {
         Evenement evenement = evenementService.getEvenementById(id);
         return ResponseEntity.ok(evenement);
    }

    @GetMapping
    public ResponseEntity<?> getEvenements() {
        return ResponseEntity.ok(evenementService.findAll());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateEvenement(@PathVariable long id, @RequestBody Evenement evenement) {
        evenementService.updateEvenement(evenement);
        return ResponseEntity.ok(Map.of("message", "Événement mis à jour avec succès"));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEvenement(@PathVariable long id) {
        evenementService.deleteEvenement(id);
        return ResponseEntity.ok(Map.of("message", "Événement supprimé avec succès"));
    }
}