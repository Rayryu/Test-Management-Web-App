package ma.map.tm.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Scenario implements Serializable{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String description;
	private Date dateCreation;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="campagneParent_id")
	private CampagneTest campagneParent;
	@OneToMany(mappedBy="scenarioParent", cascade=CascadeType.ALL)
	private Collection<CasTest> listeCasTests = new ArrayList<CasTest>();
	
	
	
	public Scenario(String nom, String description, CampagneTest campagneParent, Collection<CasTest> listeCasTests) {
		super();
		this.nom = nom;
		this.description = description;
		this.campagneParent = campagneParent;
		this.listeCasTests = listeCasTests;
	}

	public Scenario() {
		super();
	}
	
	public Scenario(String nom, String description) {
		super();
		this.nom = nom;
		this.description = description;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CampagneTest getCampagne() {
		return campagneParent;
	}
	public void setCampagne(CampagneTest campagneTest) {
		this.campagneParent = campagneTest;
	}

	public Collection<CasTest> getListeCasTests() {
		return listeCasTests;
	}

	public void setListeCasTests(Collection<CasTest> listeCasTests) {
		this.listeCasTests = listeCasTests;
	}

	public CampagneTest getCampagneParent() {
		return campagneParent;
	}

	public void setCampagneParent(CampagneTest campagneParent) {
		this.campagneParent = campagneParent;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	

	
	

}
