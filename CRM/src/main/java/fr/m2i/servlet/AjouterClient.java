package fr.m2i.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2i.dao.AdresseDao;
import fr.m2i.dao.ClientDao;
import fr.m2i.dao.DaoException;
import fr.m2i.dao.DaoFactory;
import fr.m2i.model.Adresse;
import fr.m2i.model.Client;


/**
 * Servlet implementation class AjouterClient
 */
@WebServlet("/AjouterClient")
public class AjouterClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ClientDao clientDao;
	private AdresseDao adresseDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterClient() {
        super();
        clientDao = DaoFactory.getInstance().getClientDao();
        adresseDao = DaoFactory.getInstance().getAdresseDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setAttribute("adresses", adresseDao.lister());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterClient.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nom = request.getParameter("nomClient");
		String prenom = request.getParameter("prenomClient");
		int idAdresse = Integer.parseInt(request.getParameter("adresseClient"));
		String nomSociete = request.getParameter("nomSocieteClient");
		String mail = request.getParameter("mailClient");
		String telephone = request.getParameter("telephoneClient");
		int etat = Integer.parseInt(request.getParameter("etatClient"));
		int genre = Integer.parseInt(request.getParameter("genreClient"));
		
		Client client = new Client();
		try {
			client.setNom(nom);
			client.setPrenom(prenom);
			Adresse adresse = adresseDao.trouver(idAdresse);
			client.setAdresse(adresse);
			client.setNomSociete(nomSociete);
			client.setMail(mail);
			client.setTelephone(telephone);
			client.setEtat(etat);
			client.setGenre(genre);
			
			clientDao.creer(client);
		} catch(DaoException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/ListeClients");
		
	}
				
}
