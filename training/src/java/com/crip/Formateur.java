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
public class Formateur {
    private String idFormateur;
    private String action;
    private String message;

    public String getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(String idFormateur) {
        this.idFormateur = idFormateur;
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
    
    /**
     * Creates a new instance of Formateur
     */
    public Formateur() {
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
    
}
