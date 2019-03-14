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
    private String nomUniv;
    private String action;
    private String message;

    public String getNomUniv() {
        return nomUniv;
    }

    public void setNomUniv(String nomUniv) {
        this.nomUniv = nomUniv;
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
     * Creates a new instance of Universite
     */
    public Universite() {
    }
    
    public Universite(String nomUniv) {
        this.nomUniv=nomUniv;
    }
    
     public List<Universite> liste(){
        List<Universite> lst = new ArrayList<>();
        try{
            DBConnection conn = new DBConnection();
            ResultSet result = conn.Data_Source(
                    "SELECT "
                            + "nomUniv "                    
                            + "FROM Universite"
            );
            
            while(result.next())
            {
                lst.add(new Universite(
                        result.getString("nomUniv")                      
                        )
                );
            }
        }catch(ClassNotFoundException | SQLException | IOException | ParseException ex){
             message=ex.getMessage();
        }
        return lst;
    }
}
