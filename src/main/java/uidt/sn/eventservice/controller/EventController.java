package uidt.sn.eventservice.controller;

import org.springframework.security.oauth2.jwt.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uidt.sn.eventservice.dto.EventRequestDTO;
import uidt.sn.eventservice.dto.EventResponseDTO;
import uidt.sn.eventservice.enumeration.EventStatus;
import uidt.sn.eventservice.mapper.EventMapper;
import uidt.sn.eventservice.model.Event;
import uidt.sn.eventservice.service.EventService;
import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final EventMapper eventMapper;

    @PostMapping
    public ResponseEntity<EventResponseDTO> create(
            @RequestBody EventRequestDTO dto,
            @AuthenticationPrincipal Jwt jwt) {
        Event event = eventMapper.toEntity(dto, jwt);
        Event saved = eventService.createEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventMapper.toDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAll() {
        return ResponseEntity.ok(eventMapper.toDTOList(eventService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(eventMapper.toDTO(eventService.getById(id)));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<EventResponseDTO>> getByStatus(@PathVariable EventStatus status) {
        return ResponseEntity.ok(eventMapper.toDTOList(eventService.getByStatus(status)));
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<EventResponseDTO>> getUpcoming() {
        return ResponseEntity.ok(eventMapper.toDTOList(eventService.getUpcomingEvents()));
    }

    @GetMapping("/myevent/{id}")
    public ResponseEntity<List<EventResponseDTO>> getMyEvent(@PathVariable Long id) {
        return ResponseEntity.ok(eventMapper.toDTOList(eventService.getMyEvent(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDTO> update(
            @PathVariable Long id,
            @RequestBody EventRequestDTO dto) {
        Event updated = eventMapper.toEntity(dto, null);
        return ResponseEntity.ok(eventMapper.toDTO(eventService.updateEvent(id, updated)));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<EventResponseDTO> cancel(@PathVariable Long id) {
        return ResponseEntity.ok(eventMapper.toDTO(eventService.cancelEvent(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}