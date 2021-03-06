package com.crip;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;

import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped

public class Membre extends Personne{
    protected String idMembre ;
    protected String nom;
    protected String postNom;
    protected String prenom;
    protected String sexe;
    protected String message;
    protected String action;
    
    protected String motPass = generatePSWD();
    protected String confirmPassWord;

    public Membre() {
    }

    public Membre(String idPers, String nom, String postNom, String prenom, String sexe) {
        super.idPers = idPers;
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

    public String getMotPass() {
        return motPass;
    }

    public void setMotPass(String motPass) {
        this.motPass = motPass;
    }

    public String getConfirmPassWord() {
        return confirmPassWord;
    }

    public void setConfirmPassWord(String confirmPassWord) {
        this.confirmPassWord = confirmPassWord;
    }
    
    
    
    public String saisie()
    {
        return "membre";
    }
    
    public String enregistrer(){
                
        try {
            DBConnection conn = new DBConnection();
            conn.Execute_Query(
                    "BEGIN; "
                    + "INSERT INTO membre(idmembre, nom, postnom, prenom, sexe) VALUES ("
                    + "'" + super.idPers + "'," 
                    + "'" + nom + "'," 
                    + "'" + postNom + "',"
                    + "'" + prenom + "',"
                    + "'" + sexe + "'); "
                    + "INSERT INTO utilisateur(idutilisateur,motdepasse) VALUES ("
                    + "'" + super.idPers + "',"
                    + "'" + this.motPass + "'); "
                    + "INSERT INTO Personne (idpers,tel,email,adresse) VALUES ("
                    + "'"+ super.idPers +"',"
                    + "'"+ super.tel +"',"
                    + "'"+ super.email +"',"
                    + "'"+ super.adresse +"'); "
                    + "COMMIT;"
            ); 
            message = "Enregistrement effectué avec succès";
            Personne pers=new Personne();
            super.idPers =pers.generateIdMembre() ;
            this.motPass = this.generatePSWD();
            nom = "";
            postNom = "";
            prenom = "";
            sexe = "";
            
            super.tel="";
            super.email="";
            super.adresse="";
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            message= ex.getMessage();
        }
        return "membre";
    }
    
    public void modifier(){
        
    }
    
    public void supprimer(){
        
    }
    
    public List<Membre> liste(){
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
    
     public List<Membre> listeFormateur(){
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
                            + "FROM membre inner join Formateur on membre.idMembre=Formateur.idFormateur"
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
    
    //RETOUR
    //======
    public String retour(){
        return "main";
    }
  
    
    //GENERATION DU MOT DE PASSE
    //==========================
    public String generatePSWD(){
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 4) + "rck" + uuid.substring(4, 8);
    }
}
