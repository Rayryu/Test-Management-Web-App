package ma.map.tm.services.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.standard.expression.Each;

import ma.map.tm.dao.ProjetRepository;
import ma.map.tm.dao.UtilisateurRepository;
import ma.map.tm.entities.Projet;
import ma.map.tm.entities.Utilisateur;
import ma.map.tm.services.ProjetService;
import ma.map.tm.services.UtilisateurService;
import ma.map.tm.utils.Consts;

@Service
public class ProjetServiceImpl implements ProjetService {
	
	final static Logger logger = Logger.getLogger(ProjetServiceImpl.class);

	@Autowired
	private ProjetRepository projetRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
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
		if (u.getRole().getNom().equals("Testeur")) return projetRepository.findByUtilisateurId(u.getId());
		return projetRepository.findAll();
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

	@Override
	public List<Projet> getTroisDerniersProjets(Utilisateur utilisateurCourant) {
		 List<Projet> listeProjetOrdonnee = projetRepository.findAllDateDesc();
		if (utilisateurCourant.getRole().getNom().equals("Testeur")) {
			listeProjetOrdonnee = projetRepository.findByUserCreationDesc(utilisateurCourant);
		}
		List<Projet> subList = new ArrayList<>();
		subList = listeProjetOrdonnee.subList(0, Math.min(listeProjetOrdonnee.size(), 3));
			Collections.reverse(subList);
		return subList;
	}

	@Override
	public List<Integer> getStatsTroisDerniersProjets(Utilisateur utilisateurCourant) {
		List<Integer> stats = new ArrayList<Integer>();
		List<Projet> projets = getTroisDerniersProjets(utilisateurCourant);
		for (Projet projet : projets) {
			int reussis = projetRepository.getNombreExecutionsParStatus(projet, Consts.RÉUSSI);
			int echoues = projetRepository.getNombreExecutionsParStatus(projet, Consts.ECHOUÉ);
			int bloques = projetRepository.getNombreExecutionsParStatus(projet, Consts.BLOQUÉ);
			stats.add(reussis);
			stats.add(echoues);
			stats.add(bloques);
		}
		return stats;
	}

	@Override
	public List<Utilisateur> getUtilisateurParProjet(Projet projetParent) {
		return projetRepository.getListUsers(projetParent);
	}

	@Override
	public void ajouterMembre(Long idProjet, Utilisateur membreTransitant) {
		
		Projet projetParent = projetRepository.getOne(idProjet);
		membreTransitant = utilisateurRepository.getOne(membreTransitant.getId());
		if(!projetParent.getListeUtilisateurs().contains(membreTransitant)) {
			membreTransitant = utilisateurRepository.getOne(membreTransitant.getId());
			projetParent.updateListeUtilisateurs(membreTransitant);
			projetRepository.save(projetParent);
			membreTransitant.updateListeProjets(projetParent);
			utilisateurRepository.save(membreTransitant);
		}

	}

	@Override
	public void retirerMembre(Long idProjet, Utilisateur membreTransitant) {
		Projet projetParent = projetRepository.getOne(idProjet);
		membreTransitant = utilisateurRepository.getOne(membreTransitant.getId());
		projetParent.updateListeUtilisateurs(membreTransitant,-1);
		projetRepository.save(projetParent);
		membreTransitant.updateListeProjets(projetParent, -1);
		utilisateurRepository.save(membreTransitant);
		
	}

	@Override
	public List<Utilisateur> getUtilisateurHorsProjet(Projet projetParent) {
		List<Utilisateur> listeUtilisateurs = utilisateurRepository.findAll();
		List<Utilisateur> listeDesUtilisateurs  = getUtilisateurParProjet(projetParent);
		List<Utilisateur> listeUtilisateursHorsProjet = new ArrayList<>();
		for (Utilisateur utilisateur : listeUtilisateurs) {
			if (!listeDesUtilisateurs.contains(utilisateur)) listeUtilisateursHorsProjet.add(utilisateur);
		}
		return listeUtilisateursHorsProjet;
	}




}
