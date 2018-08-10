package ma.map.tm.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ma.map.tm.entities.Utilisateur;
import ma.map.tm.services.CampagneService;
import ma.map.tm.services.CasTestService;
import ma.map.tm.services.ExecutionCasService;
import ma.map.tm.services.ProjetService;
import ma.map.tm.services.ScenarioService;
import ma.map.tm.services.UtilisateurService;
import ma.map.tm.entities.CasTest;
import ma.map.tm.entities.Projet;

@Controller
public class DashboardController {
	
	@Autowired
	private ProjetService projetService;
	@Autowired
	private ScenarioService scenarioService;
	@Autowired
	private CampagneService campagneService;
	@Autowired
	private CasTestService casTestService;
	@Autowired
	private ExecutionCasService executionCasTestService;
	@Autowired
	private UtilisateurService utilisateurService;
	
	
	@RequestMapping("/Dashboard")
	public String dashboard(Model model) {
		Utilisateur utilisateurCourant = utilisateurService.getLoggedInUser();
		
		int nombreProjets = projetService.listeProjetsParUtilisateur(utilisateurCourant).size();
		int nombreCampagnes = campagneService.listeCampagneTestParUtilisateur(utilisateurCourant).size();
		int nombreScenarios = scenarioService.listeScenarioTestParUtilisateur(utilisateurCourant).size();
		int nombreCasTest = casTestService.listeCasTestParUtilisateur(utilisateurCourant).size();
		
		List<Projet> listeTroisDerniersProjets = projetService.getTroisDerniersProjets(utilisateurCourant);
		List<Integer> listeStatsTroisDerniersProjets = projetService.getStatsTroisDerniersProjets(utilisateurCourant);
		List<Integer> listeStatsExecutions = executionCasTestService.getStats(utilisateurCourant);
		
		List<CasTest> listeTroisDernierCas = casTestService.getTroisDerniersCasTest(utilisateurCourant);
        		
		model.addAttribute("nombreProjets", nombreProjets);
		model.addAttribute("nombreCampagnes", nombreCampagnes);
		model.addAttribute("nombreScenarios", nombreScenarios);
		model.addAttribute("nombreCasTest", nombreCasTest);
		
		model.addAttribute("listeTroisDerniersProjets", listeTroisDerniersProjets);
		model.addAttribute("listeStatsTroisDerniersProjets", listeStatsTroisDerniersProjets);
		model.addAttribute("listeStatsExecutions", listeStatsExecutions);
		
		model.addAttribute("listeTroisDernierCas", listeTroisDernierCas);
		
		model.addAttribute("currentUser", utilisateurService.getLoggedInUser());
		
		return "Dashboard";
	}
}
