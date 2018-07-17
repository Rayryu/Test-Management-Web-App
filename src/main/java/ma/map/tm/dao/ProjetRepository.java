package ma.map.tm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.map.tm.entities.Projet;


public interface ProjetRepository  extends JpaRepository<Projet, Long>{
	@Query("SELECT p FROM Projet p JOIN p.listeUtilisateurs l where l.id = :x")
	public List<Projet> findByUtilisateurId(@Param("x")Long id_utilisateur);

}
