package ma.map.tm.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ma.map.tm.entities.CampagneTest;
import ma.map.tm.entities.CasTest;
import ma.map.tm.entities.Projet;
import ma.map.tm.entities.Scenario;
import ma.map.tm.entities.Utilisateur;
import ma.map.tm.services.CampagneService;
import ma.map.tm.services.CasTestService;
import ma.map.tm.services.ProjetService;
import ma.map.tm.services.ScenarioService;
import ma.map.tm.services.UtilisateurService;

@Controller
public class ExecutionController {
	
	@Autowired
	private ScenarioService scenarioService;
	@Autowired
	private CasTestService casTestService;
	@Autowired
	private CampagneService campagneService;
	@Autowired
	private ProjetService projetService;
	
	@RequestMapping("/Execution")
	public String executionMain(Model model) {
		
		//Utilisateur connecté
		Utilisateur utilisateurCourant = new Utilisateur();
		utilisateurCourant.setId(1L);
		
		List<CasTest> listeDesCas = casTestService.listeCasTestParUtilisateur(utilisateurCourant);
		
		model.addAttribute("listeDesCas", listeDesCas);
		return "Executions";
	}
	
	@RequestMapping("/ExecuterCasDeTest/{id}")
	public String executerCasDeTest(Model model, @PathVariable("id") Long id_casTest) {
		
		Scenario scenarioParent = scenarioService.getScenarioByCasTestId(id_casTest);
		CampagneTest campagneParente = campagneService.getCampagneById(scenarioParent.getCampagne().getId());
		Projet projetParent  = projetService.getProjetById(campagneParente.getProjetParent().getId());
		CasTest casTestCourant = casTestService.getCasTestById(id_casTest);
		casTestCourant.setScenario(scenarioParent);
		
		// a revoir-----------------------------
		List<String> listetypeTest = new ArrayList<String>();
		listetypeTest.add("Test Fonctionnel");
		listetypeTest.add("Test Unitaire");
		listetypeTest.add("Test de Regression");
		
		List<String> listePriorite = new ArrayList<String>();
		listePriorite.add("Faible");
		listePriorite.add("Moyenne");
		listePriorite.add("Haute");
		
		List<String> listeStatut = new ArrayList<String>();
		listeStatut.add("Réussi");
		listeStatut.add("Echoué");
		listeStatut.add("Bloqué");
		
		
		model.addAttribute("listetypeTest", listetypeTest);
		model.addAttribute("listePriorite", listePriorite);
		model.addAttribute("listeStatut", listeStatut);
		//--------------------------------
		
		model.addAttribute("campagneParente", campagneParente);
		model.addAttribute("scenarioParent", scenarioParent);
		model.addAttribute("projetParent", projetParent);
		model.addAttribute("casTestCourant", casTestCourant);
		
		return "ExecuterCasDeTest";
	}
	
	@RequestMapping("/AjouterResultat/{id}")
	public ModelAndView ajouterResultat(@PathVariable("id") Long idCasDeTest, CasTest casTestCourant) {
		
		casTestService.ajouterResultat(idCasDeTest, casTestCourant);
		
		return new ModelAndView("redirect:/Execution/");
		
	}
}
