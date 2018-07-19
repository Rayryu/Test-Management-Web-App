package ma.map.tm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.map.tm.dao.ProjetRepository;
import ma.map.tm.entities.Projet;
import ma.map.tm.entities.Utilisateur;

@Service
public class ProjetServiceImpl implements ProjetService {

	@Autowired
	private ProjetRepository projetRepository;
	
	@Override
	public void addProjet(Projet p) {
		projetRepository.save(p);
	}

	@Override
	public List<Projet> listeProjets() {
		return projetRepository.findAll();
	}

	@Override
	public List<Projet> listeProjetsParUtilisateur(Utilisateur u) {
		return projetRepository.findByUtilisateurId(u.getId());
	}

	@Override
	public Projet getProjetById(Long id) {
		return projetRepository.getOne(id);
	}

}
