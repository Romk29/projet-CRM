package fr.m2i.model;

public class Panier {
	
	private int id;
	private Client client;
	
	public Panier() {
		
	}

	public Panier(Client client) {
		this.client = client;
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
	
	@Override
	public String toString() {
		return  getId() + " " + getClient().getNom() + " " + getClient().getPrenom();
	}

}
