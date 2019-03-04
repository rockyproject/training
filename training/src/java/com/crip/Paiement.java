/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crip;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    
    public Paiement(){
        
    }
    
    public Paiement(String numBord,String idMembre, String idModule, Date dateP, float montant, String monnaie, String taux){
        this.numBord=numBord;
        this.idMembre=idMembre;
        this.idModule=idModule;
        this.dateP=dateP;
        this.montant=montant;
        this.monnaie=monnaie;
        this.taux=taux;
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
    
    public List<Paiement> listePaiement() throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, ParseException{
        List<Paiement> lst = new ArrayList<>();
        
            DBConnection conn = new DBConnection();
            ResultSet result = conn.Data_Source(
                    "SELECT "
                            +"numbord,"
                            +"(select concat (nom,' ',postnom,' ',prenom) from Membre where IdMembre=Paiement.IdMembre)as membre,"
                            +"design,"
                            +"DateP,"
                            +"Montant,"
                            +"Monnaie,"
                            +"Taux "
                            +"from Paiement inner join module on Paiement.IdModule=Module.IdModule"
                        );
            
            while(result.next())
            {
                lst.add(new Paiement(
                        result.getString("numbord"),
                        result.getString("membre"),
                        result.getString("design"),                       
                        result.getDate("DateP"),
                        result.getFloat("Montant"),
                        result.getString("Monnaie"),
                        result.getString("Taux")
                        )
                );
            }
        return lst;
    }
}
