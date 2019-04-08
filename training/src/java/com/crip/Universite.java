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
public class Universite extends Structure {
    private String dg;
    private String academique;
    private String ab;
    private String percepteur;
    private String action;
    private String message;
    private Membre dirGen;
    private Membre academ;
    private Membre adminBugd;
    private Membre percept;
    Membre mbre=new Membre();
    private String motPass=mbre.generatePSWD();

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

    public String getPercepteur() {
        return percepteur;
    }

    public void setPercepteur(String percepteur) {
        this.percepteur = percepteur;
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

    public String getMotPass() {
        return motPass;
    }

    public void setMotPass(String motPass) {
        this.motPass = motPass;
    }

    public Membre getPercept() {
        return percept;
    }

    public void setPercept(Membre percept) {
        this.percept = percept;
    }
    
    

    /**
     * Creates a new instance of Universite
     */
    public Universite() {
    }
    
    public Universite(String idStruct,String nom,String sigle,String adresse, String tel,String email,String siteweb,String dg, String academique, String ab) {
        super.idStruct=idStruct;
        super.nom=nom;
        super.sigle=sigle;
        super.adresse=adresse;
        super.tel=tel;
        super.email=email;
        super.siteweb=siteweb;
        this.dg=dg;
        this.academique=academique;
        this.ab=ab;
    }
    
    public String Enregistrer(){
        try {
            DBConnection cnx=new DBConnection();
            cnx.Execute_Query(
                    "BEGIN; "
                    + "INSERT INTO Personne(idpers,tel,email,adresse) VALUES ("
                    + "'" + super.idStruct + "'," 
                    + "'" + super.tel + "'," 
                    + "'" + super.email + "',"
                    + "'" + super.adresse + "'); "
                    + "INSERT INTO Structure(idStruct, nom, sigle, siteWeb) VALUES ("
                    + "'" + super.idStruct + "'," 
                    + "'" + super.nom + "'," 
                    + "'" + super.sigle + "',"
                    + "'" + super.siteweb + "'); "
                    + "INSERT INTO utilisateur(idutilisateur,motdepasse) VALUES ("
                    + "'" + super.idStruct + "',"
                    + "'" + this.motPass + "'); "
                    + "INSERT INTO Universite (idUniv,dg,academique,ab,percepteur) VALUES ("
                    + "'"+ super.idStruct +"',"
                    + "'"+ this.dirGen.getIdPers() +"',"
                    + "'"+ this.academ.getIdPers() +"',"
                    + "'"+ this.adminBugd.getIdPers() +"',"
                    + "'"+ this.percept.getIdPers() +"'); "
                    + "COMMIT;"
            ); 
                this.message = "";
                this.action = "";
                super.idStruct = "";
                super.nom="";
                super.sigle="";
                super.adresse="";
                super.tel="";
                super.email="";
                super.siteweb="";
                this.dg="";
                this.academique="";
                this.ab="";
                this.percepteur="";
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
                            + "FROM Universite inner join Structure on Universite.idUniv=Structure.idStruct "
                            + "inner join Personne on Structure.idStruct=Personne.IdPers "
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
