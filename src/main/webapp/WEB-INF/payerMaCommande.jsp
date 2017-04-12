<%-- 
    Document   : payerMaCommande
    Created on : Apr 12, 2017, 9:33:16 AM
    Author     : bagouc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payer ma commande</title>
    </head>
    <body>
        <h1>Votre commande : </h1>
        <form action="controleur" accept-charset="utf-8">
        <button type="submit" class="button"><span>Payer</span></button><br>
        <input type="hidden" name="action" value="Payer"/>
        <input type="hidden" name="total" value="total"/>
        </form>
    </body>
</html>
