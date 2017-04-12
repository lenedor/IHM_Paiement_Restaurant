<%-- 
    Document   : paiementPersonnalise
    Created on : Apr 12, 2017, 9:57:33 AM
    Author     : bagouc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Paiement personnalisé</title>
    </head>
    <body>
        <h1>Sélectionnez les items que vous souhaitez payer : </h1>
        <form action="controleur" accept-charset="utf-8">
        <button type="submit" class="button"><span>Valider</span></button><br>
        <input type="hidden" name="action" value="recapitulatifChoixPlatsPaiement"/>
        <input type="hidden" name="total" value="total"/>
        <input type="hidden" name="listePlats" value="listePlats"/>
        </form>
    </body>
</html>
