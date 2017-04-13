<%-- 
    Document   : recapitulatifChoixPlatsPaiement
    Created on : Apr 12, 2017, 10:02:45 AM
    Author     : bagouc
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="modele.Plat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/payerPlusieurs.css"/>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
        <title>RécapitulatifPaiement</title>
    </head>
    <body>
        <h1>Récapitulatif</h1>
        <% List<Plat> plats = (List<Plat>) request.getAttribute("plats");
            int total = (int) request.getAttribute("total");
            Iterator<Plat> itPlat = plats.iterator();
        %>
        <form action="controleur" accept-charset="utf-8">
            <table id="customers">
                <!--                <tr>
                                    <th> </th>
                                    <th></th>
                                </tr>-->
                <%      while (itPlat.hasNext()) {
                        Plat plat = itPlat.next();
                %>

                <!--<p align="left">-->
                <tr>
                    <td><%=plat.getNom()%></td>
                    <td><%=plat.getPrix()%>€</td>

                </tr>

                <%
                    }

                %>

            </table>
            <p></p>
            <p></p>
            <p></p>
        </form> 
        <form action="controleur" accept-charset="utf-8">
            <button type="submit" class="button"><span>Modifier</span></button><br>
            <input type="hidden" name="action" value="paiementPersonnalise"/>
        </form>   
        <p id="total"> Total :  <%=total%> €</p>
        <form action="controleur" accept-charset="utf-8">
            <button type="submit" class="button" id="total"><span>Régler</span></button><br>
            <input type="hidden" name="action" value="choisirMoyenPaiement"/>
            <input type="hidden" name="total" value="<%=total%>"/>
        </form>
    </body>
</html>
