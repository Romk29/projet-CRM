package fr.m2i.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2i.dao.DaoException;
import fr.m2i.dao.DaoFactory;
import fr.m2i.dao.PaiementDao;

/**
 * Servlet implementation class listePaiements
 */
@WebServlet("/ListePaiements")
public class ListePaiements extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PaiementDao paiementDao;
	
	
    public ListePaiements() {
        super();
        paiementDao  = DaoFactory.getInstance().getPaiementDao();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setAttribute("paiements", paiementDao.lister());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/listePaiements.jsp").forward(request, response);
		
		request.getSession().removeAttribute("confirmMessage");

	}
	

	


}
