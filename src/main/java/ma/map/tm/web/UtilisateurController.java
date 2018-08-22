package ma.map.tm.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ma.map.tm.entities.Role;
import ma.map.tm.entities.Utilisateur;
import ma.map.tm.services.RoleService;
import ma.map.tm.services.UtilisateurService;

@Controller
public class UtilisateurController {
	
	@Autowired
	private UtilisateurService utilisateurService;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value= {"/GestionDesUtilisateurs/{id}", "/GestionDesUtilisateurs"})
	public String gestionDesUtilisateurs(Model model, @PathVariable("id") Optional<Long> idUtilisateur) {
		
		Utilisateur utilisateurSelectionne = utilisateurService.getUserById(idUtilisateur);
		Utilisateur nouvelUtilisateur = new Utilisateur();
		List<Utilisateur> listeUtilisateurs =  utilisateurService.getAllUsers();
		List<Role> listeRoles = roleService.getAllRoles();
		
		model.addAttribute("utilisateurSelectionne", utilisateurSelectionne);
		model.addAttribute("nouvelUtilisateur", nouvelUtilisateur);
		model.addAttribute("listeUtilisateurs", listeUtilisateurs);
		model.addAttribute("listeRoles", listeRoles);
		model.addAttribute("currentUser", utilisateurService.getLoggedInUser());
		
		return "GestionUtilisateurs";
	}
	
	@RequestMapping("/AjouterNouvelUtilisateur")
	public ModelAndView ajouterUtilisateur(Utilisateur nouvelUtilisateur) {
		
		utilisateurService.addUtilisateur(nouvelUtilisateur);
		
		return new ModelAndView("redirect:/GestionDesUtilisateurs");
	}
	
	@RequestMapping("/ModifierUtilisateur/{id}")
	public ModelAndView modifierUtilisateur(@PathVariable("id") Long idUtilisateur, Utilisateur utilisateurSelectionne) {
		
		utilisateurService.modifierUtilisateur(idUtilisateur, utilisateurSelectionne);
		
		return new ModelAndView("redirect:/GestionDesUtilisateurs");
	}
	
	@RequestMapping("SupprimerUtilisateur/{id}")
	public ModelAndView supprimerUtilisateur(@PathVariable("id") Long idUtilisateur) {
		
		utilisateurService.supprimerUtilisateur(idUtilisateur);
		
		return new ModelAndView("redirect:/GestionDesUtilisateurs");
	}
	
	@RequestMapping("ActiverUtilisateur/{id}")
	public ModelAndView activerUtilisateur(@PathVariable("id") Long idUtilisateur) {
		
		utilisateurService.activerUtilisateur(idUtilisateur);
		
		return new ModelAndView("redirect:/GestionDesUtilisateurs");
	}
	
}
