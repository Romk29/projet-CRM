package fr.m2i.model;

public class Paiement {
	
	private int id;
	private int no_carte;
	private int code_confidentiel;
	private String banque;
	private Client client;
	
	public Paiement() {
		
	}
	
	
	public Paiement(int id, int no_carte, int code_confidentiel, String banque, Client client) {
		this.id = id;
		this.no_carte = no_carte;
		this.code_confidentiel = code_confidentiel;
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
		return no_carte;
	}

	public void setNo_carte(int no_carte) {
		this.no_carte = no_carte;
	}

	public int getCode_confidentiel() {
		return code_confidentiel;
	}

	public void setCode_confidentiel(int code_confidentiel) {
		this.code_confidentiel = code_confidentiel;
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
