
package com.crip;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.json.simple.parser.ParseException;


@ManagedBean
@RequestScoped
public class Saisie {

    private Membre membre = new Membre();
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }
    
    public String enregistrerMembre()
    {
        try {
            Membre mb = new Membre();
            mb.enregistrer(membre);
            message = "Enregistrement effectué avec succès";
            membre.setIdMembre("");
            membre.setNom("");
            membre.setPostNom("");
            membre.setPrenom("");
            membre.setSexe("");
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            message= ex.getMessage();
        }
        
        //message = "Enregistrement effectué avec succès";
        return "membre";
    }
    
        
    
    public Saisie() {
    }
    
}
