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


@WebServlet("/ListeAdresses")
public class ListeAdresses extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdresseDao adresseDao;

    public ListeAdresses() {
        super();
        adresseDao = DaoFactory.getInstance().getAdresseDao();
    }


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			request.setAttribute("adresses", adresseDao.lister());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		
		

		this.getServletContext().getRequestDispatcher("/WEB-INF/listeAdresses.jsp").forward(request, response);

		 request.getSession().removeAttribute("confirmMessage");
	}


}