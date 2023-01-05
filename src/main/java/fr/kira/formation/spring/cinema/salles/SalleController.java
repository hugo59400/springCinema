package fr.kira.formation.spring.cinema.salles;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("salles")
@CrossOrigin
public class SalleController {

    private final SalleService service;

    public SalleController(SalleService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Salle> findAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Salle findById(Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public Salle save(@RequestBody Salle salle) {
        return service.save(salle);
    }

    @DeleteMapping("{id}")
    public void deleteById(Integer id) {
        service.deleteById(id);
    }

    @GetMapping("salle/{date}")
    public List<Salle> findSalleDispo(String date){
        return service.findByDate(date);
    }


}
