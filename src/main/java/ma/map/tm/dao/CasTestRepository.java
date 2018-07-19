package ma.map.tm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.map.tm.entities.CasTest;

public interface CasTestRepository  extends JpaRepository<CasTest, Long>{
	@Query("SELECT c FROM CasTest c JOIN c.testeur t where t.id = :x")
	public List<CasTest> findByUtilisateurId(@Param("x")Long id_utilisateur);
	
	@Query("SELECT c FROM CasTest c JOIN c.scenarioParent s where s.id = :x")
	public List<CasTest> findByScenarioId(@Param("x")Long id_scenarioParent);
}
