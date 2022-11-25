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
 * Servlet implementation class DetailsPaiement
 */
@WebServlet("/DetailsPaiement")
public class DetailsPaiement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PaiementDao paiementDao;
	
	
    public DetailsPaiement() {
        super();
        paiementDao = DaoFactory.getInstance().getPaiementDao();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("paiement", paiementDao.trouver(id));
		} catch (DaoException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/detailsPaiement.jsp").forward(request, response);

	}


}
