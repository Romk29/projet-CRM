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
		
		String nom = request.getParameter("nom");
		double prix = Double.parseDouble(request.getParameter("prix"));
		String description = request.getParameter("description");
		
		if(nom != null) {
			if(nom.length() < 2 || nom.length() > 50) {
				erreurs.put("nomProduit", "Un nom doit contenir entre 2 et 50 caractères.");
			}
		} else {
			erreurs.put("nomProduit", "Merci d'entrer un nom.");
		}
		
		if(description != null) {
			if(description.length() > 2 || description.length() > 200 ) {
				erreurs.put("descriptionProduit", "Une description doit contenir entre 2 et 200 caractères.");
			}
			
		if(prix == 0) {
			erreurs.put("prixProduit", "Merci d'entrer un prix");
		}
		


			Produit produit = new Produit();
			produit.setNom(nom);
			produit.setPrix(prix);
			produit.setDescription(description);

			if(erreurs.isEmpty()) {
				try {
					produitDao.creer(produit);
					
					request.getSession().setAttribute("confirmMessage", "Le produit a bien été ajouté !");
				} catch (DaoException e) {
					e.printStackTrace();
				}
				
				response.sendRedirect( request.getContextPath() + "/ListeProduits" );
			} 
				request.setAttribute("produit", produit);
				request.setAttribute("erreurs", erreurs);
				
				this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterProduit.jsp").forward(request, response);
		}
	}

}

