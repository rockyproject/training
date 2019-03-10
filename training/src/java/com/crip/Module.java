
package com.crip;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.parser.ParseException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
//import javax.faces.bean.RequestScoped;

@ManagedBean
//@RequestScoped
@SessionScoped

public class Module {
    private String idModule;
    private String designation;
    private int nbrePage;    
    private String message;
    private String action;
    
    //CONSTRUCTEUR
    //============
    public Module(){
    
    }
         
    public Module(String idModule,String designation, int nbrePage){
    this.idModule=idModule;
    this.designation=designation;
    this.nbrePage=nbrePage;
    }

    //ACCESSEURS
    //==========
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
 
    //SAISIE
    //=====
    public String saisie()
    {
        return "module";
    }
            
    //ENREGISTREMENT
    //==============
    public String enregistrer(){        
        try {
            DBConnection cnx=new DBConnection();
        cnx.Execute_Query("Insert into module (idmodule,design,nbrePage) VALUES ("
                + "'"+ idModule +"',"
                + "'"+ designation +"',"
                +  nbrePage +")"
                ); 
            message = "Enregistrement effectué avec succès";
            idModule = "";
            designation = "";
            nbrePage = 0;
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            message= ex.getMessage();
        }
        return "module";
    }
    

    //LISTE DES MODULES
    //=================
    public List<Module> liste(){
        
        List<Module> lst = new ArrayList<>();
        try{
            
            DBConnection conn = new DBConnection();
            ResultSet result = conn.Data_Source(
                    "SELECT "
                            + "idmodule, "
                            + "design, "
                            + "nbrepage "                            
                            + "FROM module"
            );
            
            while(result.next())
            {
                lst.add(new Module(
                        result.getString("idmodule"),
                        result.getString("design"),
                        Integer.parseInt(result.getString("nbrepage")) 
                        )
                );
            }
        }catch(ClassNotFoundException | SQLException | IOException | ParseException ex){
             message=ex.getMessage();
        }
        
        return lst;
    }
    
    //AFFICHAGE DE LA PAGE DE SELECTION DU MODULE
    //===========================================
    public String selection()
    {
        return "selectModule";
    }
    
    //ACTION A MENER APRES SELECTION DU MODULE
    //========================================
    public String selection(Module module){
        //this.sModule = module;
        this.idModule = module.idModule;
        this.designation = module.designation;
        this.nbrePage = module.nbrePage;
        //this.action = action;
        String valeur;
        switch(action){
            case "Inscription":
                valeur = "selectMembre";
                break;
            default:
                valeur = "selectModule";
                break;
        }
        return valeur;
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
    
    //RETOUR
    //======
    public String retour(){
        return "main";
    }
}
