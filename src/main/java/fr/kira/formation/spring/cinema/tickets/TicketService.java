package fr.kira.formation.spring.cinema.tickets;

import fr.kira.formation.spring.cinema.seances.Seance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    public Ticket save(Ticket entity) {
        return repository.save(entity);
    }

    public Ticket findById(Integer integer) {
        return repository.findById(integer).orElseThrow();
    }

    public void deleteById(Integer integer) {
        repository.deleteById(integer);
    }

    public Iterable<Ticket> findAll() {
        return repository.findAll();
    }

// afficher la liste des tickets réservés pour une séance donnée
public List<Ticket> getReservedTickets(Long screeningId) {
    return repository.findBySeanceId(screeningId);
}

    public Ticket save(Integer seanceId, Integer quantity, Ticket entity) {
        // Récupérez la séance à partir de l'ID de la séance
        Seance seance = repository.findById(seanceId).orElse(null).getSeance();

        // Vérifiez que la séance existe et qu'il reste suffisamment de places
        if (seance == null || seance.getNombrePlace() < quantity) {
            throw new IllegalArgumentException("Invalid seance or not enough seats available");
        }

        // Réduisez la capacité de la séance de la quantité de tickets réservés
        seance.setNombrePlace(seance.getNombrePlace() - quantity);
        repository.save(seance);

        // Enregistrez le ticket
        entity.setSeance(seance);
        return repository.save(entity);
    }


//


}
