<%-- 
    Document   : finRepas
    Created on : Apr 12, 2017, 9:16:33 AM
    Author     : lenetd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page fin repas</title>
        
        <style>
        #buttonMenu{
            left : 40%;
        }
        </style>
        
    </head>
    <body>
        <h1>Avez-vous encore faim ?</h1>
        <form action="controleur" method="get">
            <button type="submit" class="button" id="buttonMenu"><span>Menu</span></button><br>
        </form>
        <form action="controleur" method="get">
            <button type="submit" class="button" id="buttonDessert"><span>Dessert</span></button><br>
        </form>
        <form action="controleur" method="get">
            <button type="submit" class="button" id="buttonCafe"><span>Café</span></button><br>
        </form>
        
        <h1>Souhaitez-vous régler ?</h1>
        <form action="controleur" method="get" align="center">
            <button type="submit" class="button"><span>Paiement</span></button><br>
            <input type="hidden" name="action" value="choisirPartsPaiement"/>
        </form>
    </body>
</html>
