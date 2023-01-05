package fr.kira.formation.spring.cinema.seances;

import fr.kira.formation.spring.cinema.films.Film;
import fr.kira.formation.spring.cinema.films.FilmService;
import fr.kira.formation.spring.cinema.salles.Salle;
import fr.kira.formation.spring.cinema.salles.SalleService;
import fr.kira.formation.spring.cinema.tickets.Ticket;
import fr.kira.formation.spring.cinema.tickets.TicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class SeanceService {

    private  final SeanceRepository repository;
    private final TicketRepository ticketRepository;
    private final FilmService filmService;
    private final SalleService salleService;


    public SeanceService(SeanceRepository repository, TicketRepository ticketRepository, FilmService filmService, SalleService salleService) {
        this.repository = repository;
        this.ticketRepository = ticketRepository;
        this.filmService = filmService;
        this.salleService = salleService;
    }

    public List<Seance> findAll() {
        return repository.findAll();
    }

    public Seance save(Seance entity) {
        return repository.save(entity);
    }

    public Seance findById(Integer integer) {
        return repository.findById(integer).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void deleteById(Integer integer) {
        repository.deleteById(integer);
    }


    // Afficher la liste des salles disponibles à une date et à une heure donnée
    public  List<Seance> getAvailableSeances(LocalDate date ) {
        return repository.findByDate(date);
    }

    public List<Ticket> findTicketSeance(Integer id){
        return this.ticketRepository.findBySeanceId(id);
    }


    public void addFilm(Integer id, Film film){
        Seance seance = repository.findById(id).orElseThrow();
        Film filmAjouter = this.filmService.findOrInsert(film);
        seance.setFilm(filmAjouter);
        repository.save(seance);
    }

    public void addFilmById(Integer id, Integer idFilm){
        Film film = new Film();
        film.setId(idFilm);
        addFilm(id, film);
    }



    public void addSalle(Integer id, Salle salle){
        Seance seance = repository.findById(id).orElseThrow();
        Salle salleAjouter = this.salleService.findOrInsert(salle);
        seance.setSalle(salleAjouter);
        repository.save(seance);
    }


}
