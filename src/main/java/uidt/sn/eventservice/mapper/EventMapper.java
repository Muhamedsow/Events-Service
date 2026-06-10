package uidt.sn.eventservice.mapper;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import uidt.sn.eventservice.dto.EventRequestDTO;
import uidt.sn.eventservice.dto.EventResponseDTO;
import uidt.sn.eventservice.model.Event;
import java.util.List;

@Component
public class EventMapper {

    public Event toEntity(EventRequestDTO dto, Jwt jwt) {
        return Event.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .startDateTime(dto.getStartDateTime())
                .endDateTime(dto.getEndDateTime())
                .location(dto.getLocation())
                .capacity(dto.getCapacity())
                .createdBy(jwt.getClaim("userId"))
                .build();
    }

    public EventResponseDTO toDTO(Event event) {
        return EventResponseDTO.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .startDateTime(event.getStartDateTime())
                .endDateTime(event.getEndDateTime())
                .location(event.getLocation())
                .capacity(event.getCapacity())
                .createdById(event.getCreatedBy())
                .build();
    }

    public List<EventResponseDTO> toDTOList(List<Event> events) {
        return events.stream().map(this::toDTO).toList();
    }
}