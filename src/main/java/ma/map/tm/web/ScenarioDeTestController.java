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

import ma.map.tm.entities.Utilisateur;
import ma.map.tm.entities.CampagneTest;
import ma.map.tm.entities.CasTest;
import ma.map.tm.entities.Projet;
import ma.map.tm.entities.Scenario;
import ma.map.tm.services.ScenarioService;
import ma.map.tm.services.CampagneService;
import ma.map.tm.services.CasTestService;
import ma.map.tm.services.ProjetService;

@Controller
public class ScenarioDeTestController {
	
	@Autowired
	private CampagneService campagneService;
	@Autowired
	private ScenarioService scenarioService;
	@Autowired
	private CasTestService casTestService;
	
	@RequestMapping(value = "/Scenarios")
	public String Scenarios(Model model) {
		//à changer avec l'objet utilisateur connecté
		Utilisateur currentUser = new Utilisateur();
		currentUser.setId(1L);
		
		List<Scenario> listeScenarioDeTest = scenarioService.listeScenarioTestParUtilisateur(currentUser);
		List<CasTest> listeCasTest = casTestService.listeCasTestParUtilisateur(currentUser);
		List<CampagneTest> listeCampagnes = campagneService.listeCampagneTestParUtilisateur(currentUser);
		
		model.addAttribute("listeScenarios", listeScenarioDeTest);
		model.addAttribute("listeCampagnes", listeCampagnes);
		model.addAttribute("listeCasTest", listeCasTest);
		model.addAttribute("nouveauScenario", new Scenario());
		
		return "Scenarios";
	}
	
	@RequestMapping(value="/AjouterScenarioDeTest", method=RequestMethod.POST)
	public ModelAndView ajouterScenarioDeTest(Model model, Scenario nouveauScenario) {

		scenarioService.addScenario(nouveauScenario);
		
		return new ModelAndView("redirect:/Scenario/"+nouveauScenario.getId().toString());
	}
	
	
	
}
