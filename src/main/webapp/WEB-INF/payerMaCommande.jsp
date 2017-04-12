<%-- 
    Document   : payerMaCommande
    Created on : Apr 12, 2017, 9:33:16 AM
    Author     : bagouc
--%>

<%@page import="modele.Plat"%>
<%@page import="modele.Table"%>
<%@page import="java.util.Iterator"%>
<%@page import="modele.Commande"%>
<%@page import="modele.Commande"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/payer.css"/>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
        <title>Payer ma commande</title>
    </head>
    <body>
        <p></p>
        <p></p>
        <p></p>
        <p></p>
        <p></p>
        <h1>Votre commande : </h1>
        <form action="controleur" accept-charset="utf-8">
            <% int trouve = 0;
                Table table = (Table) request.getAttribute("table");
                Commande commande = null;
                String client = (String)request.getAttribute("client");
                Iterator<Commande> it = table.getCommandes().iterator();
                while (trouve == 0 && it.hasNext()) {
                    commande = it.next();
                    System.out.println("client ds jsp = "+request.getAttribute("client"));
                    if (commande.getNomClient().equals(client)) {
                        trouve = 1;
                    }
                }
                if (trouve == 1) {
                    Iterator<Plat> itPlat = commande.getPlatsChoisis().iterator();
            %>
            <table id="customers">
            <tr>
                <th> </th>
                <th></th>
            </tr>
            <%      while (itPlat.hasNext()) {
                        Plat plat = itPlat.next();
            %>
            
                   
            <!--<p align="left">-->
            <tr>
                    <td><%=plat.getNom()%></td>
                    <td><%=plat.getPrix()%></td>
            </tr>
            <p></p>
            <%
                    }
                }
            %>
                </tr>
            </table>
            <p id="total"> Total : <%=table.getTotal()%> €</p>
            
            <button type="submit" class="button" id="total"><span>Payer</span></button><br>
            <input type="hidden" name="action" value="choisirMoyenPaiement"/>
            <input type="hidden" name="total" value=<%=commande.getTotal()%>/>
        </form>
         <form action="controleur" method="get" align="center">
            <button type="submit" class="button"><span>Retour</span></button><br>
            <input type="hidden" name="action" value="retourChoisirPartPaiement"/>
        </form>
    </body>
</html>
