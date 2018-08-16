package ma.map.tm.services;

import java.util.Hashtable;
import java.util.List;

import ma.map.tm.entities.CasTest;
import ma.map.tm.entities.ExecutionTest;
import ma.map.tm.entities.Projet;
import ma.map.tm.entities.Utilisateur;
import ma.map.tm.entities.Scenario;

public interface CasTestService {
	public void addCasDeTest(CasTest c);
	public List<CasTest> listeCasTest();
	public List<CasTest> listeCasTestParUtilisateur(Utilisateur u);
	public CasTest getCasTestById(Long id);
	public List<CasTest> listeCasTestParScenario(Scenario s);
	public void modifierCasTest(CasTest casTestCourant, Long idCasTest);
	public void supprimerCasTest(Long idCasTest);
	public List<CasTest> listeCasExecutes(Utilisateur u);
	public Hashtable<String, Integer> getStatistiquesScenario(Scenario scenarioCourant);
	public List<CasTest> getTroisDerniersCasTest(Utilisateur utilisateurCourant);
	public List<CasTest> listeCasTestByProjet(Projet projetCourant);
}
