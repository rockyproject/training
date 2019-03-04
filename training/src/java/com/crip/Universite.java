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
public class Universite {
    private String nomUniv;

    public String getNomUniv() {
        return nomUniv;
    }

    public void setNomUniv(String nomUniv) {
        this.nomUniv = nomUniv;
    }
    
    public Universite(){
        
    }
    public Universite(String nomUniv){
       this.nomUniv= nomUniv;
    }
    public void Enregistrer(Universite univ)throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, ParseException{
        DBConnection cnx=new DBConnection();
        cnx.Execute_Query("Insert into Universite (nomUniv) values ('"+ univ.nomUniv +"')");
    }
    
    public List<Universite> listeUniversite() throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, ParseException{
        List<Universite> lst = new ArrayList<>();
        
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
        
        return lst;
    }
}
