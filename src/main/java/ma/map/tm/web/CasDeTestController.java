package ma.map.tm.web;

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
		nouveauCasDeTest.setPriorite(1);
		Scenario scenarioParent = scenarioService.getScenarioById(id_scenarioParent);
		nouveauCasDeTest.setScenario(scenarioParent);
		
		//
		nouveauCasDeTest.setId(null);
		casTestService.addCasDeTest(nouveauCasDeTest);

		return new ModelAndView("redirect:/Scenario/"+id_scenarioParent.toString());
	}
	
}
