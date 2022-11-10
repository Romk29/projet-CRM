package fr.m2i.model;

public class Paiement {
	
	private int id;
	private int noCarte;
	private int codeConfidentiel;
	private String banque;
	private Client client;
	
	public Paiement() {
		
	}
	
	
	public Paiement(int id, int no_carte, int code_confidentiel, String banque, Client client) {
		this.id = id;
		this.noCarte = no_carte;
		this.codeConfidentiel = code_confidentiel;
		this.banque = banque;
		this.client = client;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public int getNo_carte() {
		return noCarte;
	}

	public void setNo_carte(int noCarte) {
		this.noCarte = noCarte;
	}

	public int getCode_confidentiel() {
		return codeConfidentiel;
	}

	public void setCode_confidentiel(int codeConfidentiel) {
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
