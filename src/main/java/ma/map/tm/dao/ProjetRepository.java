package ma.map.tm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.map.tm.entities.Projet;
import ma.map.tm.entities.Utilisateur;


public interface ProjetRepository  extends JpaRepository<Projet, Long>{
	@Query("SELECT p FROM Projet p JOIN p.listeUtilisateurs l where l.id = :x ORDER BY p.dateCreation DESC")
	public List<Projet> findByUtilisateurId(@Param("x")Long id_utilisateur);
	

	@Query("SELECT p FROM Projet p JOIN p.listeUtilisateurs l where l = :x ORDER BY p.dateCreation DESC")
	public List<Projet> findByUserCreationDesc(@Param("x")Utilisateur utilisateurCourant);

	@Query("SELECT count(e) FROM ExecutionTest e WHERE e.casTestParent.scenarioParent.campagneParent.projetParent = :x and e.status = :y ")
	public int getNombreExecutionsParStatus(@Param("x") Projet projet, @Param("y") String status);

	@Query("SELECT p FROM Projet p ORDER BY p.dateCreation DESC")
	public List<Projet> findAllDateDesc();

	@Query("SELECT p.listeUtilisateurs FROM Projet p WHERE p = :x")
	public List<Utilisateur> getListUsers(@Param("x") Projet projetParent);


}
