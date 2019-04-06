/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crip;

import java.io.IOException;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.json.simple.parser.ParseException;

/**
 *
 * @author JUSTIN KABANZA
 */
@ManagedBean
@SessionScoped
public class Structure extends Personne {
    
    protected String idStruct=generateIdStructure();
    protected String nom;
    protected String sigle;
    protected String siteweb;
    private String message;

    public String getIdStruct() {
        return idStruct;
    }

    public void setIdStruct(String idStruct) {
        this.idStruct = idStruct;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getSiteweb() {
        return siteweb;
    }

    public void setSiteweb(String siteweb) {
        this.siteweb = siteweb;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIdPers() {
        return idPers;
    }

    public void setIdPers(String idPers) {
        this.idPers = idPers;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    

    /**
     * Creates a new instance of Structure
     */
    public Structure() {
    }
    
     //GENERATION DE  L'id de l'université
    //=====================================
    private String generateIdStructure(){
        String id="";
        try {            
            DBConnection conn = new DBConnection();
            id = conn.Show_Data("select id from (select ((random()*10000000)::int)::varchar(10) AS id) t where id not in (select iduniv from Universite)", "id", 1);
            
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            this.message = ex.getMessage();
        }
        return id;
    }
    
}
