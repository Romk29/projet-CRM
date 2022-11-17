package fr.m2i.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2i.dao.ProduitDao;
import fr.m2i.dao.DaoException;
import fr.m2i.dao.DaoFactory;
import fr.m2i.model.Produit;

/**
 * Servlet implementation class ModifierProduit
 */
@WebServlet("/ModifierProduit")
public class ModifierProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ProduitDao produitDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierProduit() {
        super();
        produitDao = DaoFactory.getInstance().getProduitDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("produit", produitDao.trouver(id));
		} catch (DaoException e) {
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierProduit.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> erreurs = new HashMap<String, String>();

		int id = Integer.parseInt(request.getParameter("id"));
		String nom = request.getParameter("nomProduit");
		String description = request.getParameter("descriptionProduit");
		
		Double prix = Double.parseDouble(request.getParameter("prixProduit"));
		/*
		 * //Ajout des contrôles if(nom != null) { if(nom.length() < 2 || nom.length() >
		 * 20) { erreurs.put("nomProduit",
		 * "Un nom d'produit doit contenir entre 2 et 20 caractères."); } } else {
		 * erreurs.put("nomProduit", "Merci d'entrer un nom d'produit."); }
		 * 
		 * if(prenom != null) { if(prenom.length() > 20) { erreurs.put("prenomProduit",
		 * "Un prénom d'produit doit avoir maximum 20 caractères."); } }
		 * 
		 * if(telephone != null) { if(telephone.length() > 10) {
		 * erreurs.put("telephoneProduit",
		 * "Un numéro de téléphone doit avoir maximum 10 caractères."); }
		 * if(!telephone.matches("^\\d+$")) { erreurs.put("telephoneProduit",
		 * "Un numéro de téléphone doit contenir uniquement des chiffres."); } } else {
		 * erreurs.put("telephoneProduit", "Merci d'entrer un numéro de téléphone."); }
		 * 
		 * if(email != null) { if(email.length() > 60) { erreurs.put("emailProduit",
		 * "Un email doit avoir maximum 60 caractères."); }
		 * if(!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
		 * erreurs.put("emailProduit", "Merci d'entrer une produit email valide."); } }
		 */
		Produit produit = new Produit();
		try {
			produit = produitDao.trouver(id);
		} catch (DaoException e) {
			e.printStackTrace();
			erreurs.put("produit", "Erreur l'produit n'existe pas...");
		}
		produit.setNom(nom);
		produit.setDescription(description);
		produit.setPrix(prix);
		

		// if(erreurs.isEmpty()) {
		try {
			produitDao.miseAJour(produit);

			// Ajout d'un élément dans la session
			request.getSession().setAttribute("confirmMessage", "L'produit a bien été modifié !");

			response.sendRedirect(request.getContextPath() + "/ListeProduits");
		} catch (DaoException e) {
			e.printStackTrace();
		}
		}

}
