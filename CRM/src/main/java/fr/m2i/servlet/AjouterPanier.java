package fr.m2i.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2i.dao.DaoException;
import fr.m2i.dao.ClientDao;
import fr.m2i.dao.DaoFactory;
import fr.m2i.dao.PanierDao;
import fr.m2i.model.Panier;


@WebServlet("/AjouterPanier")
public class AjouterPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PanierDao panierDao;
	private ClientDao clientDao;
    
    public AjouterPanier() {
        super();
        panierDao = DaoFactory.getInstance().getPanierDao();
        clientDao = DaoFactory.getInstance().getClientDao();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setAttribute("listeclients", clientDao.lister());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/ajouterPanier.jsp").forward( request,response );
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Panier panier1 = new Panier();
		
		try {
			
			panier1.setClient(clientDao.trouver(Integer.parseInt(request.getParameter("client"))));
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		try {
			panierDao.creer(panier1);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/ListePaniers");
	}

}
