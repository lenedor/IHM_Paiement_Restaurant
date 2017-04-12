package modele;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bagouc
 */
public class Plat {
    private String nom; 
    private int prix; 
    private int selectionne; // 0 = non select 1 = select

    public Plat(String nom, int prix) {
        this.nom = nom;
        this.prix = prix;
        this.selectionne = 0; 
    }

    public String getNom() {
        return nom;
    }

    public int getPrix() {
        return prix;
    }
    
    public void setSelectionne(int x) {
        this.selectionne = x; 
    }
    

    public int getSelectionne() {
        return selectionne;
    }
}