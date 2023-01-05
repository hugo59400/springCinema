package fr.kira.formation.spring.cinema.seances;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SeanceService {

    private  final SeanceRepository repository;


    public SeanceService(SeanceRepository repository) {
        this.repository = repository;
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
    public  List<Seance> getAvailableScreenings(String date, String time) {
        return repository.findByDateAndTime(date, time);
    }




}
