/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crip;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Date;
import org.json.simple.parser.ParseException;
/**
 *
 * @author JUSTIN KABANZA
 */
@ManagedBean
@SessionScoped
public class Formation {
    private Module module;
    private Formateur formateur;
    private Universite universite;
    private Membre membre;
    private String idMembre;
    private String idFormateur;
    private String idModule;
    private Date heure=new Date();
    private Date dateDebut=new Date();
    private Date dateFin=new Date();
    private String nomUniv;
    private String message;
    private String action;

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Formateur getFormateur() {
        return formateur;
    }

    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }
    
    

    public Universite getUniversite() {
        return universite;
    }

    public void setUniversite(Universite universite) {
        this.universite = universite;
    }

    public String getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(String idMembre) {
        this.idMembre = idMembre;
    }

    public String getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(String idFormateur) {
        this.idFormateur = idFormateur;
    }

    public String getIdModule() {
        return idModule;
    }

    public void setIdModule(String idModule) {
        this.idModule = idModule;
    }

    public Date getHeure() {
        return heure;
    }

    public void setHeure(Date heure) {
        this.heure = heure;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getNomUniv() {
        return nomUniv;
    }

    public void setNomUniv(String nomUniv) {
        this.nomUniv = nomUniv;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    

    
    /**
     * Creates a new instance of Formation
     */
    public Formation() {
    }
    //AFFICHAGE DE LA PAGE DE SELECTION DU MODULE
    //===========================================
    public String selectModule()
    {
        return "selectModule";
       
    }
    
     //AFFICHAGE DE LA PAGE DE SELECTION FORMATEUR
    //===========================================
    public String selectFormateur()
    {
        return "selectFormateur";
        
    }
    
    //AFFICHAGE DE LA PAGE DE SELECTION FORMATEUR
    //===========================================
    public String selectUniversite()
    {
        return "selectUniversite"; 
    }
    
    //AFFICHAGE DE LA PAGE DE SELECTION FORMATEUR
    //===========================================
    public String Saisie()
    {
        return "saisieFormation"; 
    }
    
     //ENREGISTREMENT
    //==============
    public String enregistrer(){
        try {
            DBConnection cnx=new DBConnection();
            cnx.Execute_Query("Insert into Formation ("                   
                    + "idFormateur,"
                    + "idmodule,"
                    + "Heure,"
                    + "DateDebut,"
                    + "DateFin,"
                    + "Universite ) "
                    + "VALUES ("
                    + "'"+ this.membre.getIdMembre() +"',"
                    + "'"+ this.module.getIdModule() +"',"
                    + "'"+ new SimpleDateFormat("yyyy-MM-dd").format(this.heure) +"',"
                    + "'"+ new SimpleDateFormat("yyyy-MM-dd").format(this.dateDebut) +"',"
                    + "'"+ new SimpleDateFormat("yyyy-MM-dd").format(this.dateFin) +"',"
                    + "'"+ this.universite.getNomUniv() +"'"
                    + ")"
            );
                        //Initialisation
            
            this.membre = new Membre();
            this.module = new Module();
            this.heure = new Date();
            this.dateDebut = new Date();
            this.dateFin = new Date();
            this.nomUniv = "";
            this.message = "";
            //Retour à la page principale
            return "main";
        }
        catch(ClassNotFoundException | SQLException | IOException | ParseException ex){
            message=ex.getMessage();
            return "saisieFormation";
        }
        }
}
