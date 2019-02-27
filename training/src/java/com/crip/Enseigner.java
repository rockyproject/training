/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crip;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import org.json.simple.parser.ParseException;

/**
 *
 * @author JUSTIN KABANZA
 */
public class Enseigner {
    private String idModule;
    private String idFormateur;

    public String getIdModule() {
        return idModule;
    }

    public void setIdModule(String idModule) {
        this.idModule = idModule;
    }

    public String getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(String idFormateur) {
        this.idFormateur = idFormateur;
    }
   
    public void Enregistrer(Enseigner ens)throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, ParseException{
        DBConnection cnx=new DBConnection();
        cnx.Execute_Query("Insert into  (idModule,idFormateur) Values ('"+ ens.idModule +"',"
                + "'"+ ens.idFormateur +"')");
    }
}
