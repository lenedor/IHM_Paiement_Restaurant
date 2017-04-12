<%-- 
    Document   : choisirPartsPaiement
    Created on : Apr 12, 2017, 10:23:44 AM
    Author     : lenetd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Choisir parts paiement</title>
    </head>
    <body>
        <h1></h1>
        <form action="controleur" method="get" align="center">
            <button type="submit" class="button"><span>Ma Part</span></button><br>
            <input type="hidden" name="action" value="payerMaCommande"/>
        </form>
        <form action="controleur" method="get" align="center">
            <button type="submit" class="button"><span>Paiement customisé</span></button><br>
            <input type="hidden" name="action" value="paiementPersonnalise"/>
        </form>    
    </body>
</html>
