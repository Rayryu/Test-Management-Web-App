package ma.map.tm.web;

import java.util.List;

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
import ma.map.tm.entities.Scenario;
import ma.map.tm.services.ScenarioService;
import ma.map.tm.services.UtilisateurService;
import ma.map.tm.services.CampagneService;
import ma.map.tm.services.CasTestService;
@Controller
public class ScenarioDeTestController {
	
	@Autowired
	private CampagneService campagneService;
	@Autowired
	private ScenarioService scenarioService;
	@Autowired
	private CasTestService casTestService;
	@Autowired
	private UtilisateurService utilisateurService;
	
	@RequestMapping(value = "/Scenarios")
	public String Scenarios(Model model) {

		Utilisateur currentUser = utilisateurService.getLoggedInUser();
		
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
		
		return new ModelAndView("redirect:/CampagneDeTest/"+nouveauScenario.getCampagneParent().getId());
	}
	
	@RequestMapping(value="/ModifierScenarioDeTest/{id}", method=RequestMethod.POST)
	public ModelAndView modifierScenarioDeTest(Model model, Scenario nouveauScenario, @PathVariable("id") Long id_scenarioParent ) {
		
		scenarioService.modifierScenario(nouveauScenario, id_scenarioParent);
		
		return new ModelAndView("redirect:/Scenario/"+nouveauScenario.getId().toString());
	}
	
	@RequestMapping(value="/SupprimerScenario/{id}")
	public ModelAndView supprimerScenarioDeTest(@PathVariable("id") Long id_scenarioParent ) {
		
		scenarioService.supprimerScenario(id_scenarioParent);
		
		return new ModelAndView("redirect:/Scenarios");
	}
	
	
	
}
