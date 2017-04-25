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
    int id; 
    private String nom; 
    private int prix; 
    private int selectionne; // 0 = non select 1 = select
    private String nomSelectionne = null;

    public Plat(String nom, int prix) {
        this.nom = nom;
        this.prix = prix;
        this.selectionne = 0; 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    public void setNomSelectionne(String s){
        this.nomSelectionne = s;
    }
    
    public String getNomSelectionne(){
        return this.nomSelectionne;
    } 
    
}
