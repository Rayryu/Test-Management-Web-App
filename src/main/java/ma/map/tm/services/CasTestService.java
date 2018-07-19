package ma.map.tm.services;

import java.util.List;

import ma.map.tm.entities.CasTest;
import ma.map.tm.entities.Utilisateur;
import ma.map.tm.entities.Scenario;

public interface CasTestService {
	public void addCasDeTest(CasTest c);
	public List<CasTest> listeCasTest();
	public List<CasTest> listeCasTestParUtilisateur(Utilisateur u);
	public CasTest getCasTestById(Long id);
	public List<CasTest> listeCasTestParScenario(Scenario s);
}
