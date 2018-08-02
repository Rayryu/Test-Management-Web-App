package ma.map.tm.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.map.tm.dao.ProjetRepository;
import ma.map.tm.entities.Projet;
import ma.map.tm.entities.Utilisateur;

@Service
public class ProjetServiceImpl implements ProjetService {

	@Autowired
	private ProjetRepository projetRepository;
	
	@Override
	public void addProjet(Projet p) {
		p.setDateCreation(new Date());
		projetRepository.save(p);
	}

	@Override
	public List<Projet> listeProjets() {
		return projetRepository.findAll();
	}

	@Override
	public List<Projet> listeProjetsParUtilisateur(Utilisateur u) {
		return projetRepository.findByUtilisateurId(u.getId());
	}

	@Override
	public Projet getProjetById(Long id) {
		return projetRepository.getOne(id);
	}

	@Override
	public void supprimerProjet(Long id_projet) {
		Projet projetCourant = getProjetById(id_projet);
		projetRepository.delete(projetCourant);
	}

	@Override
	public void addProjet(Projet nouveauProjet, Utilisateur currentUser) {
		//Pour garder la relation N-N !! 
		Collection<Projet> listeProjetUtilisateurCourant = listeProjetsParUtilisateur(currentUser);
		listeProjetUtilisateurCourant.add(nouveauProjet);
		currentUser.setListeProjets(listeProjetUtilisateurCourant);
		
		addProjet(nouveauProjet);
		
	}

}
