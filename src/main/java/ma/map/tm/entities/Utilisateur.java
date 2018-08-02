package ma.map.tm.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Utilisateur implements Serializable{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	private String motDePass;
	private boolean enabled = true;
	private String email;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role_id")
	private Role role;
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "utilisateur_projet",
    joinColumns = @JoinColumn(name = "utilisateur_id"),
    inverseJoinColumns = @JoinColumn(name = "projet_id"))
	private Collection<Projet> listeProjets = new ArrayList<Projet>();
	@OneToMany(mappedBy="concepteurTest", cascade=CascadeType.ALL)
	private Collection<CampagneTest> listeCampagnes= new ArrayList<CampagneTest>();
	@OneToMany(mappedBy="testeur", cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private Collection<CasTest> listeCasTests = new ArrayList<CasTest>();

	public Utilisateur(String nom, String prenom, Role role, Collection<Projet> listeProjets,
			Collection<CampagneTest> listeCampagnes, Collection<CasTest> listeCasTests) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.role = role;
		this.listeProjets = listeProjets;
		this.listeCampagnes = listeCampagnes;
		this.listeCasTests = listeCasTests;
	}

	public Utilisateur() {
		super();
	}

	public Utilisateur(String nom, String prenom, Role role) {
		this.nom = nom;
		this.prenom = prenom;
		this.role = role;
	}

	public Utilisateur(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Collection<Projet> getListeProjets() {
		return listeProjets;
	}

	public void setListeProjets(Collection<Projet> listeProjets) {
		this.listeProjets = listeProjets;
	}

	public Collection<CampagneTest> getListeCampagnes() {
		return listeCampagnes;
	}

	public void setListeCampagnes(Collection<CampagneTest> listeCampagnes) {
		this.listeCampagnes = listeCampagnes;
	}

	public Collection<CasTest> getListeCasTests() {
		return listeCasTests;
	}

	public void setListeCasTests(Collection<CasTest> listeCasTests) {
		this.listeCasTests = listeCasTests;
	}
	
	public void updateListeProjets(Projet projet) {
		Collection<Projet> lu = this.getListeProjets();
		lu.add(projet);
		this.setListeProjets(lu);
	}

	public String getMotDePass() {
		return motDePass;
	}

	public void setMotDePass(String motDePass) {
		this.motDePass = motDePass;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
