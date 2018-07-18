package ma.map.tm.web;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ma.map.tm.entities.Utilisateur;
import ma.map.tm.dao.UtilisateurRepository;
import ma.map.tm.entities.CampagneTest;
import ma.map.tm.entities.Projet;
import ma.map.tm.services.CampagneService;
import ma.map.tm.services.ProjetService;

@Controller
public class CampagneDeTestController {
	
	@Autowired
	private CampagneService campagneService;
	@Autowired
	private ProjetService projetService;
	
	@RequestMapping("/MesCampagnesDeTest")
	public String mesCampagnesDeTest(Model model) {
		//à changer avec l'objet utilisateur connecté
		Utilisateur currentUser = new Utilisateur();
		currentUser.setId(1L);
		
		List<CampagneTest> listeCampagnesDeTest = campagneService.listeCampagneTestParUtilisateur(currentUser);
		List<Projet> listeProjets = projetService.listeProjetsParUtilisateur(currentUser);
		
		model.addAttribute("listeCampagnes", listeCampagnesDeTest);
		model.addAttribute("listeProjets", listeProjets);
		
		model.addAttribute("nouvelleCampagne", new CampagneTest());
		model.addAttribute("projetParent", new Projet());
		
		return "CampagneDeTest";
	}
	
	@RequestMapping(value="/AjouterCampagneDeTest", method=RequestMethod.POST)
	public ModelAndView ajouterCampagneDeTest(Model model, CampagneTest nouvelleCampagne) {

		//à changer avec l'objet utilisateur connecté
		Utilisateur utilisateurCourant = new Utilisateur();
		utilisateurCourant.setId(1L);
		//-----
		
		System.out.println("Nom" + nouvelleCampagne.getNom());
		System.out.println("Desc" + nouvelleCampagne.getDescription());
		
		System.out.println("Projet_name" + nouvelleCampagne.getProjetParent().getNom());
		
		nouvelleCampagne.setConcepteurTest(utilisateurCourant);
		
		campagneService.addCampagne(nouvelleCampagne);
		
		return new ModelAndView("redirect:/MesCampagnesDeTest");
	}
}
