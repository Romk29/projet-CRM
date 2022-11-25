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
 * Servlet implementation class SupprimerPaiement
 */
@WebServlet("/SupprimerPaiement")
public class SupprimerPaiement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PaiementDao paiementDao;
	

    public SupprimerPaiement() {
        super();
        paiementDao = DaoFactory.getInstance().getPaiementDao();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			paiementDao.supprimer(id);
			request.getSession().setAttribute("confirmMessage", "Le paiement a bien été supprimé !");

		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect( request.getContextPath() + "/ListePaiements" );

	}


}
