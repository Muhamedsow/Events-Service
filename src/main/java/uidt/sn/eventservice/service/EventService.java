package uidt.sn.eventservice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uidt.sn.eventservice.client.GuestClient;
import uidt.sn.eventservice.dto.EventDTO;
import uidt.sn.eventservice.model.Event;
import uidt.sn.eventservice.repository.EventRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    @Autowired
    private  EventRepository repository;

    @Autowired
    private GuestClient guestClient;

    public Event create(EventDTO dto, String username) {

        Event event = new Event();
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setDate(dto.getDate());
        event.setLocation(dto.getLocation());
        event.setCapacity(dto.getCapacity());
        event.setCreatedBy(username);

        return repository.save(event);
    }

    public List<Event> getAll() {
        return repository.findAll();
    }

    public Event getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    public List<String> getAllGuests(String user, String role) {
        return guestClient.getGuests(user, role);
    }
}
