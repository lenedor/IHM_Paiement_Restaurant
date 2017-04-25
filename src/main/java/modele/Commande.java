package modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author bagouc
 */
public class Commande {

    private String nomClient;
    private List<Plat> platsChoisis;
    private int total;
    private int selectionner; // 0 pas select , 1 = select
    private String nomSelectionne;

    public Commande(String nomClient) {
        this.nomClient = nomClient;
        this.platsChoisis = new ArrayList<Plat>();
        this.total = 0;
        this.selectionner = 0;
    }

    public Commande(String nomClient, List<Plat> platsChoisis) {
        this.nomClient = nomClient;
        this.platsChoisis = platsChoisis;
        this.total = 0;
        this.selectionner = 0;
    }

    public int getSelectionner() {
        return selectionner;
    }

    public String getNomClient() {
        return nomClient;
    }

    public List<Plat> getPlatsChoisis() {
        return platsChoisis;
    }

    public int getTotal() {
        return total;
    }

    public void ajoutePlat(Plat plat) {
        this.platsChoisis.add(plat);
        this.total += plat.getPrix();
    }

    public void setSelectionner(int selectionner) {
        this.selectionner = selectionner;
    }
    
    public void setNomSelectionne(String nom){
        this.nomSelectionne = nom;
    }
    
    public String getNomSelectionne(){
        return this.nomSelectionne;
    }

    public Plat getPlat(String nom) {
        int trouve = 0;
        Iterator<Plat> it = this.platsChoisis.iterator();
        while (trouve == 0 && it.hasNext()) {
            Plat plat = it.next();
            if (plat.getNom().equals(nom)) {
                trouve = 1;
                return plat;
            }
        }
        return null;
    }

    public void passerPlatsDeselect() {
        Iterator<Plat> it = this.platsChoisis.iterator();
        while (it.hasNext()) {
            it.next().setSelectionne(0);
        }
    }
    
    public void passerPlatsSelect() {
        Iterator<Plat> it = this.platsChoisis.iterator();
        while (it.hasNext()) {
            it.next().setSelectionne(1);
        }
    }
    
    // dit si tous les plats d'une commande sont select
    public boolean tousPlatsSelect() {
        Iterator<Plat> it = this.platsChoisis.iterator(); 
        while (it.hasNext()) {
            if (it.next().getSelectionne() == 0) {
                return false; 
            }
        }
        return true; 
    }
    
    public boolean tousPlatsDeselect() {
        Iterator<Plat> it = this.platsChoisis.iterator(); 
        while (it.hasNext()) {
            if (it.next().getSelectionne() == 1) {
                return false; 
            }
        }
        return true; 
    }
}
