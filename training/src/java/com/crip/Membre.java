package com.crip;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped

public class Membre {
    private String idMembre;
    private String nom;
    private String postNom;
    private String prenom;
    private String sexe;
    private String message;
    private String action;

    public Membre() {
    }

    public Membre(String idMembre, String nom, String postNom, String prenom, String sexe) {
        this.idMembre = idMembre;
        this.nom = nom;
        this.postNom = postNom;
        this.prenom = prenom;
        this.sexe = sexe;
    }

    public String getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(String idMembre) {
        this.idMembre = idMembre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPostNom() {
        return postNom;
    }

    public void setPostNom(String postNom) {
        this.postNom = postNom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
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
    
    public void enregistrer(Membre membre) throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, ParseException{
        DBConnection conn = new DBConnection();
        conn.Execute_Query("INSERT INTO membre(idmembre, nom, postnom, prenom, sexe) VALUES ("
                + "'" + membre.idMembre + "'," 
                + "'" + membre.nom + "'," 
                + "'" + membre.postNom + "',"
                + "'" + membre.prenom + "',"
                + "'" + membre.sexe + "')"
        );   
    }
    
    public void modifier(){
        
    }
    
    public void supprimer(){
        
    }
    
    public List<Membre> listeMembres(){
        List<Membre> lst = new ArrayList<>();
        try{
            DBConnection conn = new DBConnection();
            ResultSet result = conn.Data_Source(
                    "SELECT "
                            + "idmembre, "
                            + "nom, "
                            + "postnom, "
                            + "prenom, "
                            + "sexe "
                            + "FROM membre"
            );
            
            while(result.next())
            {
                lst.add(new Membre(
                        result.getString("idmembre"),
                        result.getString("nom"),
                        result.getString("postnom"),
                        result.getString("prenom"),
                        result.getString("sexe")
                        )
                );
            }
        }catch(ClassNotFoundException | SQLException | IOException | ParseException ex){
             message=ex.getMessage();
        }
        return lst;
    }
    
    public List<Membre> listeMembres(String nom){
        List<Membre> lst = new ArrayList<>();
        try{
            DBConnection conn = new DBConnection();
            ResultSet result = conn.Data_Source(
                    "SELECT "
                            + "idmembre, "
                            + "nom, "
                            + "postnom, "
                            + "prenom, "
                            + "sexe "
                            + "FROM membre "
                            + "WHERE nom || postnom || prenom like '%" + nom + "%' "
            );
            
            while(result.next())
            {
                lst.add(new Membre(
                        result.getString("idmembre"),
                        result.getString("nom"),
                        result.getString("postnom"),
                        result.getString("prenom"),
                        result.getString("sexe")
                        )
                );
            }
        }catch(ClassNotFoundException | SQLException | IOException | ParseException ex){
             message=ex.getMessage();
        }
        return lst;
    }
}
