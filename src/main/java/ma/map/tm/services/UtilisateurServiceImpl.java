package ma.map.tm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.map.tm.dao.UtilisateurRepository;
import ma.map.tm.entities.Projet;
import ma.map.tm.entities.Utilisateur;

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

}
