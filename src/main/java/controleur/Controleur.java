package controleur;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import modele.Commande;
import modele.Plat;
import modele.Table;

/**
 * Le contrôleur de l'application.
 */
@WebServlet(name = "Controleur", urlPatterns = {"/controleur"})
public class Controleur extends HttpServlet {

    /* créations des tables */
 /* scénario 4 étudiants */
    Table etudiant = new Table("4etudiants");
    List<Plat> platsVincent = new ArrayList<Plat>();
    Commande vincent = new Commande("vincent");
    List<Plat> platsOlivier = new ArrayList<Plat>();
    Commande olivier = new Commande("olivier");
    List<Plat> platsLaurent = new ArrayList<Plat>();
    Commande laurent = new Commande("laurent");
    List<Plat> platsNicolas = new ArrayList<Plat>();
    Commande nicolas = new Commande("nicolas");

    /* scénario 1 couple */
    Table couple = new Table("1couple");
    List<Plat> platsPaul = new ArrayList<Plat>();
    Commande paul = new Commande("paul"); 
    List<Plat> platsLouise = new ArrayList<Plat>();
    Commande louise = new Commande("louise");

    /* scénario 2 parents avec leur enfant */
    Table parents = new Table("2parents1enfant");
    List<Plat> platsLucas = new ArrayList<Plat>();
    Commande lucas = new Commande("lucas");
    List<Plat> platsManon = new ArrayList<Plat>();
    Commande manon = new Commande("manon");
    List<Plat> platsEnzo = new ArrayList<Plat>();
    Commande enzo = new Commande("enzo");

    @Resource(name = "jdbc/Paiement_restaurant")
    private DataSource ds;

    /* pages d’erreurs */
    private void invalidParameters(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/controleurErreur.jsp").forward(request, response);
    }

    /**
     * Actions possibles en GET : afficher (correspond à l’absence du param),
     * getOuvrage.
     */
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if (action == null) {
            request.getRequestDispatcher("/WEB-INF/finRepas.jsp").forward(request, response);
        } else if (action.equals("choisirPartsPaiement")) {
            request.getRequestDispatcher("/WEB-INF/choisirPartsPaiement.jsp").forward(request, response);
        } else if (action.equals("payerMaCommande")) {
            request.getRequestDispatcher("/WEB-INF/payerMaCommande.jsp").forward(request, response);
        } else if (action.equals("paiementPersonnalise")) {
            request.getRequestDispatcher("/WEB-INF/paiementPersonnalise.jsp").forward(request, response);
        } else if (action.equals("choisirMoyenPaiement")) {
            request.getRequestDispatcher("/WEB-INF/choisirMoyenPaiement.jsp").forward(request, response);
        } else if (action.equals("recapitulatifChoixPlatsPaiement")) {
            request.getRequestDispatcher("/WEB-INF/recapitulatifChoixPlatsPaiement.jsp").forward(request, response);
        } else if (action.equals("serveurArriveEspece")) {
            request.getRequestDispatcher("/WEB-INF/serveurArriveEspece.jsp").forward(request, response);
        } else if (action.equals("serveurArriveCarte")) {
            request.getRequestDispatcher("/WEB-INF/serveurArriveCarte.jsp").forward(request, response);
        } else {
            invalidParameters(request, response);
        }

    }

    /**
     * Actions possibles en POST
     */
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if (true) {

        } else {
            invalidParameters(request, response);
        }
    }
}
