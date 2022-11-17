package fr.m2i.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2i.dao.ProduitDao;
import fr.m2i.dao.DaoException;
import fr.m2i.dao.DaoFactory;

/**
 * Servlet implementation class SupprimerProduit
 */
@WebServlet("/SupprimerProduit")
public class SupprimerProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProduitDao produitDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerProduit() {
    	super();
    	produitDao = DaoFactory.getInstance().getProduitDao();
    	
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			produitDao.supprimer(id);
			
			//Ajout d'un élément dans la session
			request.getSession().setAttribute("confirmMessage", "L'produit a bien été supprimé !");
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect( request.getContextPath() + "/ListeProduits" );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
