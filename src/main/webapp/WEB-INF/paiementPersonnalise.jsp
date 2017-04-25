<%-- 
    Document   : paiementPersonnalise
    Created on : Apr 12, 2017, 9:57:33 AM
    Author     : bagouc
--%>

<%@page import="modele.Plat"%>
<%@page import="java.util.Iterator"%>
<%@page import="modele.Commande"%>
<%@page import="modele.Table"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/payerPlusieurs.css"/>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
        <title>Paiement personnalisé</title>
    </head>
    <body>
        <h1>Sélectionnez les items que vous souhaitez payer : </h1>



        <%  Table table = (Table) request.getAttribute("table");
            Commande commande = null;
            Iterator<Commande> it = table.getCommandes().iterator();
            while (it.hasNext()) {
                commande = it.next();
                Iterator<Plat> itPlat = commande.getPlatsChoisis().iterator();
                String toutSelectionner = null;
                int montant = 0;
                if (commande.getSelectionner() == 0) {
                    toutSelectionner = "Tout Sélectionner";
                    montant = commande.getTotal();
                } else {
                    toutSelectionner = "Tout Désélectionner";
                    montant = -commande.getTotal();
                }

        %>
        <h4>Commande de <%=commande.getNomClient()%>:</h4>
        <form action="controleur" accept-charset="utf-8">
            <table id="customers">
                <!--                <tr>
                                    <th> </th>
                                    <th></th>
                                </tr>-->
                <%      while (itPlat.hasNext()) {
                        Plat plat = itPlat.next();
                        String selectionner = null;
                        int montant1 = 0;
                        if (plat.getSelectionne() == 0) {
                            selectionner = "Sélectionner";
                            montant1 = plat.getPrix();
                        } else if (plat.getSelectionne() == 1 && plat.getNomSelectionne() == session.getAttribute("nom")){
                            selectionner = "Déselectionner";
                            montant1 = -plat.getPrix();
                        } else{
                            selectionner = "AutrePersonne";
                        }
                        
                %>

                <!--<p align="left">-->
                <tr>
                    <td><%=plat.getNom()%></td>
                    <td><%=plat.getPrix()%>€</td>
                    <% if (!selectionner.equals("AutrePersonne")){%>
                    <td>
                        <form action="controleur" accept-charset="utf-8">
                            <button type="submit" ><%=selectionner%></button>
                            <input type="hidden" name="action" value="AjoutOuRetraitPlat"/>
                            <input type="hidden" name="prix" value="<%=montant1%>"/>
                            <input type="hidden" name="idPlat" value="<%=plat.getId()%>"/>
                            <input type="hidden" name="plat" value="<%=plat.getNom()%>"/>
                            <input type="hidden" name="nomCommande" value="<%=commande.getNomClient()%>"/>
                        </form></td>
                    <%} else{%>
                        <p> Le plat a deja ete selectionne par : <%= plat.getNomSelectionne() %></p>
                    <%}%>
                </tr>

                <%
                    }

                %>

            </table>
            <p id="total"> Total <%=commande.getNomClient()%> : <%=commande.getTotal()%> €</p>
            <form action="controleur" accept-charset="utf-8">
                <button type="submit" class="button" id="total"><span><%=toutSelectionner%></span></button><br>
                <input type="hidden" name="action" value="AjoutOuRetraitCommandeEntiere"/>
                <input type="hidden" name="total" value="<%=montant%>"/>
                <input type="hidden" name="nomCommande" value="<%=commande.getNomClient()%>"/>
            </form>
            <%}
            %>

            <p>Total table :  <%=table.getTotal()%> €</p>
            <form action="controleur" accept-charset="utf-8">
                <% String totaliteNote = null;
                    if (table.getTotal() == table.getTotalCour()) {
                        totaliteNote = "Désélectionner tout"; 
                    } else {
                        totaliteNote = "Régler la totalité de la note"; 
                    }
                %>
                <button type="submit" class="button"><span><%=totaliteNote%></span></button><br>
                <input type="hidden" name="action" value="reglerTotaliteNote"/>
                <input type="hidden" name="total" value="<%=table.getTotal()%>"/>
            </form>
            <p id="total"> Total à payer:  <%=table.getTotalCour()%> €</p>
            <form action="controleur" accept-charset="utf-8">
                <button type="submit" class="button" id="total"><span>Valider</span></button><br>
                <input type="hidden" name="action" value="recapitulatifChoixPlatsPaiement"/>
                <input type="hidden" name="total" value="<%=table.getTotalCour()%>"/>
            </form>
            <form action="controleur" accept-charset="utf-8">
                <button type="submit" class="button"><span>Retour</span></button><br>
                <input type="hidden" name="action" value="retourChoisirPartPaiement"/>
            </form>
            
    </body>
</html>
