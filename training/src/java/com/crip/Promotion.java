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
public class Promotion {
    private String idProm;
    private Section sections;
    private String promotion;
    private String cp;
    private String message;

    public String getIdProm() {
        return idProm;
    }

    public void setIdProm(String idProm) {
        this.idProm = idProm;
    }

    public Section getSections() {
        return sections;
    }

    public void setSections(Section sections) {
        this.sections = sections;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    

    /**
     * Creates a new instance of Promotion
     */
    public Promotion() {
    }
    
    public String enregistrer(){
        try {
            DBConnection cnx=new DBConnection();
            /*cnx.Execute_Query("INSERT INTO Promotion (idprom,idSect,promotion,CP) VALUES ("
                + "'"+ this.idProm +"',"
                + "'"+ this.sections.getIdPers() +"',"
                + "'"+ this.promotion +"',"
                + "'"+ this.cp +"'); "                
                ); */
            cnx.Execute_Query("INSERT INTO Promotion Select "
                + "'"+ this.idProm +"',"
                + "(Select idSect from Section Where designation='"+ this.sections.getDesignFac() +"'),"
                + "'"+ this.promotion +"',"
                + "'"+ this.cp +"'; "                
                ); 
            this.message = "Enregistrement effectué avec succès";           
            this.idProm ="";
            this.sections=new Section();
            this.promotion="";
            this.cp="";  
            return "main";
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            message= ex.getMessage();
           return "saisiePromotion"; 
        } 
        
    }
    
    public String selectSection(){
        return"selectSection";
    }
     public String saisie(){
        return"saisiePromotion";
    }
}
