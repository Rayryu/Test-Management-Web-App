package ma.map.tm.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class CasDeTestController {
	
	@Autowired
	private ScenarioService scenarioService;
	@Autowired
	private CasTestService casTestService;
	@Autowired
	private UtilisateurService utilisateurService;
	@Autowired
	private CampagneService campagneService;
	@Autowired
	private ProjetService projetService;
	
	
	@RequestMapping(value= "/Scenario/{id}")
	public String Scenario(Model model, @PathVariable("id") Long id_scenarioParent) {
		
		Scenario scenarioParent = scenarioService.getScenarioById(id_scenarioParent);
		List<CasTest> listeDesCas = casTestService.listeCasTestParScenario(scenarioParent);
		CampagneTest campagneParente = campagneService.getCampagneById(scenarioParent.getCampagne().getId());
		Projet projetParent  = projetService.getProjetById(campagneParente.getProjetParent().getId());
		
		// a revoir-----------------------------
		List<String> listetypeTest = new ArrayList<String>();
		listetypeTest.add("Test Fonctionnel");
		listetypeTest.add("Test Unitaire");
		listetypeTest.add("Test de Regression");
		
		List<String> listePriorite = new ArrayList<String>();
		listePriorite.add("Faible");
		listePriorite.add("Moyenne");
		listePriorite.add("Haute");
		
		model.addAttribute("listetypeTest", listetypeTest);
		model.addAttribute("listePriorite", listePriorite);
		//--------------------------------
		
		
		model.addAttribute("campagneParente", campagneParente);
		model.addAttribute("scenarioParent", scenarioParent);
		model.addAttribute("projetParent", projetParent);
		model.addAttribute("listeDesCas", listeDesCas);
		
		model.addAttribute("nouveauCasDeTest", new CasTest());
		model.addAttribute("nouveauScenario", new Scenario());
		
		
		
		
		return "Scenario";
	}
	
	
	
	@RequestMapping(value="/AjouterCasDeTest/{id}", method=RequestMethod.POST)
	public ModelAndView ajouterCasDeTest(Model model, CasTest nouveauCasDeTest, @PathVariable("id") Long id_scenarioParent) {
		
		//à changer avec l'objet utilisateur connecté
		Utilisateur currentUser = utilisateurService.getUserById(1L);
				
		
		nouveauCasDeTest.setTesteur(currentUser);
		nouveauCasDeTest.setPriorite("Moyenne");
		Scenario scenarioParent = scenarioService.getScenarioById(id_scenarioParent);
		nouveauCasDeTest.setScenario(scenarioParent);
		
		//
		nouveauCasDeTest.setId(null);
		casTestService.addCasDeTest(nouveauCasDeTest);

		return new ModelAndView("redirect:/Scenario/"+id_scenarioParent.toString());
	}
	
	@RequestMapping("/SelectedCasTest/{id}")
	public String selectedCasTest(Model model, @PathVariable("id") Long id_casTest) {
		
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
		
		model.addAttribute("listetypeTest", listetypeTest);
		model.addAttribute("listePriorite", listePriorite);
		//--------------------------------
		
		model.addAttribute("campagneParente", campagneParente);
		model.addAttribute("scenarioParent", scenarioParent);
		model.addAttribute("projetParent", projetParent);
		model.addAttribute("casTestCourant", casTestCourant);
		
		return "SelectedCasDeTests";
	}
	
	@RequestMapping("/ModifierCasDeTest/{id}")
	public ModelAndView modifierCasTest(@PathVariable("id") Long idCasTest, CasTest casTestCourant) {
		
		casTestService.modifierCasTest(casTestCourant, idCasTest);
		
		return new ModelAndView("redirect:/Scenario/"+casTestCourant.getScenario().getId().toString());
	}
	
	@RequestMapping("/SupprimerCasTest/{id}")
	public ModelAndView supprimerCasTest(@PathVariable("id") Long idCasTest) {
		CasTest casTestCourant = casTestService.getCasTestById(idCasTest);
		
		casTestService.supprimerCasTest(idCasTest);
		
		return new ModelAndView("redirect:/Scenario/"+casTestCourant.getScenario().getId().toString());
	}
	
}
