package fr.m2i.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "adresse")
public class Adresse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 70)
	private String rue;
	
	@Column(nullable = false, length = 70)
	private String ville;
	
	@Column(nullable = false, length = 70)
	private String pays;
	
	@Column(nullable = false, length = 5)
	private String codePostal;
	
	@OneToMany(mappedBy = "adresse")
	private List<Client> clients = new ArrayList<>();
	
	
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

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
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
