package ma.map.tm.services;

import java.util.List;

import ma.map.tm.entities.Projet;
import ma.map.tm.entities.Utilisateur;

public interface ProjetService {
	public void addProjet(Projet p);
	public List<Projet> listeProjets();
	public List<Projet> listeProjetsParUtilisateur(Utilisateur u);
	public Projet getProjetById(Long id);
	public void supprimerProjet(Long id_projet);
	public void addProjet(Projet nouveauProjet, Utilisateur currentUser);
	
}
