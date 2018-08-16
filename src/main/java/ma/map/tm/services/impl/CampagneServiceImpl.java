package ma.map.tm.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.map.tm.dao.CampagneTestRepository;
import ma.map.tm.dao.CasTestRepository;
import ma.map.tm.dao.ExecutionTestRepository;
import ma.map.tm.entities.CampagneTest;
import ma.map.tm.entities.CasTest;
import ma.map.tm.entities.ExecutionTest;
import ma.map.tm.entities.Projet;
import ma.map.tm.entities.Utilisateur;
import ma.map.tm.services.CampagneService;
import ma.map.tm.services.ExecutionCasService;
import ma.map.tm.utils.Consts;

@Service
public class CampagneServiceImpl implements CampagneService {
	
	@Autowired
	private CampagneTestRepository campagneTestRepository;
	@Autowired
	private CasTestRepository casTestRepository;
	@Autowired
	private ExecutionTestRepository executionTestRepository;
	
	@Override
	public void addCampagne(CampagneTest c) {
		c.setDateCreation(new Date());
		campagneTestRepository.save(c);
	}

	@Override
	public List<CampagneTest> listeCampagneDeTest() {
		return campagneTestRepository.findAll();
	}

	@Override
	public List<CampagneTest> listeCampagneTestParUtilisateur(Utilisateur u) {
		if (u.getRole().getNom().equals("Testeur")) return campagneTestRepository.findByUtilisateurId(u.getId());
		return campagneTestRepository.findAll();
	}

	@Override
	public CampagneTest getCampagneById(Long id) {
		Optional<CampagneTest> ct =  campagneTestRepository.findById(id);
		if (ct.isPresent()) {
			return ct.get();
		} else {
			return new CampagneTest("Une erreur est survenue", "Une erreur est survenue");
		}
	}

	@Override
	public List<CampagneTest> listeCampagnesByProjetId(Long id) {
		return campagneTestRepository.findByProjetId(id);
	}

	@Override
	public void modifierCampagne(CampagneTest nouvelleCampagne, Long idCampagneParente) {
		CampagneTest campagneParente = getCampagneById(idCampagneParente);
		nouvelleCampagne.setId(campagneParente.getId());
		nouvelleCampagne.setConcepteurTest(campagneParente.getConcepteurTest());
		nouvelleCampagne.setListeScenario(campagneParente.getListeScenario());
		nouvelleCampagne.setProjetParent(campagneParente.getProjetParent());
		
		addCampagne(nouvelleCampagne);
	}

	@Override
	public void supprimerCampagne(Long id_campagne) {
		campagneTestRepository.delete(getCampagneById(id_campagne));
	}

	@Override
	public List<CampagneTest> getTroisDernieresCampagnes(Projet projetCourant) {
		List<CampagneTest> listeCampagnes = campagneTestRepository.getAllDesc(projetCourant);
		List<CampagneTest> subList = new ArrayList<>();
		if (listeCampagnes.size() > 2) subList = listeCampagnes.subList(0, 3);
		else subList = listeCampagnes.subList(0, listeCampagnes.size());
		Collections.reverse(subList);
		return subList;
	}
	
	@Override
	public List<Integer> getStatsTroisDernieresCampagnes(Projet projetCourant) {
		List<Integer> stats = new ArrayList<Integer>();
		List<CampagneTest> CampagnesTest = getTroisDernieresCampagnes(projetCourant);
		for (CampagneTest campagne : CampagnesTest) {
			int reussis = campagneTestRepository.getNombreExecutionsParStatus(campagne, Consts.RÉUSSI);
			int echoues = campagneTestRepository.getNombreExecutionsParStatus(campagne, Consts.ECHOUÉ);
			int bloques = campagneTestRepository.getNombreExecutionsParStatus(campagne, Consts.BLOQUÉ);
			stats.add(reussis);
			stats.add(echoues);
			stats.add(bloques);
		}
		return stats;
	}
	
	@Override
	public List<CasTest> listeCasParProjet(Projet projetCourant) {
		return casTestRepository.findByProjet(projetCourant);
	}

	@Override
	public List<Integer> getStats(Projet projetCourant) {
		List<Integer> stats = new ArrayList<Integer>();
		List<CasTest> listeCasTest = listeCasParProjet(projetCourant);
		
		List<ExecutionTest> listeExecutions = executionTestRepository.getAllByProjet(projetCourant);
		
		int nombreCas = listeCasTest.size();
		int nombreExecutions = listeExecutions.size();
		int nombreExecutes = casTestRepository.findAllExecutes(projetCourant).size();
		
		int nombreReussis = 0;
		int nombreBloques = 0;
		int nombreEchoues = 0;
		for (ExecutionTest executionTest : listeExecutions) {
			switch (executionTest.getStatus()) {
			case Consts.RÉUSSI: nombreReussis++; break;
			case Consts.ECHOUÉ: nombreEchoues++; break;
			case Consts.BLOQUÉ: nombreBloques++; break;
			default: break;
			}
		}

		stats.add(nombreCas);
		stats.add(nombreExecutes);
		if (nombreExecutions>0) {
			stats.add(nombreReussis*100/nombreExecutions);
			stats.add(nombreBloques*100/nombreExecutions);
			stats.add(nombreEchoues*100/nombreExecutions);
		} else {
			stats.add(0);
			stats.add(0);
			stats.add(0);
		}

		stats.add(nombreExecutions);
		return stats;
	}

	@Override
	public List<CasTest> getTroisDerniersCasTest(Projet projetCourant) {
		return casTestRepository.findByProjet(projetCourant).subList(0, Math.min(3, casTestRepository.findByProjet(projetCourant).size()));
	}

}
