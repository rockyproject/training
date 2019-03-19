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
public class Formateur  extends Membre{
    private Module module;
    
    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

   
    
    /**
     * Creates a new instance of Formateur
     */
    public Formateur() {
    }
    
    @Override
    public String enregistrer(){
                
        try {
            DBConnection conn = new DBConnection();
            conn.Execute_Query("INSERT INTO Enseigner(idmodule, idformateur) VALUES ("
                    + "'" + this.module.getIdModule() + "'," 
                    + "'" + super.idMembre + "')" 
                    
            ); 
            
            message = "Enregistrement effectué avec succès";
            super.idMembre="";
            super.nom= "";
            super.postNom="";
            super.prenom = "";
            super.sexe = "";
            this.module=new Module();
            /*this.message="";*/
            super.action="";
            return "main";
            
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            message= ex.getMessage();          
            return "saisieAttribution";
        }
        
    }
    
    public String enregistrerFormateur(){
                
        try {
            DBConnection conn = new DBConnection();
            conn.Execute_Query("INSERT INTO Formateur(idformateur) VALUES ("               
                    + "'" + super.idMembre + "')" 
                    
            ); 
            message = "Enregistrement effectué avec succès";            
            super.message="";
            super.action="";
            return "main";
            
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            message= ex.getMessage();
            return "saisieFormateur";
        }
        
    }
    
    @Override
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
    
    
    
    public String selectMembre(){
        return"selectMembre";
    }
    
    public String selectFormateur(){
        return"selectFormateur";
    }
    
    @Override
    public String saisie(){
        return"saisieAttribution";
    }
    
    public String saisieFormateur(){
        return"saisieFormateur";
    }
    
}
