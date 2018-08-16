package ma.map.tm.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import ma.map.tm.entities.CasTest;
import ma.map.tm.entities.ExecutionTest;
import ma.map.tm.entities.Projet;
import ma.map.tm.entities.Utilisateur;

public interface ExecutionTestRepository extends JpaRepository<ExecutionTest, Long> {
	@Query("SELECT e FROM ExecutionTest e where e.casTestParent = :x and e.testeur = :y")
	Collection<ExecutionTest> getResultats(@Param("x") CasTest casTestCourant, @Param("y") Utilisateur loggedInUser);

	@Query("SELECT e FROM ExecutionTest e where e.testeur = :y")
	List<ExecutionTest> getAllByUser(@Param("y") Utilisateur utilisateurcourant);
	
	@Query("SELECT e FROM ExecutionTest e where e.casTestParent.scenarioParent.campagneParent.projetParent = :y")
	List<ExecutionTest> getAllByProjet(@Param("y") Projet projetCourant);

}
