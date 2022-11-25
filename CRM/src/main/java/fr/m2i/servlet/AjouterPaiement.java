package fr.m2i.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2i.dao.ClientDao;
import fr.m2i.dao.DaoException;
import fr.m2i.dao.DaoFactory;
import fr.m2i.dao.PaiementDao;
import fr.m2i.model.Paiement;


/**
 * Servlet implementation class AjouterPaiement
 */
@WebServlet("/AjouterPaiement")
public class AjouterPaiement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PaiementDao paiementDao;
	private ClientDao clientDao;
	
    public AjouterPaiement() {
        super();
        paiementDao = DaoFactory.getInstance().getPaiementDao();
        clientDao = DaoFactory.getInstance().getClientDao();
        

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setAttribute("clients", clientDao.lister());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterPaiement.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String> erreurs = new HashMap<String, String>();
		int idClient = Integer.parseInt(request.getParameter("clientPaiement"));
		
		
		
		String noCarte = request.getParameter("noCartePaiement");
		String codeConfidentiel = request.getParameter("codeConfidentielPaiement");
		String banque = request.getParameter("banquePaiement");

		if (banque == null) {

			erreurs.put("banquePaiment", "Merci d'entrer une banque.");
		}
		Pattern patternAdress1 = Pattern.compile("[0-9]{3}");

		if (!patternAdress1.matcher(codeConfidentiel).matches()) {

			erreurs.put("codeConfidentielPaiement", "votre code confidentiel doit faire 3 choffre");
		}

		Pattern patternAdress = Pattern.compile("[0-9]{16}");
		if (!patternAdress.matcher(noCarte).matches()) {

			erreurs.put("noCartePaiement", "merci de rentrer une carte a 16 chiffre");
		}
		
				
		Paiement paiement = new Paiement();
		if (erreurs.isEmpty()) {		
			try {
				paiement.setClient(clientDao.trouver(idClient));
				paiement.setNoCarte(noCarte);
				paiement.setCodeConfidentiel(codeConfidentiel);
				paiement.setBanque(banque);
				paiementDao.creer(paiement);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			request.setAttribute("adresse", paiement);
			request.setAttribute("erreurs", erreurs);

			this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterPaiement.jsp").forward(request, response);
		}
			
		
		
		}
		
			
		
}