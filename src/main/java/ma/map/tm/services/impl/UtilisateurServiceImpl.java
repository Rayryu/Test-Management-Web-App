package ma.map.tm.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ma.map.tm.dao.UtilisateurRepository;
import ma.map.tm.entities.Projet;
import ma.map.tm.entities.Utilisateur;
import ma.map.tm.services.UtilisateurService;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{
	
	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Override
	public List<Projet> getlisteProjets(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur getUserById(Long idUtilisateur) {
		return utilisateurRepository.getOne(idUtilisateur);
	}

	@Override
	public Utilisateur getUserByEmail(String email) {
		
		return utilisateurRepository.findByUserEmail(email);
	}

	@Override
	public Utilisateur getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();
		return getUserByEmail(email);
	}

	@Override
	public List<Utilisateur> getAllUsers() {
		return utilisateurRepository.findAll();
	}

	@Override
	public void addUtilisateur(Utilisateur nouvelUtilisateur) {
		utilisateurRepository.save(nouvelUtilisateur);
	}

	@Override
	public Utilisateur getUserById(Optional<Long> idUtilisateur) {
		if (idUtilisateur.isPresent()) return getUserById(idUtilisateur.get());
		return new Utilisateur();
	}

	@Override
	public void modifierUtilisateur(Long idUtilisateur, Utilisateur utilisateurSelectionne) {
		Utilisateur currentUtilisateur = getUserById(idUtilisateur);
		utilisateurSelectionne.setId(idUtilisateur);
		utilisateurSelectionne.setListeCampagnes(currentUtilisateur.getListeCampagnes());
		utilisateurSelectionne.setListeCasTests(currentUtilisateur.getListeCasTests());
		utilisateurSelectionne.setListeProjets(currentUtilisateur.getListeProjets());
		
		utilisateurRepository.save(utilisateurSelectionne);
		
	}

	@Override
	public void supprimerUtilisateur(Long idUtilisateur) {
		Utilisateur utilisateur = getUserById(idUtilisateur);
		utilisateur.setEnabled(false);
		utilisateurRepository.save(utilisateur);
		//utilisateurRepository.delete(utilisateur);
		
	}
	
	@Override
	public void activerUtilisateur(Long idUtilisateur) {
		Utilisateur utilisateur = getUserById(idUtilisateur);
		utilisateur.setEnabled(true);
		utilisateurRepository.save(utilisateur);
		
	}
	

}
