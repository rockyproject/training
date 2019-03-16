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
    private String idFormation;
    private Module module;
    private Formateur formateur;
    private Universite universite;
    private Membre membre;
    private int heureDebut;
    private int heureFin;
    private int minuteDebut;
    private int minuteFin;
    private String heure;
    private Date dateDebut=new Date();
    private Date dateFin=new Date();
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
 
    public int getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(int heureDebut) {
        this.heureDebut = heureDebut;
    }

    public int getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(int heureFin) {
        this.heureFin = heureFin;
    }

    public int getMinuteDebut() {
        return minuteDebut;
    }

    public void setMinuteDebut(int minuteDebut) {
        this.minuteDebut = minuteDebut;
    }

    public int getMinuteFin() {
        return minuteFin;
    }

    public void setMinuteFin(int minuteFin) {
        this.minuteFin = minuteFin;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
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
                    + "idFormation,"
                    + "idFormateur,"
                    + "idmodule,"
                    + "heureDebut,"
                    + "heureFin,"
                    + "DateDebut,"
                    + "DateFin,"
                    + "idUniv ) "
                    + "VALUES ("
                    + "'"+ this.membre.getIdMembre() +"',"
                    + "'"+ this.module.getIdModule() +"',"
                    + "'"+ this.heure +"',"
                    + "'"+ new SimpleDateFormat("yyyy-MM-dd").format(this.dateDebut) +"',"
                    + "'"+ new SimpleDateFormat("yyyy-MM-dd").format(this.dateFin) +"',"
                    + "'"+ this.universite.getNomUniv() +"'"
                    + ")"
            );
                        //Initialisation
            
            this.membre = new Membre();
            this.module = new Module();
            this.heureDebut = 0;
            this.heureFin = 0;
            this.minuteDebut = 0;
            this.minuteFin = 0;
            this.heure = "";
            this.dateDebut = new Date();
            this.dateFin = new Date();
            this.universite = new Universite();
            this.message = "";
            //Retour à la page principale
            return "main";
        }
        catch(ClassNotFoundException | SQLException | IOException | ParseException ex){
            message=ex.getMessage();
            return "saisieFormation";
        }
    }
    
    //HEURES
    //======
    public int[] heures(){
        int h[] = new int[24];
        for(int i=0;i<24;i++){
            h[i]=i;
        }
        return h;
    }
    
    //MINUTES
    //=======
    public int[] minutes(){
        int m[] = new int[60];
        for(int i=0;i<60;i++){
            m[i]=i;
        }
        return m;
    }
}
