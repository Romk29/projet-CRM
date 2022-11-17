package fr.m2i.servlet;

import java.io.IOException;
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
		try {
		
		String nom = request.getParameter("nom");
		double prix = Double.parseDouble(request.getParameter("prix"));
		String description = request.getParameter("description");

			Produit produit = new Produit();
			produit.setNom(nom);
			produit.setPrix(prix);
			produit.setDescription(description);
			
			produitDao.creer(produit);
			
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect( request.getContextPath() + "/ListeProduits" );	

	}

}
