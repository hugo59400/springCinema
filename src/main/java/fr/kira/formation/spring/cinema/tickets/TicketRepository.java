package fr.kira.formation.spring.cinema.tickets;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    // afficher la liste des tickets réservés pour une séance donnée
    List<Ticket> findBySeanceId(Long seanceId);

}
