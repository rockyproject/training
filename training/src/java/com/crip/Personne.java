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
public class Personne {
    protected String idPers= generateIdMembre();
    protected String tel;
    protected String email;
    protected String adresse;
    private String message;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    

    /**
     * Creates a new instance of Personne
     */
    public Personne() {
    }
    
          
    //GENERATION DU idMembre
    //======================
    public String generateIdMembre(){
        String id="";
        try {            
            DBConnection conn = new DBConnection();
            id = conn.Show_Data("select id from (select ((random()*10000000)::int)::varchar(10) AS id) t where id not in (select idmembre from membre)", "id", 1);
            
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            this.message = ex.getMessage();
        }
        return id;
    }
    
    
}
