package controleur;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
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

    /*Variables globales */
    Table table = null;
    String client = null;

    /* créations des tables */
 /* scénario 4 étudiants */
    Table initEtudiants() {
        Table etudiants = new Table("4etudiants");
        List<Plat> platsVincent = new ArrayList<Plat>();
        Commande vincent = new Commande("vincent");
        Plat plat1 = new Plat("Carpaccio et salade verte", 15);
        Plat plat2 = new Plat("Biere pression", 4);
        Plat plat3 = new Plat("Tiramisu", 6);
        vincent.ajoutePlat(plat1);
        vincent.ajoutePlat(plat2);
        vincent.ajoutePlat(plat3);
        etudiants.ajouterCommande(vincent);
        List<Plat> platsOlivier = new ArrayList<Plat>();
        Commande olivier = new Commande("olivier");
        Plat plat4 = new Plat("Salade chevre et croutons", 13);
        Plat plat5 = new Plat("Ile flottante               ", 6);
       // Plat plat6 = new Plat("Verre de Chardonnay         ", 3);
        olivier.ajoutePlat(plat4);
        olivier.ajoutePlat(plat5);
    //    olivier.ajoutePlat(plat6);
        etudiants.ajouterCommande(olivier);
        List<Plat> platsLaurent = new ArrayList<Plat>();
        Commande laurent = new Commande("laurent");
        Plat plat7 = new Plat("Lasagnes", 12);
        Plat plat8 = new Plat("Crumble aux fruits rouges", 6);
        Plat plat9 = new Plat("Verre de Chardonnay ", 3);
        laurent.ajoutePlat(plat7);
        laurent.ajoutePlat(plat8);
        laurent.ajoutePlat(plat9);
        etudiants.ajouterCommande(laurent);
        /*List<Plat> platsNicolas = new ArrayList<Plat>();
        Commande nicolas = new Commande("nicolas");
        Plat plat10 = new Plat("Magret de canard au miel", 16);
        Plat plat11 = new Plat("Tarte aux pommes", 5);
        Plat plat12 = new Plat("Badoit rouge", 3);
        nicolas.ajoutePlat(plat10);
        nicolas.ajoutePlat(plat11);
        nicolas.ajoutePlat(plat12);
        etudiants.ajouterCommande(nicolas);*/
        return etudiants;
    }

    ///////!!!!!\\\\ finir les autres init pr varier les cas
    /* scénario 1 couple */
    Table initCouple() {
        Table couple = new Table("1couple");
        List<Plat> platsPaul = new ArrayList<Plat>();
        Commande paul = new Commande("paul");
        List<Plat> platsLouise = new ArrayList<Plat>();
        Commande louise = new Commande("louise");
        return couple;
    }

    /* scénario 2 parents avec leur enfant */
    Table initParents() {
        Table parents = new Table("2parents1enfant");
        List<Plat> platsLucas = new ArrayList<Plat>();
        Commande lucas = new Commande("lucas");
        List<Plat> platsManon = new ArrayList<Plat>();
        Commande manon = new Commande("manon");
        List<Plat> platsEnzo = new ArrayList<Plat>();
        Commande enzo = new Commande("enzo");
        return parents;
    }

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
            /* Lancer l'initialisation de la table selon le cas que l'on souhaite tester */
            table = initEtudiants();
            /* Choisir le client qui utilise l'ihm */
            client = "vincent";
            table.setTotalCour(0);
            table.passerCommandesDeselect();
            request.getRequestDispatcher("/WEB-INF/choisirPartsPaiement.jsp").forward(request, response);
        } else if (action.equals("retourFinRepas")) {
            request.getRequestDispatcher("/WEB-INF/finRepas.jsp").forward(request, response);
        } else if (action.equals("recapitulatifChoixPlatsPaiement")) {
            request.getRequestDispatcher("/WEB-INF/choisirPartsPaiement.jsp").forward(request, response);
        } else if (action.equals("AjoutOuRetraitCommandeEntiere")) {
            int montant = Integer.parseInt(request.getParameter("total"));
            String nomCommande = request.getParameter("nomCommande");
            Commande commande = table.getCommande(nomCommande);
            if (montant < 0) {
                commande.setSelectionner(0);
            } else {
                commande.setSelectionner(1);
            }
            table.addTotalCour(montant);
            /* Envoi des informations et redirection*/
            request.setAttribute("table", table);
            request.setAttribute("client", client);
            request.getRequestDispatcher("/WEB-INF/paiementPersonnalise.jsp").forward(request, response);
        } else if (action.equals("AjoutOuRetraitPlat")) {
            int montant = Integer.parseInt(request.getParameter("prix"));
            System.out.println("montant= "+montant);
            String nomCommande = request.getParameter("nomCommande");
            String nomPlat = request.getParameter("plat");
            Commande commande = table.getCommande(nomCommande);
            Plat plat = commande.getPlat(nomPlat);
            if (montant < 0) {
                plat.setSelectionne(0);
            } else {
                plat.setSelectionne(1);
            }
            table.addTotalCour(montant);
            /* Envoi des informations et redirection*/
            request.setAttribute("table", table);
            request.setAttribute("client", client);
            request.getRequestDispatcher("/WEB-INF/paiementPersonnalise.jsp").forward(request, response);
        } else if (action.equals("retourChoisirPartPaiement")) {
            request.getRequestDispatcher("/WEB-INF/choisirPartsPaiement.jsp").forward(request, response);
        } else if (action.equals("annulerEtRecommencerPaiement")) {
            request.getRequestDispatcher("/WEB-INF/choisirPartsPaiement.jsp").forward(request, response);
        } else if (action.equals("payerMaCommande")) {
            /* Envoi des informations et redirection*/
            request.setAttribute("table", table);
            request.setAttribute("client", client);
            request.getRequestDispatcher("/WEB-INF/payerMaCommande.jsp").forward(request, response);
        } else if (action.equals("paiementPersonnalise")) {
            /* Envoi des informations et redirection*/
            request.setAttribute("table", table);
            request.setAttribute("client", client);
            request.getRequestDispatcher("/WEB-INF/paiementPersonnalise.jsp").forward(request, response);
        } else if (action.equals("choisirMoyenPaiement")) {
            request.getRequestDispatcher("/WEB-INF/choisirMoyenPaiement.jsp").forward(request, response);
        } else if (action.equals("recapitulatifChoixPlatsPaiement")) {
            /* On récupère la liste des plats selectionnés */
            int total = Integer.parseInt(request.getParameter("total"));
            ArrayList<Plat> plats = new ArrayList<Plat>();
            if (total == table.getTotal()) {
                Iterator<Commande> commandes = table.getCommandes().iterator();
                // A CONTINUER
            }
            request.setAttribute("total", total);
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
