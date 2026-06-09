package uidt.sn.eventservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uidt.sn.eventservice.model.Event;


public interface EventRepository extends JpaRepository<Event, Long> {

}