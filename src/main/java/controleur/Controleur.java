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
        return etudiants;
    }

    ///////!!!!!\\\\ finir les autres init pr varier les cas
    /* scénario 1 couple */
    Table initCouple() {
        Table couple = new Table("1couple");
        List<Plat> platsPaul = new ArrayList<Plat>();
        Commande paul = new Commande("paul");
        Plat plat10 = new Plat("Magret de canard au miel", 16);
        Plat plat11 = new Plat("Tarte aux pommes", 5);
        Plat plat12 = new Plat("Badoit rouge", 3);
        paul.ajoutePlat(plat10);
        paul.ajoutePlat(plat11);
        paul.ajoutePlat(plat12);
        couple.ajouterCommande(paul);
        List<Plat> platsLouise = new ArrayList<Plat>();
        Commande louise = new Commande("louise");
        Plat plat13 = new Plat("Feuilleté au chèvre chaud", 13);
        Plat plat14 = new Plat("Panacotta fruits rouges", 5);
        Plat plat15 = new Plat("Bouteille vin rosé", 3);
        louise.ajoutePlat(plat13);
        louise.ajoutePlat(plat14);
        louise.ajoutePlat(plat15);
        couple.ajouterCommande(louise);
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

        HttpSession session = request.getSession();

        if (action == null) {
            request.getRequestDispatcher("/WEB-INF/choixPersonne.jsp").forward(request, response);
        } else if (action.equals("choisirPartsPaiement")) {
            /* Lancer l'initialisation de la table selon le cas que l'on souhaite tester */
            // table = initEtudiants();
            table = initCouple();
            table.attribuerIdPlat();
            /* Choisir le client qui utilise l'ihm */
            //client = "vincent";
            //client = "louise"; 
            table.setTotalCour(0);
            table.passerCommandesDeselect();
            request.getRequestDispatcher("/WEB-INF/choisirPartsPaiement.jsp").forward(request, response);

        } else if (action.equals("retourFinRepas")) {
            request.getRequestDispatcher("/WEB-INF/finRepas.jsp").forward(request, response);

        } else if (action.equals("AjoutOuRetraitCommandeEntiere")) {
            int montant = Integer.parseInt(request.getParameter("total"));
            String nomCommande = request.getParameter("nomCommande");
            Commande commande = table.getCommande(nomCommande);
            if (montant < 0) {
                // on veut enlever une commande
                commande.setSelectionner(0);
                commande.setNomSelectionne(null);
                if (commande.tousPlatsSelect()) {
                    commande.passerPlatsDeselect();
                    table.addTotalCour(montant);
                } else {
                    Iterator<Plat> itPlat = commande.getPlatsChoisis().iterator();
                    while (itPlat.hasNext()) {
                        Plat p = itPlat.next();
                        if (p.getSelectionne() == 1) {
                            p.setSelectionne(0);
                            p.setNomSelectionne(null);
                            table.addTotalCour(-p.getPrix());
                        }
                    }
                }
            } else {
                // on veut selectionner une commande
                commande.setSelectionner(1);
                commande.setNomSelectionne(null);
                if (commande.tousPlatsDeselect()) {
                    commande.passerPlatsSelect(session.getAttribute("nom").toString());
                    table.addTotalCour(montant);
                } else {
                    Iterator<Plat> itPlat = commande.getPlatsChoisis().iterator();
                    while (itPlat.hasNext()) {
                        Plat p = itPlat.next();
                        if (p.getSelectionne() == 0) {
                            p.setSelectionne(1);
                            p.setNomSelectionne(session.getAttribute("nom").toString());
                            table.addTotalCour(p.getPrix());
                        }
                    }
                }
            }

            /* Envoi des informations et redirection*/
            request.setAttribute("table", table);
            request.setAttribute("client", session.getAttribute("nom").toString());
            request.getRequestDispatcher("/WEB-INF/paiementPersonnalise.jsp").forward(request, response);

        } else if (action.equals("AjoutOuRetraitPlat")) {
            // int id = Integer.parseInt("idPlat"); 
            int montant = Integer.parseInt(request.getParameter("prix"));
            String nomCommande = request.getParameter("nomCommande");
            String nomPlat = request.getParameter("plat");
            Commande commande = table.getCommande(nomCommande);
            Plat plat = commande.getPlat(nomPlat);

            if (montant < 0) {
                // on veut enlever un plat
                plat.setSelectionne(0);
                plat.setNomSelectionne(null);
                if (commande.tousPlatsDeselect()) {
                    commande.setSelectionner(0);
                    commande.setNomSelectionne(null);
                }
            } else {
                // on veut selectionner un plat
                plat.setSelectionne(1);
                plat.setNomSelectionne(session.getAttribute("nom").toString());
                if (commande.tousPlatsSelect()) {
                    commande.setSelectionner(1);
                    commande.setNomSelectionne(session.getAttribute("nom").toString());

                    if (montant < 0) {
                        // on veut enlever un plat
                        plat.setSelectionne(0);
                        if (commande.tousPlatsDeselect()) {
                            commande.setSelectionner(0);
                        }
                    } else {
                        // on veut selectionner un plat
                        plat.setSelectionne(1);
                        if (commande.tousPlatsSelect()) {
                            commande.setSelectionner(1);
                        }
                    }
                    table.addTotalCour(montant);
                }
            }

            /* Envoi des informations et redirection */
            request.setAttribute("table", table);
            request.setAttribute("client", session.getAttribute("nom").toString());
            request.getRequestDispatcher("/WEB-INF/paiementPersonnalise.jsp").forward(request, response);

        } else if (action.equals("retourChoisirPartPaiement")) {
            request.getRequestDispatcher("/WEB-INF/choisirPartsPaiement.jsp").forward(request, response);

        } else if (action.equals("annulerEtRecommencerPaiement")) {
            request.getRequestDispatcher("/WEB-INF/choisirPartsPaiement.jsp").forward(request, response);

        } else if (action.equals("reglerTotaliteNote")) {
            table.passerCommandesSelect(session.getAttribute("nom").toString());
            table.setTotalCour(table.getTotal());
            /* Envoi des informations et redirection*/
            request.setAttribute("table", table);
            request.setAttribute("client", session.getAttribute("nom").toString());
            request.getRequestDispatcher("/WEB-INF/paiementPersonnalise.jsp").forward(request, response);

        } else if (action.equals("payerMaCommande")) {
            /* Envoi des informations et redirection*/
            request.setAttribute("table", table);
            request.setAttribute("client", session.getAttribute("nom").toString());
            request.getRequestDispatcher("/WEB-INF/payerMaCommande.jsp").forward(request, response);

        } else if (action.equals("paiementPersonnalise")) {
            /* Envoi des informations et redirection*/
            request.setAttribute("table", table);
            request.setAttribute("client", session.getAttribute("nom").toString());
            request.getRequestDispatcher("/WEB-INF/paiementPersonnalise.jsp").forward(request, response);

        } else if (action.equals("choisirMoyenPaiement")) {
            request.getRequestDispatcher("/WEB-INF/choisirMoyenPaiement.jsp").forward(request, response);

        } else if (action.equals("recapitulatifChoixPlatsPaiement")) {
            /* On récupère la liste des plats selectionnés */
            int total = Integer.parseInt(request.getParameter("total"));
            ArrayList<Plat> plats = new ArrayList<Plat>();
            Iterator<Commande> commandes = table.getCommandes().iterator();
            while (commandes.hasNext()) {
                Commande commande = commandes.next();
                Iterator<Plat> itPlats = commande.getPlatsChoisis().iterator();
                while (itPlats.hasNext()) {
                    Plat p = itPlats.next();
                    if (p.getSelectionne() == 1) {
                        plats.add(p);
                    }
                }
            }
            request.setAttribute("total", total);
            request.setAttribute("plats", plats);
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

        if (action.equals("choixPersonne")) {
            HttpSession session = request.getSession();
            session.setAttribute("nom", request.getParameter("nom"));
            request.getRequestDispatcher("/WEB-INF/finRepas.jsp").forward(request, response);
        } else {
            invalidParameters(request, response);
        }
    }
}
