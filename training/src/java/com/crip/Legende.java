
package com.crip;

public class Legende {
    private String couleur;
    private String libelle;
    private double montant;

    public Legende(String couleur, String libelle, double montant) {
        this.couleur = couleur;
        this.libelle = libelle;
        this.montant = montant;
    }

    
    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
    
    
}
