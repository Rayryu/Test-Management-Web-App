package ma.map.tm.services;

import java.util.List;

import ma.map.tm.entities.CampagneTest;
import ma.map.tm.entities.CasTest;
import ma.map.tm.entities.Projet;
import ma.map.tm.entities.Utilisateur;

public interface CampagneService {
	public void addCampagne(CampagneTest c);
	public List<CampagneTest> listeCampagneDeTest();
	public List<CampagneTest> listeCampagneTestParUtilisateur(Utilisateur u);
	public CampagneTest getCampagneById(Long id);
	public List<CampagneTest> listeCampagnesByProjetId(Long id);
	public void modifierCampagne(CampagneTest nouvelleCampagne, Long idCampagneParent);
	public void supprimerCampagne(Long id_campagne);
	public List<CampagneTest> getTroisDernieresCampagnes(Projet projetCourant);
	public List<Integer> getStatsTroisDernieresCampagnes(Projet projetCourant);
	public List<Integer> getStats(Projet projetCourant);
	public List<CasTest> listeCasParProjet(Projet projetCourant);
	public List<CasTest> getTroisDerniersCasTest(Projet projetCourant);
}
