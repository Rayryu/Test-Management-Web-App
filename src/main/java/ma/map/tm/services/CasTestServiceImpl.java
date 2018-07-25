package ma.map.tm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.map.tm.dao.CasTestRepository;
import ma.map.tm.entities.CasTest;
import ma.map.tm.entities.Scenario;
import ma.map.tm.entities.Utilisateur;

@Service
public class CasTestServiceImpl implements CasTestService {

	@Autowired
	private CasTestRepository casTestRepository;

	@Override
	public void addCasDeTest(CasTest c) {
		casTestRepository.save(c);
		
	}

	@Override
	public List<CasTest> listeCasTest() {
		return casTestRepository.findAll();
	}

	@Override
	public List<CasTest> listeCasTestParUtilisateur(Utilisateur u) {
		return casTestRepository.findByUtilisateurId(u);
	}

	@Override
	public CasTest getCasTestById(Long id) {
		Optional<CasTest> ct =  casTestRepository.findById(id);
		if (ct.isPresent()) {
			return ct.get();
		} else {
			return new CasTest("Une erreur est survenue", "Une erreur est survenue");
		}
	}

	@Override
	public List<CasTest> listeCasTestParScenario(Scenario s) {
		
		return casTestRepository.findByScenarioId(s.getId());
	}

	@Override
	public void modifierCasTest(CasTest casTestCourant, Long idCasTest) {
		CasTest cas = casTestRepository.getOne(idCasTest);
		casTestCourant.setId(idCasTest);
		casTestCourant.setScenarioParent(cas.getScenario());
		casTestCourant.setTesteur(cas.getTesteur());
		
		
		casTestRepository.save(casTestCourant);
		
	}

	@Override
	public void supprimerCasTest(Long idCasTest) {
		
		casTestRepository.delete(getCasTestById(idCasTest));
		
	}
	

}
