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
import fr.m2i.model.Client;
import fr.m2i.model.Panier;


@WebServlet("/ModifierPanier")
public class ModifierPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private PanierDao panierDao;
    private ClientDao clientDao;
	
    public ModifierPanier() {
        super();
        panierDao = DaoFactory.getInstance().getPanierDao();
        clientDao = DaoFactory.getInstance().getClientDao();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			request.setAttribute("panier", panierDao.trouver(Integer.parseInt((request.getParameter("id")))));
			
			request.setAttribute("listeclients", clientDao.lister());
			
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierPanier.jsp").forward( request,response );
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Panier panier1;
		Client client1;
		try {
			panier1 = panierDao.trouver(Integer.parseInt(request.getParameter("id")));				
			client1 = clientDao.trouver(Integer.parseInt(request.getParameter("client")));
				
		panier1.setClient(client1);
						
		panierDao.miseAJour(panier1);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DaoException e) {
			e.printStackTrace();
		}
							
		response.sendRedirect(request.getContextPath() + "/ListePaniers");
	}

}
