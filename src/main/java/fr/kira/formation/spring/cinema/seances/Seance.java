package fr.kira.formation.spring.cinema.seances;

import fr.kira.formation.spring.cinema.films.Film;
import fr.kira.formation.spring.cinema.salles.Salle;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "sceances")
@Getter
@Setter
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    private LocalDateTime date;

    private int nombrePlace;

    private float prix;

    /**
     * @ManyToOne Specifie que la relation est de type ManyToOne
     * Un film peut avoir plusieurs sceances
     * Une sceance ne peut avoir qu'un seul
     *
     * @JoinColumn(name = "film_id") Specifie le nom de la colonne qui va contenir la clé étrangère
     */
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;


    @ManyToOne
    @JoinColumn(name = "salle_id")
    private Salle salle;
}
