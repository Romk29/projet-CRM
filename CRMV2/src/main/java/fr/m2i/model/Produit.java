package fr.m2i.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "produit")
public class Produit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 70)
	private String nom;
	
	@Column(length = 255)
	private String description;
	
	@Column(nullable = false)
	private Double prix;
	
	@ManyToMany(mappedBy = "produits")
	private List<Panier> paniers = new ArrayList<Panier>();
	
		
	public Produit() {
	}



	public List<Panier> getPaniers() {
		return paniers;
	}



	public void setPaniers(List<Panier> paniers) {
		this.paniers = paniers;
	}



	public Produit(int id, String nom, String description, Double prix) {
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.prix = prix;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}
	
	
	

	
	
	
	

}
