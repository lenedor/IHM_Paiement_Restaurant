/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author bagouc
 */
public class Table {
    private String nom; 
    private List<Commande> commandes;
    private int total; 
    private int totalCour; 
    private int montantCour; 

    public Table(String nom) {
        this.nom = nom; 
        this.commandes = new ArrayList<Commande>(); 
        this.total = 0; 
        this.totalCour = 0; 
        this.montantCour = 0;
    }

    public int getMontantCour() {
        return montantCour;
    }

    public void setMontantCour(int montantCour) {
        this.montantCour = montantCour;
    }

    public void setTotalCour(int totalCour) {
        this.totalCour = totalCour;
    }

    public int getTotalCour() {
        return totalCour;
    }

    public String getNom() {
        return nom;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public int getTotal() {
        return total;
    }
    
    public void ajouterCommande(Commande commande) {
        this.commandes.add(commande); 
        this.total += commande.getTotal(); 
    }
    
    public void addTotalCour (int x) {
        this.totalCour += x; 
    }
    
    public Commande getCommande(String nom) {
        int trouve = 0; 
        Iterator<Commande> it = this.commandes.iterator(); 
        while (trouve == 0 && it.hasNext()) {
            Commande commande = it.next(); 
            if (commande.getNomClient().equals(nom)) {
                trouve = 1; 
                return commande; 
            }
        }
        return null; 
    }
    
    public void passerCommandesDeselect() {
        Iterator<Commande> it = this.commandes.iterator(); 
        while (it.hasNext()) {
            Commande commande = it.next();
            commande.passerPlatsDeselect();
            commande.setSelectionner(0);            
        }
    }
    
}
