package ma.map.tm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.map.tm.dao.ScenarioRepository;
import ma.map.tm.entities.CampagneTest;
import ma.map.tm.entities.Scenario;
import ma.map.tm.entities.Utilisateur;

@Service
public class ScenarioServiceImpl implements ScenarioService {
	
	@Autowired
	private ScenarioRepository scenarioRepository;


	@Override
	public void addScenario(Scenario s) {
		scenarioRepository.save(s);
	}

	@Override
	public List<Scenario> listeScenarioDeTest() {
		return scenarioRepository.findAll();
	}

	@Override
	public List<Scenario> listeScenarioTestParUtilisateur(Utilisateur u) {
		return scenarioRepository.findByScenarioId(u.getId());
	}

	@Override
	public Scenario getScenarioById(Long id) {
		Optional<Scenario> st =  scenarioRepository.findById(id);
		if (st.isPresent()) {
			return st.get();
		} else {
			return new Scenario("Une erreur est survenue", "Une erreur est survenue");
		}
	}

	@Override
	public List<Scenario> listeScenariosParCampagneId(Long id) {
		return scenarioRepository.findByCampagneId(id);
	}

	@Override
	public void modifierScenario(Scenario nouveauScenario, Long id_scenarioParent) {
		Scenario scenarioParent = getScenarioById(id_scenarioParent);
		nouveauScenario.setId(id_scenarioParent);
		nouveauScenario.setCampagneParent(scenarioParent.getCampagne());
		nouveauScenario.setListeCasTests(scenarioParent.getListeCasTests());
		
		addScenario(nouveauScenario);
		
	}

	

}
