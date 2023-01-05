package fr.kira.formation.spring.cinema.salles;

import fr.kira.formation.spring.cinema.seances.Seance;
import fr.kira.formation.spring.cinema.seances.SeanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalleService {

    private final SalleRepository repository;
    private final SeanceRepository seanceRepository;


    public SalleService(SalleRepository repository, SeanceRepository seanceRepository) {
        this.repository = repository;
        this.seanceRepository = seanceRepository;
    }

    public Salle save(Salle entity) {
        return repository.save(entity);
    }

    public Salle findById(Integer integer) {
        return repository.findById(integer).orElseThrow();
    }

    public void deleteById(Integer integer) {
        repository.deleteById(integer);
    }

    public Iterable<Salle> findAll() {
        return repository.findAll();
    }


    public List<Salle> findByDate(String date){
        List<Seance> seances = seanceRepository.findByDate(LocalDate.parse(date));
        List<Salle> salles = new ArrayList<>();
        for (Seance seance: seances){
            if (seance.getNombrePlace() > 0){
                salles.add(seance.getSalle());
            }
        }
        return salles;
    }

    public Salle findOrInsert(Salle salle){
        return this.repository.findById(salle.getId()).orElseGet(()->this.repository.save(salle));
    }



}
