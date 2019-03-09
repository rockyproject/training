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
import java.util.Date;
import java.util.List;
import org.json.simple.parser.ParseException;

/**
 *
 * @author JUSTIN KABANZA
 */
public class Evaluation {
    private String idMembre;
    private String idModule;
    private String idEvaluateur;
    private Date date;
    private int cote;

    public String getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(String idMembre) {
        this.idMembre = idMembre;
    }

    public String getIdModule() {
        return idModule;
    }

    public void setIdModule(String idModule) {
        this.idModule = idModule;
    }

    public String getIdEvaluateur() {
        return idEvaluateur;
    }

    public void setIdEvaluateur(String idEvaluateur) {
        this.idEvaluateur = idEvaluateur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCote() {
        return cote;
    }

    public void setCote(int cote) {
        this.cote = cote;
    }
    
    public Evaluation(){
        
    }
    
    public Evaluation(String idMembre, String idModule, String idEvaluateur, int cote, Date date){
        this.idMembre=idMembre;
        this.idModule=idModule;
        this.idEvaluateur=idEvaluateur;
        this.cote=cote;
        this.date=date;
        
    }
    
    public void Enregistrer(Evaluation eval)throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, ParseException{
        DBConnection cnx=new DBConnection();
        cnx.Execute_Query("Insert into evaluation(idmembre,idmodule,idevaluateur,dateEv,cote) VALUES ("
                + "'"+ eval.idMembre +"',"
                + "'"+ eval.idModule +"',"
                + "'"+ eval.idEvaluateur +"',"
                + "'"+ eval.date +"',"
                + "'"+ eval.cote +"')");
    }
    
    public List<Evaluation> listeEvaluation() throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, ParseException{
        List<Evaluation> lst = new ArrayList<>();
        
            DBConnection conn = new DBConnection();
            ResultSet result = conn.Data_Source(
                    "SELECT "
                            +"select (select concat (nom,' ',postnom,' ',prenom) from Membre \n" 
                            +"where IdMembre=Evaluation.IdMembre)as Apprenant,\n" 
                            +"design,\n" 
                            +"(select concat (nom,' ',postnom,' ',prenom) from Membre where \n" 
                            +"IdMembre=Evaluation.IdEvaluateur)as formateur,\n" 
                            +"DateEv,\n"
                            +"Cote \n"
                            +"from Evaluation inner join module on Evaluation.IdModule=Module.IdModule"
            );
            
            while(result.next())
            {
                lst.add(new Evaluation(
                        result.getString("Apprenant"),
                        result.getString("design"),
                        result.getString("formateur"),                       
                        result.getInt("Cote"),
                        result.getDate("DateEv")
                        )
                );
            }
        return lst;
    }
}
