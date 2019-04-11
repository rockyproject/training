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
public class Section extends Personne{
    private Universite univ;
    private Faculte fac;
    private String vacation;
    private String message;
    private String designFac;

    public Universite getUniv() {
        return univ;
    }

    public void setUniv(Universite univ) {
        this.univ = univ;
    }

    public Faculte getFac() {
        return fac;
    }

    public void setFac(Faculte fac) {
        this.fac = fac;
    }

    

    public String getVacation() {
        return vacation;
    }

    public void setVacation(String vacation) {
        this.vacation = vacation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDesignFac() {
        return designFac;
    }

    public void setDesignFac(String designFac) {
        this.designFac = designFac;
    }
    
    
    
    
    /**
     * Creates a new instance of Section
     */
    public Section() {
    }
    
    public Section(String designFac,String vacation) {
        this.designFac=designFac;
        this.vacation=vacation;
        
    }
    
    public String enregistrer(){
        try {
            DBConnection cnx=new DBConnection();
            cnx.Execute_Query(
                "BEGIN; "
                + "INSERT INTO Personne (idpers,tel,email,adresse) VALUES ("
                + "'"+ super.idPers +"',"
                + "'"+ super.tel +"',"
                + "'"+ super.email +"',"
                + "'"+ super.adresse +"'); "
                +"Insert into Section (idSect,idUniv,designation,vacation) VALUES ("
                + "'"+ super.idPers +"',"
                + "'"+ this.univ.getIdStruct() +"',"
                + "'"+ this.fac.getDesignation() +"',"
                +  "'"+ this.vacation +"');"
                + "COMMIT;"
                ); 
            this.message = "Enregistrement effectué avec succès";           
            this.fac =new Faculte();
            this.univ=new Universite();
            super.idPers="";
            super.tel="";
            super.email="";
            super.adresse="";
            this.vacation="";
            return "main";
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            message= ex.getMessage();
            return "saisieSection";
        } 
        
    }
    
    public String selectUniversite(){
        return"selectUniversite";
    }
    
     public String selectFaculte(){
        return"selectFaculte";
    }
    
     public List<Section> liste(){
        List<Section> lst = new ArrayList<>();
        try{
            DBConnection conn = new DBConnection();
            ResultSet result = conn.Data_Source(
                    "SELECT "
                            + "Faculte.designation as designation, "
                            + "vacation " 
                            + "FROM Section inner join Faculte on Section.designation=Faculte.designation "                           
            );           
            while(result.next())
            {
                lst.add(new Section(
                        result.getString("designation"),
                        result.getString("vacation")
                        )
                );
            }
        }catch(ClassNotFoundException | SQLException | IOException | ParseException ex){
             message=ex.getMessage();
        }
        return lst;
    }
}
