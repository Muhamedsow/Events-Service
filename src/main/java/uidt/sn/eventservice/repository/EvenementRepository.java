package uidt.sn.eventservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uidt.sn.eventservice.model.Evenement;

@Repository
public interface EvenementRepository extends JpaRepository<Evenement, Long> {
}