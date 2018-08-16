package ma.map.tm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.map.tm.entities.Projet;
import ma.map.tm.entities.Scenario;

public interface ScenarioRepository extends JpaRepository<Scenario, Long> {
	@Query("SELECT s FROM Scenario s JOIN s.campagneParent c where c.concepteurTest.id = :x")
	public List<Scenario> findByScenarioId(@Param("x")Long id_utilisateur);
	
	@Query("SELECT s FROM Scenario s JOIN s.campagneParent c where c.id = :x")
	public List<Scenario> findByCampagneId(@Param("x")Long id_campagne);

	@Query("SELECT c.scenarioParent FROM CasTest c where c.id = :x")
	public Scenario findByCasId(@Param("x")Long id_cas);

	@Query("SELECT s FROM Scenario s where s.campagneParent.projetParent = :x")
	public List<Scenario> findByProject(@Param("x")Projet projetCourant);

}
