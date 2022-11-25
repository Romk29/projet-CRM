package fr.m2i.model;

public class Paiement {
	
	private int id;
	private String noCarte;
	private String codeConfidentiel;
	private String banque;
	private Client client;
	
	public Paiement() {
		
	}
	
	
	public Paiement(int id, String noCarte, String codeConfidentiel, String banque, Client client) {
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

	public String getNoCarte() {
		return noCarte;
	}

	public void setNoCarte(String noCarte) {
		this.noCarte = noCarte;
	}

	public String getCodeConfidentiel() {
		return codeConfidentiel;
	}

	public void setCodeConfidentiel(String codeConfidentiel) {
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
