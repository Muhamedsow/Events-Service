package uidt.sn.eventservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uidt.sn.eventservice.dto.EventDTO;
import uidt.sn.eventservice.model.Event;
import uidt.sn.eventservice.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    @Autowired
    private  EventService service;

    @PostMapping
    public Event create(@RequestBody EventDTO dto,
                        @RequestHeader("X-User") String username) {
        return service.create(dto, username);
    }

    @GetMapping
    public List<Event> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Event getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
