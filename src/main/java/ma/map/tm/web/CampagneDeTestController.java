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
import ma.map.tm.services.CampagneService;
import ma.map.tm.services.ProjetService;
import ma.map.tm.services.ScenarioService;

@Controller
public class CampagneDeTestController {
	
	@Autowired
	private ScenarioService scenarioService;
	@Autowired
	private CampagneService campagneService;
	@Autowired
	private ProjetService projetService;
	
	
	@RequestMapping(value = "/CampagnesDeTest")
	public String CampagnesDeTest(Model model) {
		//à changer avec l'objet utilisateur connecté
		Utilisateur currentUser = new Utilisateur();
		currentUser.setId(1L);
		
		List<CampagneTest> listeCampagnesDeTest = campagneService.listeCampagneTestParUtilisateur(currentUser);
		List<Projet> listeProjets = projetService.listeProjetsParUtilisateur(currentUser);
		
		model.addAttribute("listeCampagnes", listeCampagnesDeTest);
		model.addAttribute("listeProjets", listeProjets);

		model.addAttribute("nouvelleCampagne", new CampagneTest());
		model.addAttribute("projetParent", new Projet());
		
		return "CampagnesDeTest";
	}
	
	@RequestMapping("/CampagneDeTest/{id}")
	public String CampagneDeTest(Model model, @PathVariable("id") Long id) {
		
		//A modifier avec les informations de l'utilisateur connecté
		Utilisateur currentUser = new Utilisateur();
		currentUser.setId(1L);
		
		List<Scenario> listeScenarios = scenarioService.listeScenariosParCampagneId(id);
		Scenario nouveauScenario = new Scenario();
		CampagneTest campagneParente = campagneService.getCampagneById(id);
		nouveauScenario.setCampagne(campagneParente);
		Projet projetParent = projetService.getProjetById(campagneParente.getProjetParent().getId());
		
		model.addAttribute("listeScenarios", listeScenarios);
		model.addAttribute("nouveauScenario",nouveauScenario);
		model.addAttribute("campagneParente",campagneParente);
		model.addAttribute("projetParent",projetParent);
		
		return "CampagneDeTest";
	}
	
	
	
	
	@RequestMapping(value="/AjouterCampagneDeTest", method=RequestMethod.POST)
	public ModelAndView ajouterCampagneDeTest(Model model, CampagneTest nouvelleCampagne) {

		//à changer avec l'objet utilisateur connecté
		Utilisateur utilisateurCourant = new Utilisateur();
		utilisateurCourant.setId(1L);
		//-----
		
		nouvelleCampagne.setConcepteurTest(utilisateurCourant);
		
		campagneService.addCampagne(nouvelleCampagne);
		
		return new ModelAndView("redirect:/Projet/"+nouvelleCampagne.getProjetParent().getId().toString());
	}
	
	@RequestMapping(value="/AjouterCampagneDeTest/{id}", method=RequestMethod.POST)
	public ModelAndView ajouterCampagneDeTest(Model model, @PathVariable("id") Long id, CampagneTest nouvelleCampagne) {
		
		campagneService.modifierCampagne(nouvelleCampagne, id);
		
		return new ModelAndView("redirect:/CampagneDeTest/"+id.toString());
	}
	
	@RequestMapping(value="/SupprimerCampagne/{id}")
	public ModelAndView supprimerCampagneDeTest(@PathVariable("id") Long id_campagne) {
		
		campagneService.supprimerCampagne(id_campagne);
		
		return new ModelAndView("redirect:/CampagnesDeTest");
	}
	
	
	
}
