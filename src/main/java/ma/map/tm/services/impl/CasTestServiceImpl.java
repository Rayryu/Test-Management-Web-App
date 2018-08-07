package ma.map.tm.services.impl;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.map.tm.dao.CasTestRepository;
import ma.map.tm.entities.CasTest;
import ma.map.tm.entities.ExecutionTest;
import ma.map.tm.entities.Scenario;
import ma.map.tm.entities.Utilisateur;
import ma.map.tm.services.CasTestService;
import ma.map.tm.utils.Consts;

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
	public Hashtable<String, Integer> getStatistiquesScenario(Scenario scenarioCourant) {
		
		Hashtable<String, Integer> executionStats = new Hashtable<String, Integer>();
		
		executionStats.put(Consts.RÉUSSI, casTestRepository.getStatistiqueByStatus(scenarioCourant, Consts.RÉUSSI));
		executionStats.put(Consts.ECHOUÉ, casTestRepository.getStatistiqueByStatus(scenarioCourant, Consts.ECHOUÉ));
		executionStats.put(Consts.BLOQUÉ, casTestRepository.getStatistiqueByStatus(scenarioCourant, Consts.BLOQUÉ));
		
		return executionStats;
	}


	

}
