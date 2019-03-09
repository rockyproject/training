
package com.crip;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.json.simple.parser.ParseException;

//import javax.faces.bean.RequestScoped;

@ManagedBean
//@RequestScoped
@SessionScoped
public class Inscription {
    
    private Module module;        
    private Membre membre;
    private String numBV;
    private Date dateBV = new Date();
    private int montant;
    private String monnaie;
    private double taux;    
    private String action;
    private String message;
    
    //CONSTRUCTEUR
    //============
    public Inscription() {        
    }

    //ACCESSEURS
    //==========
    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getNumBV() {
        return numBV;
    }

    public void setNumBV(String numBV) {
        this.numBV = numBV;
    }

    public Date getDateBV() {
        return dateBV;
    }

    public void setDateBV(Date dateBV) {
        this.dateBV = dateBV;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getMonnaie() {
        return monnaie;
    }

    public void setMonnaie(String monnaie) {
        this.monnaie = monnaie;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    //AFFICHAGE DE LA PAGE DE SAISIE DE L'INSCRIPTION
    //===============================================
    public String saisie(){
        return "saisieInscription";
    }
    
    //AFFICHAGE DE LA PAGE DE SELECTION DU MODULE
    //===========================================
    public String selectModule()
    {
        return "selectModule";
    }
    
    //AFFICHAGE DE LA PAGE DE SELECTION DU MEMBRE
    //==========================================
    public String selectMembre(){
        return "selectMembre";
    }

    //RETOUR
    //======
    public String retour(){
        return "main";
    }
    
    //ENREGISTREMENT
    //==============
    public String enregistrer(){
        try {
            //Enregistrement
            DBConnection cnx=new DBConnection();
            cnx.Execute_Query("Insert into inscription ("
                    + "numbord,"
                    + "idmembre,"
                    + "idmodule,"
                    + "datep,"
                    + "montant,"
                    + "monnaie,"
                    + "taux) "
                    + "VALUES ("
                    + "'"+ this.numBV +"',"
                    + "'"+ this.membre.getIdMembre() +"',"
                    + "'"+ this.module.getIdModule() +"',"
                    + "'"+ new SimpleDateFormat("yyyy-MM-dd").format(this.dateBV) +"',"
                    + this.montant +","
                    + "'"+ this.monnaie +"',"
                    + this.taux 
                    + ")"
            );
            //Initialisation
            this.numBV="";
            this.membre = new Membre();
            this.module = new Module();
            this.dateBV = new Date();
            this.montant = 0;
            this.monnaie = "";
            this.taux = 0.0;
            this.message = "";
            //Retour à la page principale
            return "main";
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            //Affichage du message d'erreur sur la page
            message=ex.getMessage();
            return "saisieInscription";
        }
    
    }
}
