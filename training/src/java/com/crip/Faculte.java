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
public class Faculte {
    private String designation;
    private String message;

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
           
    /**
     * Creates a new instance of Faculte
     */
    public Faculte() {
    }
    
    public Faculte(String designation) {
       this.designation=designation; 
    }
    
    public String enregistrer(){
        try {
            DBConnection cnx=new DBConnection();
            cnx.Execute_Query("Insert into Faculte (designation) VALUES ("
                +  "'"+ this.designation +"')"
                ); 
            this.message = "Enregistrement effectué avec succès";           
            this.designation = "";            
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            message= ex.getMessage();
            
        } 
        return "saisieFaculte";
    }
    
    public String saisie(){
        return "saisieFaculte";
    }
    
    public List<Faculte> liste(){
        List<Faculte> lst = new ArrayList<>();
        try{
            DBConnection conn = new DBConnection();
            ResultSet result = conn.Data_Source(
                    "SELECT "
                            + "designation " 
                            + "FROM Faculte "                           
            );           
            while(result.next())
            {
                lst.add(new Faculte(
                        result.getString("designation")                       
                        )
                );
            }
        }catch(ClassNotFoundException | SQLException | IOException | ParseException ex){
             message=ex.getMessage();
        }
        return lst;
    }

}
