package uidt.sn.eventservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uidt.sn.eventservice.enumeration.EventStatus;
import uidt.sn.eventservice.exception.EventCompleted;
import uidt.sn.eventservice.exception.EventNotFound;
import uidt.sn.eventservice.model.Event;
import uidt.sn.eventservice.repository.EventRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public Event createEvent(Event event) {
        event.setStatus(EventStatus.PUBLISHED);
        return eventRepository.save(event);
    }

    public Event getById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFound("Event not found with id: " + id));
    }

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public List<Event> getByStatus(EventStatus status) {
        return eventRepository.findByStatus(status);
    }

    public List<Event> getUpcomingEvents() {
        return eventRepository.findUpcomingEvents(
                LocalDateTime.now(),
                EventStatus.PUBLISHED
        );
    }

    public Event updateEvent(Long id, Event updated) {
        Event existing = getById(id);
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setStartDateTime(updated.getStartDateTime());
        existing.setEndDateTime(updated.getEndDateTime());
        existing.setLocation(updated.getLocation());
        existing.setCapacity(updated.getCapacity());
        return eventRepository.save(existing);
    }

    public Event cancelEvent(Long id) {
        Event event = getById(id);
        if (event.getStatus() == EventStatus.COMPLETED) {
            throw new EventCompleted("Cannot cancel a COMPLETED event");
        }
        event.setStatus(EventStatus.CANCELLED);
        return eventRepository.save(event);
    }

    public List<Event> getMyEvent(Long userId) {
        return eventRepository.findByCreatedBy(userId);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}