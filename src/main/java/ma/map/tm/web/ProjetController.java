package ma.map.tm.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ma.map.tm.entities.CampagneTest;
import ma.map.tm.entities.Projet;
import ma.map.tm.entities.Utilisateur;
import ma.map.tm.services.CampagneService;
import ma.map.tm.services.ProjetService;
import ma.map.tm.services.UtilisateurService;

@Controller
public class ProjetController {
	
	@Autowired
	private ProjetService projetService;
	@Autowired
	private CampagneService campagneService;
	@Autowired
	private UtilisateurService utilisateurService;
	
	@RequestMapping(value="/Projets")
	public String Projets(Model model) {

		Utilisateur currentUser = utilisateurService.getLoggedInUser();
		
		List<Projet> listeProjets = projetService.listeProjetsParUtilisateur(currentUser);
		
		model.addAttribute("listeProjets", listeProjets);
		model.addAttribute("nouveauProjet", new Projet());
		model.addAttribute("currentUser", utilisateurService.getLoggedInUser());
		
		return "Projets";
	}
	
	@RequestMapping("/Projet/{id}")
	public String Projet(Model model, @PathVariable("id") Long id) {
		
		List<CampagneTest> listeCampagnes = campagneService.listeCampagnesByProjetId(id);
		Projet projetParent = projetService.getProjetById(id);
		CampagneTest nouvelleCampagne = new CampagneTest();
		nouvelleCampagne.setProjetParent(projetParent);
		
		model.addAttribute("listeCampagnes", listeCampagnes);
		model.addAttribute("projetParent", projetParent);
		model.addAttribute("nouvelleCampagne", nouvelleCampagne);
		model.addAttribute("nouveauProjet", new Projet());
		model.addAttribute("currentUser", utilisateurService.getLoggedInUser());
		
		
		
		return "Projet";
	}
	
	@RequestMapping(value="/EnregistrerProjet", method=RequestMethod.POST)
	public ModelAndView enregistrerProjet(Projet nouveauProjet, Model model) {
		
		Utilisateur currentUser = utilisateurService.getLoggedInUser();
		
		projetService.addProjet(nouveauProjet, currentUser);
		
		//utilisateurRepository.save(utilisateurCourant);
		
		return new ModelAndView("redirect:/Projets/");
	}
	
	@RequestMapping(value="/EnregistrerProjet/{id}", method=RequestMethod.POST)
	public ModelAndView modifierProjet(Projet projetParent, Model model, @PathVariable("id") Long id) {
		
		projetParent.setId(id);
		projetService.addProjet(projetParent);
		
		return new ModelAndView("redirect:/Projet/"+projetParent.getId().toString());
	}
	
	@RequestMapping(value="/SupprimerProjet/{id}")
	public ModelAndView SupprimerProjet(Model model, @PathVariable("id") Long id_projet) {
		
		projetService.supprimerProjet(id_projet);
		
		return new ModelAndView("redirect:/Projets");
	}
}
