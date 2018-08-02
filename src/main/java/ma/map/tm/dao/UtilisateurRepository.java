package ma.map.tm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.map.tm.entities.Utilisateur;

public interface UtilisateurRepository  extends JpaRepository<Utilisateur, Long>{
	@Query("SELECT u FROM Utilisateur u where u.email = :x")
	public Utilisateur findByUserEmail(@Param("x")String email);
}
