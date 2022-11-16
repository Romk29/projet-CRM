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


@WebServlet("/ListePaniers")
public class ListePaniers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private PanierDao panierDao;
	
	
	
    public ListePaniers() {
        super();
        panierDao = DaoFactory.getInstance().getPanierDao();        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
try {
			
			request.setAttribute("listepaniers", panierDao.lister());
			
		} catch (DaoException e) {
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher( "/WEB-INF/listePaniers.jsp").forward( request,response );
		
	}

	
	

}
