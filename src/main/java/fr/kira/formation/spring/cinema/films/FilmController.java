package fr.kira.formation.spring.cinema.films;

import fr.kira.formation.spring.cinema.acteurs.Acteur;
import fr.kira.formation.spring.cinema.films.dto.FilmCompletDto;
import fr.kira.formation.spring.cinema.films.dto.FilmReduitDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("films")
@CrossOrigin // evite les problèmes les CORS
public class FilmController {

    private final FilmService service;


    public FilmController(FilmService service) {
        this.service = service;
    }

    /**
     * <h2>Sauvegarde ou remplace un {@link Film} dans la base de données.</h2>
     * <ul>
     *     <li>
     *         Si le film posséde aucun id, alors sauvegarde le film et lui donne un id.
     *     </li>
     *     <li>
     *         Sinon remplace le film portant l'id dans la base de données.
     *     </li>
     * </ul>
     * <p>
     *     <b>Attention!</b> Pour remplacer un film, il est préférable de passer par la méthode {@link #update(Film)}.
     * </p>
     * @param film a sauvegarder
     * @return film sauvegarder.
     */
    @PostMapping
    public FilmCompletDto save(@RequestBody Film film) {
        return service.save(film);
    }

    /**
     * <h2>Retourne la liste de tous les films.</h2>
     *
     * @return la liste de tous les films.
     */
    @GetMapping
    public List<FilmReduitDto> findAll() {
        var res =  service.findAll();
        return res;
    }

    /**
     * <h2>Retourne le film correspondant à l'id.</h2>
     *
     * @param id l'id du film à retourner.
     * @return le film correspondant à l'id.
     */
    @GetMapping("{id}")
    public FilmCompletDto findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    /**
     * <h2>Supprime le film correspondant à l'id.</h2>
     *
     * @param id l'id du film à supprimer.
     */
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
    }

    /**
     * <h2>Sauvegarde ou remplace un {@link Film} dans la base de données.</h2>
     * <ul>
     *     <li>
     *         Si le film posséde aucun id, alors sauvegarde le film et lui donne un id.
     *     </li>
     *     <li>
     *         Sinon remplace le film portant l'id dans la base de données.
     *     </li>
     * </ul>
     * @param film a sauvegarder
     * @return film sauvegarder.
     */
    @PutMapping
    public FilmCompletDto update(@RequestBody Film film){
        return this.service.save(film);
    }

    @GetMapping("titre/{titre}")
    public List<FilmReduitDto> findByTitre(@PathVariable String titre){
        return this.service.findByTitreContaining(titre);
    }

    /**
     * <h2>Ajoute un acteur a un film en utilisant l'id de l'acteur et l'id du film.</h2>
     * <p>C'est la version la qui utilise l'id de l'acteur. Vous pouvez aussi utiliser l'acteur au complet avec la methode
     * {@link FilmController#addActeur(Integer, Acteur)}</p>
     *
     * <p>
     *     Pour utiliser cette methode, vous devez au préalable avoir ajouté un acteur avec la methode
     *      {@link fr.kira.formation.spring.cinema.acteurs.ActeurController#save(Acteur)}
     * </p>
     *
     * <p>Pour appeler cette methode, vous devez utiliser une requete HTTP de type POST sur l'url suivante:
     *    <code>/films/{idFilm}/acteurs/{idActeur}</code>
     * </p>
     *
     * @param id du film
     * @param idActeur id de l'acteur
     *
     */
    @PostMapping("{id}/acteurs/{idActeur}")
    public void addActeurById(@PathVariable Integer id, @PathVariable Integer idActeur){
        this.service.addActeurById(id, idActeur);
    }
    // Ou
    /**
     * <h2>Ajoute un acteur a un film en utilisant l'acteur et l'id du film.</h2>
     * <p>C'est la version la qui utilise l'acteur au complet. Vous pouvez aussi utiliser l'id de l'acteur avec la methode
     *    {@link FilmController#addActeurById(Integer, Integer)}
     * </p>
     *
     * <p>
     *     Pour utiliser cette methode, vous devez au préalable avoir ajouté un acteur avec la methode
     *     {@link fr.kira.formation.spring.cinema.acteurs.ActeurController#save(Acteur)}
     * </p>
     * <p>
     *     Pour appeler cette methode, vous devez utiliser une requete HTTP de type POST sur l'url suivante:
     *     <code>/films/{idFilm}/acteurs</code>
     * </p>
     * @param id du film ou ajouter l'acteur
     * @param acteur a ajouter
     */
    @PostMapping("{id}/acteurs")
    public void addActeur(@PathVariable Integer id, @RequestBody Acteur acteur){
        this.service.addActeur(id, acteur);
    }

    /**
     * <h2>Supprime un acteur d'un film en utilisant l'id de l'acteur et l'id du film.</h2>
     * <p>
     *     Pour appeler cette methode, vous devez utiliser une requete HTTP de type DELETE sur l'url suivante:
     *     <code>/films/{idFilm}/acteurs/{idActeur}</code>
     * </p>
     * @param id du film
     * @param idActeur id de l'acteur
     */
    @DeleteMapping("{id}/acteurs/{idActeur}")
    public void deleteActeur(@PathVariable Integer id, @PathVariable Integer idActeur){
        this.service.deleteActeurById(id, idActeur);
    }

    /**
     * <h2>Ajoute un {@link fr.kira.formation.spring.cinema.realisateurs.Realisateur} d'un {@link Film} en fonction de leurs ids</h2>
     * @param id du film
     * @param idRealisateur du realisateur
     */
    @PostMapping("{id}/realisateurs/{idRealisateur}")
    public void addRealisateur(@PathVariable Integer id, @PathVariable Integer idRealisateur){
        this.service.addRealisateurById(id, idRealisateur);
    }

    /**
     * <h2>Supprime un {@link fr.kira.formation.spring.cinema.realisateurs.Realisateur} d'un {@link Film} en fonction de leurs ids</h2>
     * @param id du film
     * @param idRealisateur du realisateur
     */
    @DeleteMapping("{id}/realisateurs/{idRealisateur}")
    public void deleteRealisateur(@PathVariable Integer id, @PathVariable Integer idRealisateur){
        this.service.deleteRealisateurById(id, idRealisateur);
    }

}
