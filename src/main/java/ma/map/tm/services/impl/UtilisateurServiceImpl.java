package ma.map.tm.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	

}
