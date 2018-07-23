package ma.map.tm.services;

import java.util.List;

import ma.map.tm.entities.Scenario;
import ma.map.tm.entities.Utilisateur;
import ma.map.tm.entities.CampagneTest;;

public interface ScenarioService {
	public void addScenario(Scenario s);
	public List<Scenario> listeScenarioDeTest();
	public List<Scenario> listeScenarioTestParUtilisateur(Utilisateur u);
	public Scenario getScenarioById(Long id);
	public List<Scenario> listeScenariosParCampagneId(Long id);
	public void modifierScenario(Scenario nouveauScenario, Long id_scenarioParent);
	public void supprimerScenario(Long id_scenarioParent);

}
