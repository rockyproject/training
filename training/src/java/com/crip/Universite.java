/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crip;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.json.simple.parser.ParseException;

/**
 *
 * @author JUSTIN KABANZA
 */
@ManagedBean
@SessionScoped
public class Universite {
    private String iduniv;
    private String nom;
    private String sigle;
    private String adresse;
    private String tel;
    private String email;
    private String siteweb;
    private String dg;
    private String academique;
    private String ab;
    private String action;
    private String message;
    private Membre dirGen;
    private Membre academ;
    private Membre adminBugd;

    public String getIduniv() {
        return iduniv;
    }

    public void setIduniv(String iduniv) {
        this.iduniv = iduniv;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSiteweb() {
        return siteweb;
    }

    public void setSiteweb(String siteweb) {
        this.siteweb = siteweb;
    }

    public String getDg() {
        return dg;
    }

    public void setDg(String dg) {
        this.dg = dg;
    }

    public String getAcademique() {
        return academique;
    }

    public void setAcademique(String academique) {
        this.academique = academique;
    }

    public String getAb() {
        return ab;
    }

    public void setAb(String ab) {
        this.ab = ab;
    }

   

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Membre getDirGen() {
        return dirGen;
    }

    public void setDirGen(Membre dirGen) {
        this.dirGen = dirGen;
    }

    public Membre getAcadem() {
        return academ;
    }

    public void setAcadem(Membre academ) {
        this.academ = academ;
    }

    public Membre getAdminBugd() {
        return adminBugd;
    }

    public void setAdminBugd(Membre adminBugd) {
        this.adminBugd = adminBugd;
    }
    
    

    /**
     * Creates a new instance of Universite
     */
    public Universite() {
    }
    
    public Universite(String iduniv,String nom,String sigle,String adresse, String tel,String email,String siteweb,String dg, String academique, String ab) {
        this.iduniv=iduniv;
        this.nom=nom;
        this.sigle=sigle;
        this.adresse=adresse;
        this.tel=tel;
        this.email=email;
        this.siteweb=siteweb;
        this.dg=dg;
        this.academique=academique;
        this.ab=ab;
    }
    
    public String Enregistrer(){
        try {
            DBConnection cnx=new DBConnection();
            cnx.Execute_Query("Insert into Universite ("
                    + "iduniv,"
                    + "nom,"
                    + "sigle,"
                    + "adresse,"
                    + "tel,"
                    + "email,"
                    + "siteweb,"
                    + "dg,"
                    + "academique,"
                    + "ab) VALUES ("
                    + "'"+ this.iduniv +"',"
                    + "'"+ this.nom +"',"
                    +"'"+ this.sigle +"',"
                    +"'"+ this.adresse +"',"
                    +"'"+ this.tel +"',"
                    +"'"+ this.email +"',"
                    +"'"+ this.siteweb +"',"
                    +"'"+ this.dirGen.getIdMembre() +"',"
                    +"'"+ this.academ.getIdMembre() +"',"
                    +"'"+ this.adminBugd.getIdMembre() +"')"
                    
                ); 
                this.message = "";
                this.action = "";
                this.iduniv = "";
                this.nom="";
                this.sigle="";
                this.adresse="";
                this.tel="";
                this.email="";
                this.siteweb="";
                this.dg="";
                this.academique="";
                this.ab="";
                this.dirGen=new Membre();
                this.academ=new Membre();
                this.adminBugd=new Membre();
                return "main";
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            message= ex.getMessage();
        }
        return "saisieUniversite";
    }
    
     public List<Universite> liste(){
        List<Universite> lst = new ArrayList<>();
        try{
            DBConnection conn = new DBConnection();
            ResultSet result = conn.Data_Source(
                    "SELECT "
                            + "iduniv, " 
                            + "nom, "
                            + "Sigle, "
                            + "adresse, "
                            + "tel, "
                            + "email, "
                            + "siteweb, "
                            + "dg, "
                            + "academique, "
                            + "ab "
                            + "FROM Universite"
            );
            
            while(result.next())
            {
                lst.add(new Universite(
                        result.getString("iduniv"),
                        result.getString("nom"),
                        result.getString("sigle"),
                        result.getString("adresse"),
                        result.getString("tel"),
                        result.getString("email"),
                        result.getString("siteweb"),
                        result.getString("dg"),
                        result.getString("academique"),
                        result.getString("ab")
                        )
                );
            }
        }catch(ClassNotFoundException | SQLException | IOException | ParseException ex){
             message=ex.getMessage();
        }
        return lst;
    }
     
     public String saisie(){
         return "saisieUniversite";
     }
     
     public String selectDG(){
         return "selectMembre";
     }
     
     public String selectAcademique(){
         return "selectMembre";
     }
     
     public String selectAb(){
         return "selectMembre";
     }
}
