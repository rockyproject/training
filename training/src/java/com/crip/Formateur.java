/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crip;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.parser.ParseException;

/**
 *
 * @author JUSTIN KABANZA
 */
public class Formateur {
    private String idFormateur;

    public String getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(String idFormateur) {
        this.idFormateur = idFormateur;
    }
    
   public Formateur(){
       
   }
   
   public Formateur(String idFormateur){
       this.idFormateur=idFormateur;
   }
    
    public void Enregistrer(Formateur form)throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, ParseException{
        DBConnection cnx=new DBConnection();
        cnx.Execute_Query("Insert into Formateur(IdFormateur)Values ('"+ form.idFormateur +"')");
    }
    
    public List<Membre> listeFormateur() throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, ParseException{
        List<Membre> lst = new ArrayList<>();
        
            DBConnection conn = new DBConnection();
            ResultSet result = conn.Data_Source(
                    "SELECT "
                            + "idMembre, "
                            + "nom, "
                            + "Postnom, " 
                            + "Prenom, "
                            + "Sexe " 
                            + "FROM Membre inner join Formateur on Membre.idmembre=Formateur.idformateur"
            );
            
            while(result.next())
            {
                lst.add(new Membre(
                        result.getString("idMembre"),
                        result.getString("nom"),
                        result.getString("Postnom"),
                        result.getString("Prenom"),
                        result.getString("Sexe")
                        )
                );
            }
        
        return lst;
    }
}
