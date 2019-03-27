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
import javax.faces.bean.ManagedProperty;
import org.json.simple.parser.ParseException;

/**
 *
 * @author JUSTIN KABANZA
 */
@ManagedBean
@SessionScoped
public class Evaluation {
   private Membre membre;
   private Module module;
   private Formateur evaluateur;
   private Date dateEv=new Date();
   private Double cote;
   private String message;   
   private String user; 
   private ActionMessage actionMess;
   
   @ManagedProperty(value="#{utilisateur}")
   Utilisateur utilisateur;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
   
   

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Formateur getEvaluateur() {
        return evaluateur;
    }

    public void setEvaluateur(Formateur evaluateur) {
        this.evaluateur = evaluateur;
    }

    public Date getDateEv() {
        return dateEv;
    }

    public void setDateEv(Date dateEv) {
        this.dateEv = dateEv;
    }

    public Double getCote() {
        return cote;
    }

    public void setCote(Double cote) {
        this.cote = cote;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ActionMessage getActionMess() {
        return actionMess;
    }

    public void setActionMess(ActionMessage actionMess) {
        this.actionMess = actionMess;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    
   

    /**
     * Creates a new instance of Evaluation
     */
    public Evaluation() {
    }
    
    public String selectMembre(){
        return "selectMembre";
    }
    
    public String selectModule(){
        return "selectModule";
    }
    
     public String saisie(){
        return "saisieEvaluation";
    }
    
    public String enregistrer(){
       try {
            //Enregistrement
            DBConnection cnx=new DBConnection();
            cnx.Execute_Query("Insert into Evaluation ("                   
                    + "idmembre,"
                    + "idmodule,"
                    + "idEvaluateur,"
                    + "DateEv,"
                    + "cote) "
                    + "VALUES ("
                    + "'"+ this.membre.getIdMembre() +"',"
                    + "'"+ this.module.getIdModule() +"',"
                    + "'"+ this.utilisateur.idMembre +"',"
                    + "'"+ new SimpleDateFormat("yyyy-MM-dd").format(this.dateEv) +"',"
                    + this.cote 
                    + ")"
            );
            //Initialisation
            this.membre = new Membre();
            this.module = new Module();
            this.dateEv = new Date();
            this.actionMess=new ActionMessage();
            this.cote = 0.0;
            this.message = "";
            
            //Retour à la page principale
            return "main";
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            //Affichage du message d'erreur sur la page
            message=ex.getMessage();
            return "saisieEvaluation";
        } 
    }
}
