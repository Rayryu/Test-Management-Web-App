package ma.map.tm.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.map.tm.dao.CasTestRepository;
import ma.map.tm.entities.CasTest;
import ma.map.tm.entities.Scenario;
import ma.map.tm.entities.Utilisateur;
import ma.map.tm.services.CasTestService;

@Service
public class CasTestServiceImpl implements CasTestService {

	@Autowired
	private CasTestRepository casTestRepository;

	@Override
	public void addCasDeTest(CasTest c) {
		c.setDateCreation(new Date());
		c.setId(null);
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
		if (ct.isPresent()) return ct.get();
		else return new CasTest();
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

	@Override
	public void ajouterResultat(Long idCasDeTest, CasTest casTestCourant) {
		CasTest cas = casTestRepository.getOne(idCasDeTest);
		cas.setCommentaire(casTestCourant.getCommentaire());
		cas.setStatut(casTestCourant.getStatut());
		cas.setResultatActuel(casTestCourant.getResultatActuel());
		
		casTestRepository.save(cas);
		
	}
	

}
