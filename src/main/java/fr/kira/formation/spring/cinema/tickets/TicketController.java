package fr.kira.formation.spring.cinema.tickets;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tickets")
@CrossOrigin
public class TicketController {

        private final TicketService service;

        public TicketController(TicketService service) {
            this.service = service;
        }

        @PostMapping
        public Ticket save(@RequestBody Ticket entity) {
            return service.save(entity);
        }


        // Réserver des tickets pour une séance donnée
        @PostMapping("/tickets")
        public Ticket save(@RequestParam("seanceId") Long seanceId,
                           @RequestParam("capacite") Integer capacite,
                           @RequestBody Ticket entity) {
            return service.save(Math.toIntExact(seanceId), capacite, entity);
        }




        @GetMapping("{id}")
        public Ticket findById(@PathVariable Integer integer) {
            return service.findById(integer);
        }

        @DeleteMapping("{id}")
        public void deleteById(@PathVariable Integer integer) {
            service.deleteById(integer);
        }

        @GetMapping
        public Iterable<Ticket> findAll() {
            return service.findAll();
        }



        // afficher la liste des tickets réservés pour une séance donnée
        @GetMapping("/tickets")
        public String getReservedTickets(@RequestParam("seanceId") Long screeningId) {
            List<Ticket> tickets = service.getReservedTickets(screeningId);
            return tickets.toString();
        }

}
