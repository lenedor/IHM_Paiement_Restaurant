package controleur;


import java.io.*;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

/**
 * Le contrôleur de l'application.
 */
@WebServlet(name = "Controleur", urlPatterns = {"/controleur"})
public class Controleur extends HttpServlet {

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

 