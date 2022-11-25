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

/**
 * Servlet implementation class ListeClients
 */
@WebServlet("/ListeClients")
public class ListeClients extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ClientDao clientDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeClients() {
        super();
        clientDao = DaoFactory.getInstance().getClientDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setAttribute("clients", clientDao.lister());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/listeClients.jsp").forward(request, response);
	}

}
