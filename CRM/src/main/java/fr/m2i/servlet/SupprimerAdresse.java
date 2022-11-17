package fr.m2i.servlet;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2i.dao.AdresseDao;
import fr.m2i.dao.DaoException;
import fr.m2i.dao.DaoFactory;


@WebServlet("/SupprimerAdresse")
public class SupprimerAdresse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdresseDao adresseDao;

    public SupprimerAdresse() {
        super();
        adresseDao = DaoFactory.getInstance().getAdresseDao();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			adresseDao.supprimer(id);
			
			
			request.getSession().setAttribute("confirmMessage", "L'adresse a bien été supprimée !");
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect( request.getContextPath() + "/ListeAdresses" );
	}

	

}
