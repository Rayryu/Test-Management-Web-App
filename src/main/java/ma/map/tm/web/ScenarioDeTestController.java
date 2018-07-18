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
import ma.map.tm.entities.Projet;
import ma.map.tm.entities.Scenario;
import ma.map.tm.services.ScenarioService;
import ma.map.tm.services.CampagneService;
import ma.map.tm.services.ProjetService;

@Controller
public class ScenarioDeTestController {
	
	@Autowired
	private CampagneService campagneService;
	@Autowired
	private ScenarioService scenarioService;
	
	@RequestMapping(value = {"/MesScenarioDeTest/{id}", "/MesScenarioDeTest"})
	public String mesScenarioDeTest(Model model, @PathVariable("id") Optional<Long> idScenarioDeTest) {
		//à changer avec l'objet utilisateur connecté
		Utilisateur currentUser = new Utilisateur();
		currentUser.setId(1L);
		
		Scenario scenarioForInfos = ((idScenarioDeTest.isPresent()) ?
				scenarioService.getScenarioById(idScenarioDeTest.get()) :
					new Scenario("Cliquer sur une campagne de test pour afficher son nom", "Cliquer sur une campagne de test pour afficher sa description"));
		List<Scenario> listeScenarioDeTest = scenarioService.listeScenarioTestParUtilisateur(currentUser);
		List<CampagneTest> listeCampagnes = campagneService.listeCampagneTestParUtilisateur(currentUser);
		
		model.addAttribute("listeScenarios", listeScenarioDeTest);
		model.addAttribute("listeCampagnes", listeCampagnes);

		model.addAttribute("nouveauScenario", new Scenario());
		model.addAttribute("campagneParent", new CampagneTest());
		model.addAttribute("scenarioSelectionnee", scenarioForInfos);
		
		return "CasDeTests";
	}
	
	@RequestMapping(value="/MesScenarioDeTest/AjouterScenarioDeTest", method=RequestMethod.POST)
	public ModelAndView toSolveACertainProblem(Model model, Scenario nouveauScenario) {
		
		//Je ne sais pas pourquoi, mais lorsque je suis sur une page 
		//MesCampagnesDeTest/{id} et que j'envoie une requête POST pour ajouter une campagne de test,
		//le controleur ajouterCampagneDeTest me redirige vers /MesCampagnesDeTest/AjouterCampagneDeTest.
		//Pour détourner ce probleme, redirection.
		
		scenarioService.addScenario(nouveauScenario);
		
		return new ModelAndView("redirect:/MesScenarioDeTest/"+nouveauScenario.getId().toString());
	}
	
	
	
	@RequestMapping(value="/AjouterScenarioDeTest", method=RequestMethod.POST)
	public ModelAndView ajouterScenarioDeTest(Model model, Scenario nouveauScenario) {

		scenarioService.addScenario(nouveauScenario);
		
		return new ModelAndView("redirect:/MesScenarioDeTest/"+nouveauScenario.getId().toString());
	}
	
}
