package ma.map.tm.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.map.tm.dao.CasTestRepository;
import ma.map.tm.dao.ExecutionTestRepository;
import ma.map.tm.entities.CasTest;
import ma.map.tm.entities.ExecutionTest;
import ma.map.tm.entities.Utilisateur;
import ma.map.tm.services.ExecutionCasService;
import ma.map.tm.services.UtilisateurService;
import ma.map.tm.utils.Consts;

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

	@Override
	public List<ExecutionTest> listeExecutionsParUtilisateur(Utilisateur utilisateurcourant) {
		return executionTestRepository.getAllByUser(utilisateurcourant);
	}

	@Override
	public List<Integer> getStats(Utilisateur utilisateurCourant) {
		List<Integer> stats = new ArrayList<Integer>();
		List<CasTest> listeCasTest = casTestRepository.findAll();
		if (utilisateurCourant.getRole().getNom().equals("Testeur")) listeCasTest= casTestRepository.findByUtilisateurId(utilisateurCourant);
		List<ExecutionTest> listeExecutions = executionTestRepository.findAll();
		if (utilisateurCourant.getRole().getNom().equals("Testeur")) listeExecutions = listeExecutionsParUtilisateur(utilisateurCourant);
		int nombreCas = listeCasTest.size();
		int nombreExecutions = listeExecutions.size();
		int nombreExecutes = casTestRepository.findAllExecutes().size();
		if (utilisateurCourant.getRole().getNom().equals("Testeur")) nombreExecutes = casTestRepository.getExecutes(utilisateurCourant).size();
		
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

	

}
