package fr.m2i.model;

import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.Table;

@Entity
@Table(name = "panier")
public class Panier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Client client;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name="contient",
			joinColumns = { @JoinColumn(name="panier_id") },
			inverseJoinColumns = { @JoinColumn(name="produit_id") }
	)
	private List<Produit> produits = new ArrayList<Produit>();
	
	
	public Panier() {
		
	}

	public Panier(Client client, List<Produit> produits) {
		this.client = client;
		this.produits = produits;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
			
	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	@Override
	public String toString() {
		return  getId() + " " + getClient().getNom() + " " + getClient().getPrenom();
	}

}
