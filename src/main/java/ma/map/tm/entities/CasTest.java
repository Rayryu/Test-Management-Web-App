package ma.map.tm.entities;

import java.io.Serializable;
import java.util.Date;

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
	private String titre;
	private String description;
	private String priorite;
	private String precondition;
	private String resultatAttendu;
	private String resultatActuel;
	private String remarques;
	private String etapes;
	private String commentaire;
	private String statut;
	private Date dateCreation;
	private String jeuDeTest;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="type_id")
	private TypeTest typeTest;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="testeur_id")
	private Utilisateur testeur;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scenarioParent_id")
	private Scenario scenarioParent;
	

	public CasTest(String description, TypeTest typeTest, String priorite, String precondition, String resultatAttendu,
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

	public CasTest(String description, TypeTest typeTest) {
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

	public TypeTest getTypeTest() {
		return typeTest;
	}

	public void setTypeTest(TypeTest typeTest) {
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

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return the jeuDeTest
	 */
	public String getJeuDeTest() {
		return jeuDeTest;
	}

	/**
	 * @param jeuDeTest the jeuDeTest to set
	 */
	public void setJeuDeTest(String jeuDeTest) {
		this.jeuDeTest = jeuDeTest;
	}


}
