package ma.map.tm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.map.tm.entities.Utilisateur;

public interface UtilisateurRepository  extends JpaRepository<Utilisateur, Long>{

}
