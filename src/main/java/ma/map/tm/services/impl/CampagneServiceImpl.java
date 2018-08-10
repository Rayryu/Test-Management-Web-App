package ma.map.tm.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.map.tm.dao.CampagneTestRepository;
import ma.map.tm.entities.CampagneTest;
import ma.map.tm.entities.Utilisateur;
import ma.map.tm.services.CampagneService;

@Service
public class CampagneServiceImpl implements CampagneService {
	
	@Autowired
	private CampagneTestRepository campagneTestRepository;

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


}
