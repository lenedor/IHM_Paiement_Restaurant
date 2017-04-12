<%-- 
    Document   : choisirMoyenPaiement
    Created on : Apr 12, 2017, 9:47:24 AM
    Author     : bagouc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Choisir paiement</title>
    </head>
    <body>
        <h1>Choisissez votre moyen de paiement : </h1>
        <form action="controleur" accept-charset="utf-8">
            <button type="submit" class="button"><span>Especes</span></button><br>
            <input type="hidden" name="action" value="serveurArriveEspece"/>
            <input type="hidden" name="total" value=${total}/>
        </form>
        <p></p>
        <p></p>
        <p></p>
        <form action="controleur" accept-charset="utf-8">
            <button type="submit" class="button"><span>Carte Bancaire</span></button><br>
            <input type="hidden" name="action" value="serveurArriveCarte"/>
            <input type="hidden" name="total" value=${total}/>
        </form>
        <p></p>
        <p></p>
        <p></p>
        <form action="controleur" accept-charset="utf-8">
            <button type="submit" class="button"><span>Annuler et recommencer la procédure de paiement</span></button><br>
            <input type="hidden" name="action" value="annulerEtRecommencerPaiement"/>
        </form>

    </body>
</html>
