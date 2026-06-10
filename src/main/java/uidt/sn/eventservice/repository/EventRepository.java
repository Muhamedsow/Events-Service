package uidt.sn.eventservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uidt.sn.eventservice.enumeration.EventStatus;
import uidt.sn.eventservice.model.Event;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByStatus(EventStatus status);

    List<Event> findByCreatedBy(Long createdById);

    @Query("SELECT e FROM Event e WHERE e.startDateTime >= :date AND e.status = :status")
    List<Event> findUpcomingEvents(@Param("date") LocalDateTime date,
                                   @Param("status") EventStatus status);

    @Query("SELECT e FROM Event e WHERE e.capacity > :minCapacity AND e.status = 'PUBLISHED'")
    List<Event> findAvailableEvents(@Param("minCapacity") int minCapacity);
}