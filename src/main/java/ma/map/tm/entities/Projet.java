package ma.map.tm.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Projet implements Serializable{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String description; 
	@ManyToMany(mappedBy="listeProjets")
	private Collection<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
	@OneToMany(mappedBy="projetParent", cascade=CascadeType.ALL)
	private Collection<CampagneTest> listeCampagne = new ArrayList<CampagneTest>();
	public Projet( String nom, String description, Collection<CampagneTest> listeCampagne, Collection<Utilisateur> listeUtilisateurs) {
		super();

		this.nom = nom;
		this.description = description;
		this.listeCampagne = listeCampagne;
		this.listeUtilisateurs = listeUtilisateurs;
	}
	
	public Projet() {
		super();
	}

	public Projet( String nom, String description, Collection<Utilisateur> listeUtilisateurs) {
		super();

		this.nom = nom;
		this.description = description;
		this.listeUtilisateurs = listeUtilisateurs;
	}

	public Projet( String nom, String description) {
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

	public Collection<Utilisateur> getListeUtilisateurs() {
		return listeUtilisateurs;
	}
	
	public void updateListeUtilisateurs(Utilisateur utilisateur) {
		Collection<Utilisateur> lu = this.getListeUtilisateurs();
		lu.add(utilisateur);
		this.setListeUtilisateurs(lu);
	}

	public void setListeUtilisateurs(Collection<Utilisateur> listeUtilisateurs) {
		this.listeUtilisateurs = listeUtilisateurs;
	}

	public Collection<CampagneTest> getListeCampagne() {
		return listeCampagne;
	}

	public void setListeCampagne(Collection<CampagneTest> listeCampagne) {
		this.listeCampagne = listeCampagne;
	}
	
	
	
	
	
	
	
}
