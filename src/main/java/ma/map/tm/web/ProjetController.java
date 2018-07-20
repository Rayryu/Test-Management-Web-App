package ma.map.tm.web;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ma.map.tm.dao.UtilisateurRepository;
import ma.map.tm.entities.CampagneTest;
import ma.map.tm.entities.Projet;
import ma.map.tm.entities.Utilisateur;
import ma.map.tm.services.CampagneService;
import ma.map.tm.services.ProjetService;

@Controller
public class ProjetController {
	
	@Autowired
	private ProjetService projetService;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private CampagneService campagneService;
	
	@RequestMapping(value="/Projets")
	public String Projets(Model model) {
		//à changer avec l'objet utilisateur connecté
		Utilisateur user = new Utilisateur();
		user.setId(1L);
		
		List<Projet> listeProjets = projetService.listeProjetsParUtilisateur(user);
		model.addAttribute("listeProjets", listeProjets);
		model.addAttribute("nouveauProjet", new Projet());
		
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
		
		return "Projet";
	}
	
	
	
	@RequestMapping(value="/EnregistrerProjet", method=RequestMethod.POST)
	public ModelAndView EnregistrerProjet(Projet nouveauProjet, Model model) {
		
		//à changer avec l'objet utilisateur connecté
		Utilisateur utilisateurCourant = new Utilisateur();
		utilisateurCourant.setId(1L);
		//-----
		
		//Pour garder la relation N-N !! 
		Collection<Projet> listeProjetUtilisateurCourant = projetService.listeProjetsParUtilisateur(utilisateurCourant);
		listeProjetUtilisateurCourant.add(nouveauProjet);
		utilisateurCourant.setListeProjets(listeProjetUtilisateurCourant);
		//à changer avec une methode de la partie metier !
		
		
		projetService.addProjet(nouveauProjet);
		
		utilisateurRepository.save(utilisateurCourant);
		
		return new ModelAndView("redirect:/Projet/"+nouveauProjet.getId().toString());
	}
}
