package ma.map.tm.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ma.map.tm.entities.Utilisateur;
import ma.map.tm.services.CampagneService;
import ma.map.tm.services.CasTestService;
import ma.map.tm.services.ExecutionCasService;
import ma.map.tm.services.ProjetService;
import ma.map.tm.services.ScenarioService;
import ma.map.tm.services.UtilisateurService;
import ma.map.tm.entities.CampagneTest;
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
		List<Projet> listeProjets = projetService.listeProjetsParUtilisateur(utilisateurCourant);
		String nomProjetCourant = "Tout les projets";
		
		int nombreProjets = listeProjets.size();
		int nombreCampagnes = campagneService.listeCampagneTestParUtilisateur(utilisateurCourant).size();
		int nombreScenarios = scenarioService.listeScenarioTestParUtilisateur(utilisateurCourant).size();
		int nombreCasTest = casTestService.listeCasTestParUtilisateur(utilisateurCourant).size();
		
		List<Projet> listeTroisDerniersProjets = projetService.getTroisDerniersProjets(utilisateurCourant);
		List<Integer> listeStatsTroisDerniersProjets = projetService.getStatsTroisDerniersProjets(utilisateurCourant);
		List<Integer> listeStatsExecutions = executionCasTestService.getStats(utilisateurCourant);
		
		List<CasTest> listeTroisDernierCas = casTestService.getTroisDerniersCasTest(utilisateurCourant);
		
		model.addAttribute("listeProjets", listeProjets);
		model.addAttribute("nomProjetCourant", nomProjetCourant);
        		
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
	
	@RequestMapping("/Dashboard/{id}")
	public String dashboardProjet(Model model, @PathVariable("id") Long idProjet) {
		Utilisateur utilisateurCourant = utilisateurService.getLoggedInUser();
		List<Projet> listeProjets = projetService.listeProjetsParUtilisateur(utilisateurCourant);
		Projet projetCourant = projetService.getProjetById(idProjet);
		listeProjets.remove(projetCourant);
		
		int nombreCampagnes = campagneService.listeCampagnesByProjetId(projetCourant.getId()).size();
		int nombreScenarios = scenarioService.listeScenariosByProjet(projetCourant).size();
		int nombreCasTest = casTestService.listeCasTestByProjet(projetCourant).size();
		
		List<CampagneTest> listeTroisDernieresCampagnes = campagneService.getTroisDernieresCampagnes(projetCourant);
		List<Integer> listeStatsTroisDernieresCampagnes = campagneService.getStatsTroisDernieresCampagnes(projetCourant);
		
		List<Integer> listeStatsExecutions = campagneService.getStats(projetCourant);
		
		List<CasTest> listeTroisDernierCas = campagneService.getTroisDerniersCasTest(projetCourant);
		
		model.addAttribute("listeProjets", listeProjets);
		model.addAttribute("nomProjetCourant", projetCourant.getNom());
        		
		model.addAttribute("nombreCampagnes", nombreCampagnes);
		model.addAttribute("nombreScenarios", nombreScenarios);
		model.addAttribute("nombreCasTest", nombreCasTest);
		
		model.addAttribute("listeTroisDernieresCampagnes", listeTroisDernieresCampagnes);
		model.addAttribute("listeStatsTroisDernieresCampagnes", listeStatsTroisDernieresCampagnes);
		
		model.addAttribute("listeStatsExecutions", listeStatsExecutions);
		
		model.addAttribute("listeTroisDernierCas", listeTroisDernierCas);
		
		model.addAttribute("currentUser", utilisateurService.getLoggedInUser());
		
		return "DashboardProjet";
	}
}
