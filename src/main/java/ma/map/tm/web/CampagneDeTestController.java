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
import ma.map.tm.services.CampagneService;
import ma.map.tm.services.ProjetService;

@Controller
public class CampagneDeTestController {
	
	@Autowired
	private CampagneService campagneService;
	@Autowired
	private ProjetService projetService;
	
	@RequestMapping(value = {"/MesCampagnesDeTest/{id}", "/MesCampagnesDeTest"})
	public String mesCampagnesDeTest(Model model, @PathVariable("id") Optional<Long> idCampagneDeTest) {
		//à changer avec l'objet utilisateur connecté
		Utilisateur currentUser = new Utilisateur();
		currentUser.setId(1L);
		
		CampagneTest campagneForInfos = ((idCampagneDeTest.isPresent()) ?
				campagneService.getCampagneById(idCampagneDeTest.get()) :
					new CampagneTest("Cliquer sur une campagne de test pour afficher son nom", "Cliquer sur une campagne de test pour afficher sa description"));
		List<CampagneTest> listeCampagnesDeTest = campagneService.listeCampagneTestParUtilisateur(currentUser);
		List<Projet> listeProjets = projetService.listeProjetsParUtilisateur(currentUser);
		
		model.addAttribute("listeCampagnes", listeCampagnesDeTest);
		model.addAttribute("listeProjets", listeProjets);

		model.addAttribute("nouvelleCampagne", new CampagneTest());
		model.addAttribute("projetParent", new Projet());
		model.addAttribute("campagneSelectionnee", campagneForInfos);
		
		return "CampagneDeTest";
	}
	
	@RequestMapping(value="/MesCampagnesDeTest/AjouterCampagneDeTest", method=RequestMethod.POST)
	public ModelAndView toSolveACertainProblem(Model model, CampagneTest nouvelleCampagne) {
		
		//Je ne sais pas pourquoi, mais lorsque je suis sur une page 
		//MesCampagnesDeTest/{id} et que j'envoie une requête POST pour ajouter une campagne de test,
		//le controleur ajouterCampagneDeTest me redirige vers /MesCampagnesDeTest/AjouterCampagneDeTest.
		//Pour détourner ce probleme, redirection.
		
		//à changer avec l'objet utilisateur connecté
		Utilisateur utilisateurCourant = new Utilisateur();
		utilisateurCourant.setId(1L);
		//-----x
		
		nouvelleCampagne.setConcepteurTest(utilisateurCourant);
		
		campagneService.addCampagne(nouvelleCampagne);
		
		return new ModelAndView("redirect:/MesCampagnesDeTest/"+nouvelleCampagne.getId().toString());
	}
	
	
	
	@RequestMapping(value="/AjouterCampagneDeTest", method=RequestMethod.POST)
	public ModelAndView ajouterCampagneDeTest(Model model, CampagneTest nouvelleCampagne) {

		//à changer avec l'objet utilisateur connecté
		Utilisateur utilisateurCourant = new Utilisateur();
		utilisateurCourant.setId(1L);
		//-----
		
		nouvelleCampagne.setConcepteurTest(utilisateurCourant);
		
		campagneService.addCampagne(nouvelleCampagne);
		
		return new ModelAndView("redirect:/MesCampagnesDeTest/"+nouvelleCampagne.getId().toString());
	}
	
}
