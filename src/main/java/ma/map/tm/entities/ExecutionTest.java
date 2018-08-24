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
public class ExecutionTest implements Serializable{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String resultatObtenu;
	private String status;
	private String commentaire;
	private Date dateCreation;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="casTestParent_id")
	private CasTest casTestParent;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="testeur_id")
	private Utilisateur testeur;
	
	public ExecutionTest() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getResultatObtenu() {
		return resultatObtenu;
	}
	public void setResultatObtenu(String resultatObtenu) {
		this.resultatObtenu = resultatObtenu;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public CasTest getCasTestParent() {
		return casTestParent;
	}
	public void setCasTestParent(CasTest casTestParent) {
		this.casTestParent = casTestParent;
	}
	public Utilisateur getTesteur() {
		return testeur;
	}
	public void setTesteur(Utilisateur testeur) {
		this.testeur = testeur;
	}
	
	
}
