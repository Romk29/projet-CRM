package fr.m2i.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2i.dao.DaoException;
import fr.m2i.dao.DaoFactory;
import fr.m2i.dao.PanierDao;


@WebServlet("/SupprimerPanier")
public class SupprimerPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private PanierDao panierDao;
	
    public SupprimerPanier() {
        super();
        panierDao = DaoFactory.getInstance().getPanierDao();
    }
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			int id = Integer.parseInt((request.getParameter("id")));
				
			panierDao.supprimer(id);
			
			request.getSession().setAttribute("confirmMessage", "Le panier a bien été supprimé !");
			
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/ListePaniers");
		
		
	}

	
	

}
