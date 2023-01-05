package fr.kira.formation.spring.cinema.films;

// jakarta == JEE
// jakarta.persistence == JPA

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.kira.formation.spring.cinema.acteurs.Acteur;
import fr.kira.formation.spring.cinema.realisateurs.Realisateur;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Ici on utilise la norme JEE JPA implémenté par l'ORM hibernate
 * JEE => ensemble des normes Java pour les applications d'entreprises
 * JPA => norme JEE pour la gestion de la persistance des données
 * Hibernate => Implémentation la plus courante de JPA pour faire un ORM
 */
// ORM = Object Relational Mapping ici hibernate
@Entity // Definie que la classe représente une entité dans la base de données
@Table(name="films") // perme de definir dans quel table se trouve cette entité
@Getter // Ajoute un getter pour chaque attribut
@Setter // Ajoute un setter pour chaque attribut
@NoArgsConstructor // Ajoute un constructeur sans params
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Film {

    @Id // Define que le champ représente la clé primaire de la table
    @GeneratedValue(strategy = GenerationType.AUTO) // permet de générer l'id automatiquement
    private Integer id;

    @Column(name = "titre", nullable = false)
    private String titre;

    @Column(name="date_sortie")
        private LocalDate dateSortie;

    private int duree;

    @Column(length = 500)
    private String resume;

    // Attention!! ManyToMany bidirectionnel ne peut pas être serialisé en JSON
    //@JsonManagedReference // Permet de ne pas avoir de boucle infinie lors de la sérialisation en JSON
    @ManyToMany(mappedBy = "films", cascade = {CascadeType.ALL, CascadeType.PERSIST}) // fais référence à l'attribut films de la classe Acteur
    private List<Acteur> acteurs = new ArrayList<>();

    @ManyToMany(mappedBy = "films")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private List<Realisateur> realisateurs = new ArrayList<>();

}
