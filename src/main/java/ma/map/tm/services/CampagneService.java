package ma.map.tm.services;

import java.util.List;

import ma.map.tm.entities.CampagneTest;
import ma.map.tm.entities.Utilisateur;

public interface CampagneService {
	public void addCampagne(CampagneTest c);
	public List<CampagneTest> listeCampagneDeTest();
	public List<CampagneTest> listeCampagneTestParUtilisateur(Utilisateur u);
}
