/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bagouc
 */
public class Table {
    private String nom; 
    private List<Commande> commandes;
    private int total; 

    public Table(String nom) {
        this.nom = nom; 
        this.commandes = new ArrayList<Commande>(); 
        this.total = 0; 
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
    
}
