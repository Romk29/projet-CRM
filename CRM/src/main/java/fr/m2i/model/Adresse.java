package fr.m2i.model;

public class Adresse {
	int id;
	String rue;
	String ville;
	String pays;
	int code_postal;
	
	
	public  Adresse() {
	
	}

	public Adresse(int id, String rue, String ville, String pays, int code_postal) {
		
		this.id = id;
		this.rue = rue;
		this.ville = ville;
		this.pays = pays;
		this.code_postal = code_postal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public int getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(int code_postal) {
		this.code_postal = code_postal;
	}

}
