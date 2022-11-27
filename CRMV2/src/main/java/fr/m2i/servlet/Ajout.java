package fr.m2i.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2i.model.Adresse;
import fr.m2i.model.Client;
import fr.m2i.model.Paiement;
import fr.m2i.model.Panier;
import fr.m2i.model.Produit;

@WebServlet("/Ajout")
public class Ajout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EntityManagerFactory entityManagerFactory;
           
    public Ajout() {
        super();
      entityManagerFactory = Persistence.createEntityManagerFactory("applicationcrm");
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//Création Entité
				EntityTransaction entityTransaction = null;
				try {
					entityTransaction = entityManager.getTransaction();
					entityTransaction.begin();
					
						//Ajout de 5 adresses
					
					   	Adresse newAdresse = new Adresse();
					   	newAdresse.setRue("7 rue de la Fayette");
					   	newAdresse.setVille("New York");
					   	newAdresse.setPays("USA");
					   	newAdresse.setCodePostal("44620");
					   	
			            entityManager.persist(newAdresse);
			            
			            Adresse newAdresse2 = new Adresse();
					   	newAdresse2.setRue("8 rue de Conemuse");
					   	newAdresse2.setVille("Rennes");
					   	newAdresse2.setPays("FRANCE");
					   	newAdresse2.setCodePostal("35500");
					   	
			            entityManager.persist(newAdresse2);
			            
			            Adresse newAdresse3 = new Adresse();
					   	newAdresse3.setRue("8 rue de Mellac");
					   	newAdresse3.setVille("Quimperlé");
					   	newAdresse3.setPays("FRANCE");
					   	newAdresse3.setCodePostal("29300");
					   	
			            entityManager.persist(newAdresse3);
			            
			            Adresse newAdresse4 = new Adresse();
					   	newAdresse4.setRue("3 rue des Franciscains");
					   	newAdresse4.setVille("Nantes");
					   	newAdresse4.setPays("FRANCE");
					   	newAdresse4.setCodePostal("44300");
					   	
			            entityManager.persist(newAdresse4);
			            
			            Adresse newAdresse5 = new Adresse();
					   	newAdresse5.setRue("9 rue de parc-mar");
					   	newAdresse5.setVille("Bannalec");
					   	newAdresse5.setPays("FRANCE");
					   	newAdresse5.setCodePostal("29380");
					   	
			            entityManager.persist(newAdresse5);
			            
			          //Ajout de 5 clients
	
			            Client newClient = new Client();
			            newClient.setNom("Eric");
			            newClient.setPrenom("Gilbert");
			            newClient.setMail("salopette@gmail.com");
			            newClient.setTelephone("0617354610");
			            newClient.setAdresse(newAdresse);
			           			           			  			           			           
			            entityManager.persist(newClient);
			           
			            Client newClient2 = new Client();
			            newClient2.setNom("Salaun");
			            newClient2.setPrenom("Morgane");
			            newClient2.setMail("pirouette@gmail.com");
			            newClient2.setTelephone("0617958610");
			            newClient2.setAdresse(newAdresse2);
			           			           			  			           			           
			            entityManager.persist(newClient2);
			            
			            Client newClient3 = new Client();
			            newClient3.setNom("Tartampion");
			            newClient3.setPrenom("Franklin");
			            newClient3.setMail("Tartipouf@gmail.com");
			            newClient3.setTelephone("0601021345");
			            newClient3.setAdresse(newAdresse3);
			           			           			  			           			           
			            entityManager.persist(newClient3);
			            
			            Client newClient4 = new Client();
			            newClient4.setNom("Robert");
			            newClient4.setPrenom("Charlotte");
			            newClient4.setMail("charlotte@gmail.com");
			            newClient4.setTelephone("0723554489");
			            newClient4.setAdresse(newAdresse4);
			           			           			  			           			           
			            entityManager.persist(newClient4);
			           
			            Client newClient5 = new Client();
			            newClient5.setNom("Frette");
			            newClient5.setPrenom("Nicolas");
			            newClient5.setMail("nicolerigolo@gmail.com");
			            newClient5.setTelephone("0799825417");
			            newClient5.setAdresse(newAdresse4);
			           			           			  			           			           
			            entityManager.persist(newClient5);
			           			           
			            //Ajout de 5 produits
			            
			            Produit newProduit = new Produit();
			            newProduit.setNom("Paddle");
			            newProduit.setPrix(530.50);
			            
			            entityManager.persist(newProduit);
			            
			            Produit newProduit2 = new Produit();
			            newProduit2.setNom("Kayak");
			            newProduit2.setPrix(419.99);
			            
			            entityManager.persist(newProduit2);
			            
			            Produit newProduit3 = new Produit();
			            newProduit3.setNom("Violon");
			            newProduit3.setPrix(5730.00);
			            
			            entityManager.persist(newProduit3);
			            
			            Produit newProduit4 = new Produit();
			            newProduit4.setNom("Parapluie");
			            newProduit4.setPrix(950.70);
			            
			            entityManager.persist(newProduit4);
			            
			            Produit newProduit5 = new Produit();
			            newProduit5.setNom("Chausette");
			            newProduit5.setPrix(10.25);
			            
			            entityManager.persist(newProduit5);
			            
			            //Mep 5 listes de produits pour remplir les paniers
			            
			            List<Produit> produits = new ArrayList<Produit>();
			            produits.add(newProduit);
			            
			            List<Produit> produits2 = new ArrayList<Produit>();
			            produits2.add(newProduit2);
			            produits2.add(newProduit3);
			            
			            List<Produit> produits3 = new ArrayList<Produit>();
			            produits3.add(newProduit4);
			            produits3.add(newProduit4);
			            produits3.add(newProduit5);
			            
			            List<Produit> produits4 = new ArrayList<Produit>();
			            produits4.add(newProduit5);
			            produits4.add(newProduit5);
			            
			            List<Produit> produits5 = new ArrayList<Produit>();
			            produits5.add(newProduit);
			            produits5.add(newProduit2);
			            produits5.add(newProduit3);
			            produits5.add(newProduit4);
			            produits5.add(newProduit5);
			            			            
			            //Ajout de 5 paniers
			            			            
			            Panier newPanier = new Panier();
			            newPanier.setClient(newClient);
			            newPanier.setProduits(produits);
			            
			            entityManager.persist(newPanier);
			            
			            Panier newPanier2 = new Panier();
			            newPanier2.setClient(newClient2);
			            newPanier2.setProduits(produits2);
			            
			            entityManager.persist(newPanier2);
			            
			            Panier newPanier3 = new Panier();
			            newPanier3.setClient(newClient3);
			            newPanier3.setProduits(produits3);
			            
			            entityManager.persist(newPanier3);
			            
			            Panier newPanier4 = new Panier();
			            newPanier4.setClient(newClient3);
			            newPanier4.setProduits(produits4);
			            
			            entityManager.persist(newPanier4);
			            
			            Panier newPanier5 = new Panier();
			            newPanier5.setClient(newClient5);
			            newPanier5.setProduits(produits5);
			            
			            entityManager.persist(newPanier5);
			            
			            //Ajout de 5 paiements
			            			            			            
			            Paiement newPaiement = new Paiement();
			            newPaiement.setNoCarte(1234);
			            newPaiement.setCodeConfidentiel(111);
			            newPaiement.setBanque("CMB");
			            newPaiement.setClient(newClient);
			            
			            entityManager.persist(newPaiement);
			            
			            Paiement newPaiement2 = new Paiement();
			            newPaiement2.setNoCarte(5678);
			            newPaiement2.setCodeConfidentiel(222);
			            newPaiement2.setBanque("CA");
			            newPaiement2.setClient(newClient2);
			            
			            entityManager.persist(newPaiement2);
			            
			            Paiement newPaiement3 = new Paiement();
			            newPaiement3.setNoCarte(1020);
			            newPaiement3.setCodeConfidentiel(333);
			            newPaiement3.setBanque("SG");
			            newPaiement3.setClient(newClient3);
			            
			            entityManager.persist(newPaiement3);
			            
			            Paiement newPaiement4 = new Paiement();
			            newPaiement4.setNoCarte(2580);
			            newPaiement4.setCodeConfidentiel(444);
			            newPaiement4.setBanque("CE");
			            newPaiement4.setClient(newClient5);
			            
			            entityManager.persist(newPaiement4);			            			            
					
					entityTransaction.commit();
				} catch(Exception e) {
					e.printStackTrace();
					if(entityTransaction!=null)
						entityTransaction.rollback();
				}		
		
	}
		
}
