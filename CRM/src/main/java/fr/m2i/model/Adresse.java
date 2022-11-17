package fr.m2i.model;

public class Adresse {
	int id;
	String rue;
	String ville;
	String pays;
	String codePostal;
	
	
	public  Adresse() {
	
	}

	public Adresse(String rue, String ville, String pays, String codePostal) {
		
		this.rue = rue;
		this.ville = ville;
		this.pays = pays;
		this.codePostal = codePostal;
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

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String string) {
		this.codePostal = string;
	}

	@Override
	public String toString() {
		return getId() + " : " + getRue() + " " + getVille() + " - " + getPays() + " / " + getCodePostal();
	}

	
}
