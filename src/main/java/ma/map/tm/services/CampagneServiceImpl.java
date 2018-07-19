package ma.map.tm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.map.tm.dao.CampagneTestRepository;
import ma.map.tm.entities.CampagneTest;
import ma.map.tm.entities.Scenario;
import ma.map.tm.entities.Utilisateur;

@Service
public class CampagneServiceImpl implements CampagneService {
	
	@Autowired
	private CampagneTestRepository campagneTestRepository;

	@Override
	public void addCampagne(CampagneTest c) {
		campagneTestRepository.save(c);
	}

	@Override
	public List<CampagneTest> listeCampagneDeTest() {
		return campagneTestRepository.findAll();
	}

	@Override
	public List<CampagneTest> listeCampagneTestParUtilisateur(Utilisateur u) {
		return campagneTestRepository.findByUtilisateurId(u.getId());
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


}
