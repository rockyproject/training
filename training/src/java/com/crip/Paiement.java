/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crip;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import org.json.simple.parser.ParseException;
/**
 *
 * @author JUSTIN KABANZA
 */
public class Paiement {
    private String numBord;
    private String idMembre;
    private String idModule;
    private Date dateP;
    private float montant;
    private String monnaie;
    private String taux;

    public String getNumBord() {
        return numBord;
    }

    public void setNumBord(String numBord) {
        this.numBord = numBord;
    }

    public String getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(String idMembre) {
        this.idMembre = idMembre;
    }

    public String getIdModule() {
        return idModule;
    }

    public void setIdModule(String idModule) {
        this.idModule = idModule;
    }

    public Date getDateP() {
        return dateP;
    }

    public void setDateP(Date dateP) {
        this.dateP = dateP;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String getMonnaie() {
        return monnaie;
    }

    public void setMonnaie(String monnaie) {
        this.monnaie = monnaie;
    }

    public String getTaux() {
        return taux;
    }

    public void setTaux(String taux) {
        this.taux = taux;
    }
    
    public void Enregistrer(Paiement paie)throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, ParseException{
        DBConnection cnx=new DBConnection();
        cnx.Execute_Query("Insert into paiement(numbord,idmembre,idmodule,dateP,montant,monnaie,taux) values ("
                + "'"+ paie.numBord +"',"
                + "'"+ paie.idMembre +"',"
                + "'"+ paie.idModule +"',"
                + "'"+ paie.dateP +"',"
                + "'"+ paie.montant +"',"
                + "'"+ paie.monnaie +"',"
                + "'"+ paie.taux +"')");
    }
}
