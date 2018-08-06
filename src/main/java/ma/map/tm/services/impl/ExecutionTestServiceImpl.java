package ma.map.tm.services.impl;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.map.tm.dao.CasTestRepository;
import ma.map.tm.dao.ExecutionTestRepository;
import ma.map.tm.entities.CasTest;
import ma.map.tm.entities.ExecutionTest;
import ma.map.tm.entities.Utilisateur;
import ma.map.tm.services.ExecutionCasService;
import ma.map.tm.services.UtilisateurService;

@Service
public class ExecutionTestServiceImpl implements ExecutionCasService {
	
	@Autowired
	private CasTestRepository casTestRepository;
	@Autowired
	private ExecutionTestRepository executionTestRepository;
	@Autowired
	private UtilisateurService utilisateurService;

	@Override
	public void ajouterResultat(Long idCasDeTest, ExecutionTest nouvelleExecution) {
		CasTest casTestParent = casTestRepository.getOne(idCasDeTest);
		nouvelleExecution.setCasTestParent(casTestParent);
		nouvelleExecution.setTesteur(utilisateurService.getLoggedInUser());
		nouvelleExecution.setDateCreation(new Date());
		nouvelleExecution.setId(null);
		
		executionTestRepository.save(nouvelleExecution);
	}

	@Override
	public Collection<ExecutionTest> getResultatsPrecedents(CasTest casTestCourant, Utilisateur loggedInUser) {
		
		return executionTestRepository.getResultats(casTestCourant, loggedInUser);
	}

	

}
