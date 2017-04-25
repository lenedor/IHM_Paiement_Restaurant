<%-- 
    Document   : choixPersonne
    Created on : Apr 25, 2017, 1:54:14 PM
    Author     : bagouc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Choix Personne</title>
    </head>
    
    <body>
    <form action="controleur" method="post" accept-charset="UTF-8">
        <label><b>Nom</b></label>
        <input type="text" name="nom" required>
        <button type="submit" class="button"><span>Valider</span></button><br>
        <input type="hidden" name="action" value="choixPersonne"/>
      </form>
    </body>
</html>
