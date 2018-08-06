package ma.map.tm.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import ma.map.tm.entities.CasTest;
import ma.map.tm.entities.ExecutionTest;
import ma.map.tm.entities.Utilisateur;

public interface ExecutionTestRepository extends JpaRepository<ExecutionTest, Long> {
	@Query("SELECT e FROM ExecutionTest e where e.casTestParent = :x and e.testeur = :y")
	Collection<ExecutionTest> getResultats(@Param("x") CasTest casTestCourant, @Param("y") Utilisateur loggedInUser);

}
