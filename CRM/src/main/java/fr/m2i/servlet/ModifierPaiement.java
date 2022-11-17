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
import fr.m2i.dao.PaiementDao;
import fr.m2i.model.Paiement;


/**
 * Servlet implementation class ModifierPaiement
 */
@WebServlet("/ModifierPaiement")
public class ModifierPaiement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PaiementDao paiementDao;
	private ClientDao clientDao;
	
    public ModifierPaiement() {
        super();
        paiementDao = DaoFactory.getInstance().getPaiementDao();
        clientDao = DaoFactory.getInstance().getClientDao();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("paiement", paiementDao.trouver(id));
			request.setAttribute("clients", clientDao.lister());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierPaiement.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		int idClient = Integer.parseInt(request.getParameter("clientPaiement"));
		String noCarte = request.getParameter("noCartePaiement");
		String codeConfidentiel = request.getParameter("codeConfidentielPaiement");
		String banque = request.getParameter("banquePaiement");
	
		try {
			Paiement paiement = paiementDao.trouver(id);
			paiement.setClient(clientDao.trouver(idClient));
			paiement.setNoCarte(noCarte);
			paiement.setCodeConfidentiel(codeConfidentiel);
			paiement.setBanque(banque);
			
			paiementDao.miseAJour(paiement);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect( request.getContextPath() + "/ListePaiements" );		
	}

}
