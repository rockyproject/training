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
public class Module {
    private String idModule;
    private String designation;
    private int nbrePage;

    public String getIdModule() {
        return idModule;
    }

    public void setIdModule(String idModule) {
        this.idModule = idModule;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getNbrePage() {
        return nbrePage;
    }

    public void setNbrePage(int nbrePage) {
        this.nbrePage = nbrePage;
    }
    
    public Module(){
    
    }
    public Module(String idModule,String designation, int nbrePage){
    this.idModule=idModule;
    this.designation=designation;
    this.nbrePage=nbrePage;
    }
    public void Enregistrer(Module mod)throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, ParseException{
        DBConnection cnx=new DBConnection();
        cnx.Execute_Query("Insert into module (idmodule,design,nbrePage) VALUES ("
                + "'"+ mod.idModule +"',"
                + "'"+ mod.designation +"',"
                + "'"+ mod.nbrePage +"')"
                );
    
    }
    
    public List<Module> listeModule() throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, ParseException{
        List<Module> lst = new ArrayList<>();
        
            DBConnection conn = new DBConnection();
            ResultSet result = conn.Data_Source(
                    "SELECT "
                            + "idModule, "
                            + "Design, "
                            + "nbrePage "                         
                            + "FROM Module"
            );
            
            while(result.next())
            {
                lst.add(new Module(
                        result.getString("idModule"),
                        result.getString("Design"),
                        result.getInt("nbrePage")
                       
                        )
                );
            }
        
        return lst;
    }
}
