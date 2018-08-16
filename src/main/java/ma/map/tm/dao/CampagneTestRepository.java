package ma.map.tm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.map.tm.entities.CampagneTest;
import ma.map.tm.entities.Projet;
import ma.map.tm.entities.Utilisateur;

public interface CampagneTestRepository  extends JpaRepository<CampagneTest, Long>{
	@Query("SELECT c FROM CampagneTest c JOIN c.concepteurTest t where t.id = :x")
	public List<CampagneTest> findByUtilisateurId(@Param("x")Long id_utilisateur);
	
	@Query("SELECT c FROM CampagneTest c JOIN c.projetParent t where t.id = :x")
	public List<CampagneTest> findByProjetId(@Param("x")Long id_projet);
	
	@Query("SELECT c FROM CampagneTest c WHERE c.projetParent= :x ORDER BY c.dateCreation DESC")
	public List<CampagneTest> getAllDesc(@Param("x")Projet projetCourant);
	
	@Query("SELECT count(e) FROM ExecutionTest e WHERE e.casTestParent.scenarioParent.campagneParent = :x and e.status = :y ")
	public int getNombreExecutionsParStatus(@Param("x") CampagneTest campagne, @Param("y") String status);


	
	

}
