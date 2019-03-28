
package com.crip;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.json.simple.parser.ParseException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
//import javax.faces.bean.RequestScoped;

@ManagedBean
//@RequestScoped
@SessionScoped

public class Module {
    @ManagedProperty(value="#{miseAjour}")
    MiseAjour maj;
    
    private String idModule=generateIdModule();
    private String designation;
    private int nbrePage;    
    private String message;
    private String action;
    private String auteurs;
    private Membre auteur;
    
    
    private ActionMessage actionMess;
            
    //CONSTRUCTEUR
    //============
    public Module(){
    
    }
         
    public Module(String idModule,String designation, String auteurs){
    this.idModule=idModule;
    this.designation=designation;
    this.auteurs=auteurs;
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

    public MiseAjour getMaj() {
        return maj;
    }

    public void setMaj(MiseAjour maj) {
        this.maj = maj;
    }

    public ActionMessage getActionMess() {
        return actionMess;
    }

    public void setActionMess(ActionMessage actionMess) {
        this.actionMess = actionMess;
    }

    public Membre getAuteur() {
        return auteur;
    }

    public void setAuteur(Membre auteur) {
        this.auteur = auteur;
    }

    public String getAuteurs() {
        return auteurs;
    }

    public void setAuteurs(String auteurs) {
        this.auteurs = auteurs;
    }
    
    
    
    //SAISIE
    //=====
    public String saisie()
    {
        return "module";
    }
    
    public String selectMembre(){
        return "selectMembre";
    }
            
    //ENREGISTREMENT
    //==============
    public String enregistrer(){        
        try {
            DBConnection cnx=new DBConnection();
            cnx.Execute_Query("Insert into module (idmodule,design,auteur) VALUES ("
                + "'"+ idModule +"',"
                + "'"+ designation +"',"
                +  "'"+this.auteur.idMembre +"')"
                ); 
            message = "";
            idModule = this.generateIdModule();
            designation = "";
            this.auteur=new Membre();
            nbrePage = 0;
            return "main";
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            message= ex.getMessage();
            return "module";
        }       
    }
    
    //MODIFICATION
    //============
    public String modifier(){
        //this.message = maj.getModule().designation;
        try {
            DBConnection cnx=new DBConnection();
            cnx.Execute_Query("UPDATE module SET "
                + "idmodule='"+ idModule +"', "
                + "design='"+ designation +"', "
                + "nbrepage=" + nbrePage +" "
                + "WHERE "
                + "idmodule='" + maj.getModule().idModule + "'"
                ); 
            message = "";
            idModule = "";
            designation = "";
            nbrePage = 0;
            return "main";
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            message= ex.getMessage();
            return "module";
        }
        
    }
    
    public String supprimer(){
        try {
            DBConnection cnx=new DBConnection();
            cnx.Execute_Query("DELETE FROM module "
                + "WHERE "
                + "idmodule='" + maj.getModule().idModule + "'"
                ); 
            message = "";
            idModule = "";
            designation = "";
            nbrePage = 0;
            return "selectModule";
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            message= ex.getMessage();
            return "module";
        }
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
                            + "(select concat (nom,' ',postnom,' ',prenom) from membre where idmembre=module.auteur)as Auteur "                            
                            + "FROM module "
                            + "ORDER BY design"
            );
            
            while(result.next())
            {
                lst.add(new Module(
                        result.getString("idmodule"),
                        result.getString("design"),
                        result.getString("Auteur") 
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
                            + "(select concat (nom,' ',postnom,' ',prenom) from membre where idmembre=module.auteur)as Auteur "                         
                            + "FROM Module"
            );
            
            while(result.next())
            {
                lst.add(new Module(
                        result.getString("idModule"),
                        result.getString("Design"),
                        result.getString("Auteur")
                       
                        )
                );
            }
        
        return lst;

    }
    
    //RETOUR
    //======
    public String retour(){
        this.action="";
        return "main";
    }
    
    //GENERATION DE L'ID MODULE
    //==========================
    private String generateIdModule(){
        /*String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 4) + "rck" + uuid.substring(4, 6);*/
        String id="";
        try {            
            DBConnection conn = new DBConnection();
            id = conn.Show_Data("SELECT id from (SELECT substring(md5(random()::text),1,10) AS id) t WHERE id not in(SELECT idModule FROM Module)", "id", 1);
            
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            this.message = ex.getMessage();
        }
        return id;
    }
}
