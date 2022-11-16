package fr.m2i.model;

public class Contient {
	
	int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private Produit produit;
	private Panier panier;
	
	public Contient() {
		
	}
	
	public Contient(Produit produit, Panier panier) {
		this.produit = produit;
		this.panier = panier;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	
		
}
