package fr.m2i.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "paiement")
public class Paiement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 4)
	private int noCarte;
	
	@Column(nullable = false, length = 3)
	private int codeConfidentiel;
	
	@Column(nullable = false)
	private String banque;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Client client;
	
	
	public Paiement() {
		
	}
		
	public Paiement(int id, int noCarte, int codeConfidentiel, String banque, Client client) {
		this.id = id;
		this.noCarte = noCarte;
		this.codeConfidentiel = codeConfidentiel;
		this.banque = banque;
		this.client = client;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public int getNoCarte() {
		return noCarte;
	}

	public void setNoCarte(int noCarte) {
		this.noCarte = noCarte;
	}

	public int getCodeConfidentiel() {
		return codeConfidentiel;
	}

	public void setCodeConfidentiel(int codeConfidentiel) {
		this.codeConfidentiel = codeConfidentiel;
	}

	public String getBanque() {
		return banque;
	}

	public void setBanque(String banque) {
		this.banque = banque;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}	
	
}
