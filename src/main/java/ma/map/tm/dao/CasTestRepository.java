package ma.map.tm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.map.tm.entities.CasTest;
import ma.map.tm.entities.Scenario;
import ma.map.tm.entities.Utilisateur;

public interface CasTestRepository  extends JpaRepository<CasTest, Long>{
	@Query("SELECT c FROM CasTest c JOIN c.testeur t where t = :x")
	public List<CasTest> findByUtilisateurId(@Param("x")Utilisateur testeur);
	
	@Query("SELECT c FROM CasTest c JOIN c.scenarioParent s where s.id = :x")
	public List<CasTest> findByScenarioId(@Param("x")Long id_scenarioParent);

	@Query("SELECT count(e) FROM ExecutionTest e where e.status = :y and e.casTestParent.scenarioParent = :x")
	public Integer getStatistiqueByStatus(@Param("x")Scenario scenarioCourant,@Param("y") String status);

	@Query("SELECT distinct e.casTestParent FROM ExecutionTest e where e.casTestParent.testeur = :x")
	public List<CasTest> getExecutes(@Param("x")Utilisateur u);

	@Query("SELECT c FROM CasTest c where c.testeur = :x ORDER BY c.dateCreation DESC")
	public List<CasTest> findByUserCreationDesc(@Param("x")Utilisateur utilisateurCourant);

	@Query("SELECT c FROM CasTest c ORDER BY c.dateCreation DESC")
	public List<CasTest> findAllDateDesc();

}
