package uidt.sn.eventservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uidt.sn.eventservice.exception.CapacityException;
import uidt.sn.eventservice.exception.DateException;
import uidt.sn.eventservice.model.Evenement;
import uidt.sn.eventservice.repository.EvenementRepository;

import java.util.List;

@Service
public class EvenementService {
    @Autowired
    private EvenementRepository evenementRepository;

    public void createEvenement(Evenement evenement) {
        if (evenement.getDate() == null || evenement.getDate().isBefore(java.time.LocalDate.now())) {
            throw new DateException("La date de l'événement doit être valide et ne pas être dans le passé.");
        }
        if (evenement.getCapacite() != null && evenement.getCapacite() <= 0) {
            throw new CapacityException("La capacité doit être supérieur à 0.");
        }
        evenementRepository.save(evenement);
    }

    public List<Evenement> findAll() {
        return evenementRepository.findAll();
    }

    public void deleteEvenement(Long id) {
        evenementRepository.deleteById(id);
    }

    public void updateEvenement(Evenement evenement) {
        if (evenement.getDate() == null || evenement.getDate().isBefore(java.time.LocalDate.now())) {
            throw new DateException("La date de l'événement doit être valide et ne pas être dans le passé.");
        }
        if (evenement.getCapacite() != null && evenement.getCapacite() <= 0) {
            throw new CapacityException("La capacité doit être supérieur à 0.");
        }
        evenementRepository.save(evenement);
    }

    public Evenement getEvenementById(Long id) {
        return evenementRepository.findById(id).orElse(null);
    }
}