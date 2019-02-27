/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crip;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
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
    
    public void Enregistrer(Evaluation eval)throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, ParseException{
        DBConnection cnx=new DBConnection();
        cnx.Execute_Query("Insert into Formation (IdMembre,IdModule,IdEvaluateur,DateEv,Cote)VALUES ("
                +"'"+ eval.idMembre +"','"+ eval.idModule +"','"+ eval.idEvaluateur +"',"
                +"'"+ eval.date +"','"+ eval.cote +"')");
    }
}
