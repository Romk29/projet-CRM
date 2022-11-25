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


@WebServlet("/ModifierProduit")
public class ModifierProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ProduitDao produitDao;
   
    public ModifierProduit() {
        super();
        produitDao = DaoFactory.getInstance().getProduitDao();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("produit", produitDao.trouver(id));
		} catch (DaoException e) {
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierProduit.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String> erreurs = new HashMap<String, String>();
		
		
		String nom = request.getParameter("nomProduit");
		String description = request.getParameter("descriptionProduit");
		int id = Integer.parseInt(request.getParameter("id"));
		double prix = 0;
			
		if (nom.length() == 0) {
			erreurs.put("nomProduit", "Merci d'entrer un nom.");
		}
		
		try {
			prix = Double.parseDouble(request.getParameter("prixProduit"));
		} catch (NumberFormatException e) {
			erreurs.put("prixProduit", "Merci d'entrer 1 pour un prix.");
		}
		
		Produit produit = null;
		
		try {
			produit = produitDao.trouver(id);
		} catch (DaoException e) {
			e.printStackTrace();
			erreurs.put("produit", "Erreur le produit n'existe pas...");
		}
		
		produit.setNom(nom);
		produit.setDescription(description);
		produit.setPrix(prix);
		

		if(erreurs.isEmpty()) {
			
		
		try {
			produitDao.miseAJour(produit);

			request.getSession().setAttribute("confirmMessage", "Le produit a bien été modifié !");
			
		} catch (DaoException e) {
			e.printStackTrace();
		} 
		
		response.sendRedirect(request.getContextPath() + "/ListeProduits");
				
	}	else {
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("produit", produitDao.trouver(id));
			request.setAttribute("erreurs", erreurs);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierProduit.jsp").forward(request, response);
	}
		
			
	}
}
