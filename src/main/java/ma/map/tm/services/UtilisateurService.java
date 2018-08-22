package ma.map.tm.services;

import ma.map.tm.entities.Utilisateur;

import java.util.List;
import java.util.Optional;

import ma.map.tm.entities.Projet;

public interface UtilisateurService {
	public List<Projet> getlisteProjets(Utilisateur utilisateur);
	public Utilisateur getUserById(Long idUtilisateur);
	public Utilisateur getUserByEmail(String email);
	public Utilisateur getLoggedInUser();
	public List<Utilisateur> getAllUsers();
	public void addUtilisateur(Utilisateur nouvelUtilisateur);
	public Utilisateur getUserById(Optional<Long> idUtilisateur);
	public void modifierUtilisateur(Long idUtilisateur, Utilisateur utilisateurSelectionne);
	public void supprimerUtilisateur(Long idUtilisateur);
	void activerUtilisateur(Long idUtilisateur);

}
