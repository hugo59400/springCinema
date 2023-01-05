package fr.kira.formation.spring.cinema.seances;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "seances", path = "seances")
public interface SeanceRepository extends JpaRepository<Seance, Integer> {
    //Afficher la liste des salles disponibles à une date et à une heure donnée
    List<Seance> findByDateAndTime(String date, String time);


}
