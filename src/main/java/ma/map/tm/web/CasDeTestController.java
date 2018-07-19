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

import ma.map.tm.entities.CasTest;
import ma.map.tm.entities.Scenario;
import ma.map.tm.entities.Utilisateur;
import ma.map.tm.services.CasTestService;
import ma.map.tm.services.ScenarioService;
import ma.map.tm.services.UtilisateurService;

@Controller
public class CasDeTestController {
	
	@Autowired
	ScenarioService scenarioService;
	
	@Autowired
	CasTestService casTestService;
	
	@Autowired
	UtilisateurService utilisateurService;
	
	@RequestMapping(value= "/Scenario/{id}")
	public String Scenario(Model model, @PathVariable("id") Long id_scenarioParent) {
		
		Scenario scenarioParent = scenarioService.getScenarioById(id_scenarioParent);
		List<CasTest> listeDesCas = casTestService.listeCasTestParScenario(scenarioParent);
		
		model.addAttribute("scenarioParent", scenarioParent);
		model.addAttribute("listeDesCas", listeDesCas);
		model.addAttribute("nouveauCasDeTest", new CasTest());
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
