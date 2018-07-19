package ma.map.tm.services;

import ma.map.tm.entities.Utilisateur;

import java.util.List;

import ma.map.tm.entities.Projet;

public interface UtilisateurService {
	public List<Projet> getlisteProjets(Utilisateur utilisateur);
	public Utilisateur getUserById(Long idUtilisateur);

}
