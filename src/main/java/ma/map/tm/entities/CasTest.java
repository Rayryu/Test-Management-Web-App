package ma.map.tm.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CasTest implements Serializable{
	

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String description;
	private String typeTest;
	private String priorite;
	private String precondition;
	private String resultatAttendu;
	private String resultatActuel;
	private String remarques;
	private String etapes;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="testeur_id")
	private Utilisateur testeur;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scenarioParent_id")
	private Scenario scenarioParent;
	

	public CasTest(String description, String typeTest, String priorite, String precondition, String resultatAttendu,
			String resultatActuel, String remarques, String etapes, Utilisateur testeur, Scenario scenarioParent) {
		super();
		this.description = description;
		this.typeTest = typeTest;
		this.priorite = priorite;
		this.precondition = precondition;
		this.resultatAttendu = resultatAttendu;
		this.resultatActuel = resultatActuel;
		this.remarques = remarques;
		this.etapes = etapes;
		this.testeur = testeur;
		this.scenarioParent = scenarioParent;
	}

	public CasTest() {
		super();
	}

	public CasTest(String description, String typeTest) {
		this.description = description;
		this.typeTest = typeTest;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTypeTest() {
		return typeTest;
	}

	public void setTypeTest(String typeTest) {
		this.typeTest = typeTest;
	}

	public String getPriorite() {
		return priorite;
	}

	public void setPriorite(String  priorite) {
		this.priorite = priorite;
	}

	public String getPrecondition() {
		return precondition;
	}

	public void setPrecondition(String precondition) {
		this.precondition = precondition;
	}

	public String getResultatAttendu() {
		return resultatAttendu;
	}

	public void setResultatAttendu(String resultatAttendu) {
		this.resultatAttendu = resultatAttendu;
	}

	public String getResultatActuel() {
		return resultatActuel;
	}

	public void setResultatActuel(String resultatActuel) {
		this.resultatActuel = resultatActuel;
	}

	public String getRemarques() {
		return remarques;
	}

	public void setRemarques(String remarques) {
		this.remarques = remarques;
	}

	public Scenario getScenario() {
		return scenarioParent;
	}

	public void setScenario(Scenario scenario) {
		this.scenarioParent = scenario;
	}

	public Utilisateur getTesteur() {
		return testeur;
	}

	public void setTesteur(Utilisateur testeur) {
		this.testeur = testeur;
	}

	public String getEtapes() {
		return etapes;
	}

	public void setEtapes(String etapes) {
		this.etapes = etapes;
	}

	public Scenario getScenarioParent() {
		return scenarioParent;
	}

	public void setScenarioParent(Scenario scenarioParent) {
		this.scenarioParent = scenarioParent;
	}


}
