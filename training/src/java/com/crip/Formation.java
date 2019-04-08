/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crip;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Date;
import java.util.List;
import org.json.simple.parser.ParseException;
/**
 *
 * @author JUSTIN KABANZA
 */
@ManagedBean
@SessionScoped
public class Formation {
    private String idFormation=generateIdFormation();
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
    
    private String idModule;
    private String idFormateur;
    private String nomUniv;
    private String heuredeb;
    private String heuref;

    public String getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(String idFormation) {
        this.idFormation = idFormation;
    }
 

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

    public String getIdModule() {
        return idModule;
    }

    public void setIdModule(String idModule) {
        this.idModule = idModule;
    }

    public String getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(String idFormateur) {
        this.idFormateur = idFormateur;
    }

    public String getNomUniv() {
        return nomUniv;
    }

    public void setNomUniv(String nomUniv) {
        this.nomUniv = nomUniv;
    }

    public String getHeuredeb() {
        return heuredeb;
    }

    public void setHeuredeb(String heuredeb) {
        this.heuredeb = heuredeb;
    }

    public String getHeuref() {
        return heuref;
    }

    public void setHeuref(String heuref) {
        this.heuref = heuref;
    }
    
    

    

    
    
    
    /**
     * Creates a new instance of Formation
     */
    public Formation() {
    }
    
    public Formation(String idFormation,String idModule, String idFormateur, Date dateDebut, Date dateFin, String heuredeb, String heuref) {
        this.idFormation=idFormation;
        this.idModule=idModule;
        this.idFormateur=idFormateur;
        this.dateDebut=dateDebut;
        this.dateFin=dateFin;
        this.heuredeb=heuredeb;
        this.heuref=heuref;
    }
    
    //AFFICHAGE DE LA PAGE DE SELECTION DU MODULE
    //===========================================
    public String selectModule()
    {
        return "selectModule";
       
    }
    
    //AFFICHAGE DE LA PAGE DE SELECTION DU MODULE
    //===========================================
    public String selectMembre()
    {
        return "selectMembre";  
    }
    
     //AFFICHAGE DE LA PAGE DE SELECTION DU MODULE
    //===========================================
    public String selectFormation()
    {
        return "selectFormation";  
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
                    + "'"+ this.idFormation +"',"
                    + "'"+ this.membre.getIdMembre() +"',"
                    + "'"+ this.module.getIdModule() +"',"
                    + "'"+ this.heureDebut+ ":" + this.minuteDebut +"',"
                    + "'"+ this.heureFin+ ":" + this.minuteFin +"',"
                    + "'"+ new SimpleDateFormat("yyyy-MM-dd").format(this.dateDebut) +"',"
                    + "'"+ new SimpleDateFormat("yyyy-MM-dd").format(this.dateFin) +"',"
                    + "'"+ this.universite.getIdStruct()+"'"
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
    
    public String enregistrerAffectation_Formation(){        
        try {
            DBConnection cnx=new DBConnection();
            cnx.Execute_Query("Insert into affectationmbremodule ("                   
                    + "idmembre,"
                    + "idFormation) "
                    + "VALUES ("
                    + "'"+ this.membre.getIdMembre() +"',"
                    + "'"+ this.idFormation +"'"                   
                    + ")"
            );
                        //Initialisation
            
            this.membre = new Membre();
            this.idFormation="";
            
            this.message = "";
            //Retour à la page principale
            return "main";
        }
        catch(ClassNotFoundException | SQLException | IOException | ParseException ex){
            message=ex.getMessage();
            return "selectFormation";
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
    
    public List<Formation> listeFormation(){
        List<Formation> lst = new ArrayList<>();
        try{
            DBConnection conn = new DBConnection();
            ResultSet result = conn.Data_Source(
                    "SELECT "
                            + "idformation, " 
                            + "(select Design from module where idmodule=formation.idmodule)as Design, "
                            + "(select concat(nom,' ',postnom,' ',prenom) from Membre where idmembre=formation.idformateur)as nomFormateur, "
                            + "heuredebut, "
                            + "heurefin, "
                            + "datedebut, "
                            + "datefin "                  
                            + "FROM Formation "                                                     
            );
            
            while(result.next())
            {
                lst.add(new Formation(      
                        result.getString("idformation"),
                        result.getString("Design"),
                        result.getString("nomFormateur"),
                        result.getDate("datedebut"),
                        result.getDate("datefin"),
                        result.getString("heuredebut"),
                        result.getString("heurefin")
                        )
                );
            }
        }catch(ClassNotFoundException | SQLException | IOException | ParseException ex){
             message=ex.getMessage();
        }
        return lst;
    }
    
    //GENERATION DE  L'idFormation
    //======================
    private String generateIdFormation(){
        String id="";
        try {            
            DBConnection conn = new DBConnection();
            id = conn.Show_Data("select id from (select ((random()*10000000)::int)::varchar(10) AS id) t where id not in (select idformation from Formation)", "id", 1);
            
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            this.message = ex.getMessage();
        }
        return id;
    }
}
