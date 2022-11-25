package fr.m2i.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 70)
	private String nom;
	
	@Column(nullable = false, length = 70)
	private String prenom;
	
	@Column(nullable = false)
	private String mail;
	
	@Column(length = 70)
	private String nomSociete;
	
	@Column(nullable = false, length = 10)
	private String telephone;
	
	private int etat;
	
	private int genre;
	
	@ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	private Adresse adresse;
	
	@OneToMany(mappedBy = "client")
	private List<Panier> paniers = new ArrayList<Panier>();
	
	@OneToMany(mappedBy = "client")
	private List<Paiement> paiements = new ArrayList<Paiement>();
	
	
	public Client() {
		
	}

	public Client(String nom, String prenom, Adresse adresse, String mail, String nomSociete, String telephone,
			int etat, int genre) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.mail = mail;
		this.nomSociete = nomSociete;
		this.telephone = telephone;
		this.etat = etat;
		this.genre = genre;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNomSociete() {
		return nomSociete;
	}

	public void setNomSociete(String nomSociete) {
		this.nomSociete = nomSociete;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public int getGenre() {
		return genre;
	}

	public void setGenre(int genre) {
		this.genre = genre;
	}
	
	public List<Panier> getPaniers() {
		return paniers;
	}

	public void setPaniers(List<Panier> paniers) {
		this.paniers = paniers;
	}

	public List<Paiement> getPaiements() {
		return paiements;
	}

	public void setPaiements(List<Paiement> paiements) {
		this.paiements = paiements;
	}

	@Override
	public String toString() {
		return getId() + " : " + getNom() + " " + getPrenom();
	}
	

}
