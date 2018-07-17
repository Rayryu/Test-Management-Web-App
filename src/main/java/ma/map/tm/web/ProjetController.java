package ma.map.tm.web;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ma.map.tm.dao.UtilisateurRepository;
import ma.map.tm.entities.Projet;
import ma.map.tm.entities.Utilisateur;
import ma.map.tm.services.ProjetService;
import ma.map.tm.services.UtilisateurService;

@Controller
public class ProjetController {
	
	@Autowired
	private ProjetService projetService;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@RequestMapping(value="/MesProjets")
	public String mesProjets(Model model) {
		//à changer avec l'objet utilisateur connecté
		Utilisateur user = new Utilisateur();
		user.setId(1L);
		
		List<Projet> listeProjets = projetService.listeProjetsParUtilisateur(user);
		model.addAttribute("listeProjets", listeProjets);
		
		return "MesProjets";
	}
	
	@RequestMapping(value="/AjouterProjet", method=RequestMethod.GET)
	public String ajouterProjetForm(Model model) {
		// à changer avec l'objet utilisateur connecté	
		model.addAttribute("nouveauProjet", new Projet());
		return "AjouterProjet";
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
		utilisateurRepository.save(utilisateurCourant);
		
		projetService.addProjet(nouveauProjet);
		
		return new ModelAndView("redirect:/MesProjets");
	}
}
