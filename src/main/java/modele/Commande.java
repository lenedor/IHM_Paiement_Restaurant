package modele;


import java.util.ArrayList;
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

    public Commande(String nomClient) {
        this.nomClient = nomClient;
        this.platsChoisis = new ArrayList<Plat>(); 
        this.total = 0; 
    }

    public Commande(String nomClient, List<Plat> platsChoisis) {
        this.nomClient = nomClient;
        this.platsChoisis = platsChoisis;
        this.total = 0; 
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
}
