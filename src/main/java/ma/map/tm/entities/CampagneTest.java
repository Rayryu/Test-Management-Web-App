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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class CampagneTest implements Serializable{	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String description;
	private Date dateCreation;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="concepteurTest_id")
	private Utilisateur concepteurTest;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="projetParent_id")
	private Projet projetParent;
	@OneToMany(mappedBy="campagneParent", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private Collection<Scenario> listeScenario = new ArrayList<Scenario>();
	
	public CampagneTest(String nom, String description, Utilisateur concepteurTest, Projet projetParent,
			Collection<Scenario> listeScenario) {
		super();
		this.nom = nom	;
		this.description = description;
		this.concepteurTest = concepteurTest;
		this.projetParent = projetParent;
		this.listeScenario = listeScenario;
	}

	public CampagneTest() {
		super();
	}

	public CampagneTest(String nom, String description, Utilisateur concepteurTest, Projet projetParent) {
		super();
		this.nom = nom;
		this.description = description;
		this.concepteurTest = concepteurTest;
		this.projetParent = projetParent;
	}

	public CampagneTest(String nom, String description) {
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

	
	public Utilisateur getConcepteurTest() {
		return concepteurTest;
	}

	public void setConcepteurTest(Utilisateur concepteurTest) {
		this.concepteurTest = concepteurTest;
	}

	public Projet getProjetParent() {
		return projetParent;
	}

	public void setProjetParent(Projet projetParent) {
		this.projetParent = projetParent;
	}

	public Collection<Scenario> getListeScenario() {
		return listeScenario;
	}

	public void setListeScenario(Collection<Scenario> listeScenario) {
		this.listeScenario = listeScenario;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	
		

}
