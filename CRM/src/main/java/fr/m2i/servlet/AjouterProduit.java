package fr.m2i.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2i.dao.ClientDao;
import fr.m2i.dao.DaoException;
import fr.m2i.dao.DaoFactory;
import fr.m2i.dao.ProduitDao;
import fr.m2i.model.Produit;

/**
 * Servlet implementation class AjouterProduit
 */
@WebServlet("/AjouterProduit")
public class AjouterProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ProduitDao produitDao;
	private ClientDao clientDao;

    public AjouterProduit() {
        super();
        produitDao = DaoFactory.getInstance().getProduitDao();
        clientDao = DaoFactory.getInstance().getClientDao();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("listeclients", clientDao.lister());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/ajouterProduit.jsp").forward( request,response );

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String> erreurs = new HashMap<String, String>();
			
			
		String nom = request.getParameter("nomProduit");
		String description = request.getParameter("descriptionProduit");
		
		double prix = 0;
			
		if (nom.length() == 0) {
			erreurs.put("nomProduit", "Merci d'entrer un nom.");
		}
		
		try {
			prix = Double.parseDouble(request.getParameter("prixProduit"));
		} catch (NumberFormatException e) {
			erreurs.put("prixProduit", "Merci d'entrer 1 pour un prix.");
		}
				
			Produit produit = new Produit();
			produit.setNom(nom);
			produit.setPrix(prix);
			produit.setDescription(description);
			
		if (erreurs.isEmpty()) {
				
			try {
			
			produitDao.creer(produit);
			request.getSession().setAttribute("confirmMessage", "Le produit a bien été ajouté !");
			
			response.sendRedirect(request.getContextPath() + "/ListeProduits");
		} catch(DaoException e) {
			e.printStackTrace();
		}
		
		} else {
			request.setAttribute("produit", produit);
			request.setAttribute("erreurs", erreurs);

			this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterProduit.jsp").forward(request, response);
		}
			
	}

}

