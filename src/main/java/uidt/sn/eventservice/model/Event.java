package uidt.sn.eventservice.model;

import jakarta.persistence.*;
import lombok.*;
import uidt.sn.eventservice.enumeration.EventStatus;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String location;
    private int capacity;
    private Long createdBy;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private EventStatus status = EventStatus.PUBLISHED;
}