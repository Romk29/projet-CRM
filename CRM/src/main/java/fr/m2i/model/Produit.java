package fr.m2i.model;

public class Produit {

	private int id;
	private String nom;
	private String description;
	private Double prix;
	
	
	
	
	public Produit() {
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
