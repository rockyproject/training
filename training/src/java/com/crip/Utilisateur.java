
package com.crip;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.json.simple.parser.ParseException;

@ManagedBean
@SessionScoped
public class Utilisateur extends Membre {

    
    public Utilisateur() {
        super.idMembre = "";
        super.motPass = "";
    }
    
    public String connecter(){
        this.message = "";
        try {  
            String account;
            DBConnection conn = new DBConnection();
            account = conn.Show_Data("select count(*) as nbre from utilisateur "
                    + "where idutilisateur='" + super.idMembre + "' "
                    + "and motdepasse='" + super.motPass + "'", 
                    "nbre", 1);
            if(!account.equals("0"))
            {                
                //VERIFICATION DU MOT DE PASSE PAR DEFAUT
                //=======================================
                if(super.motPass.contains("rck"))
                {
                    this.motPass = "";
                    this.confirmPassWord = "";
                    return "psw";
                }
                else{
                    ResultSet result = conn.Data_Source(
                        "SELECT "
                        + "idmembre, "
                        + "nom, "
                        + "postnom, "
                        + "prenom, "
                        + "sexe "
                        + "FROM membre "
                        + "WHERE idmembre='" + super.idMembre + "'"
                    );

                    while(result.next())
                    {
                        this.idMembre = result.getString("idmembre");
                        this.nom = result.getString("nom");
                        this.postNom = result.getString("postnom");
                        this.prenom = result.getString("prenom");
                        this.sexe = result.getString("sexe");
                    }
                    return "main";
                }
                
            }
            else{
                this.message = "ID ou Mot de passe incorrect, corrigez et réessayez!";
                return "login";
            }
            
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            this.message = ex.getMessage();
            return "login";
        }
        
    }
    
    public String annuler(){
        return "index";
    }
    
    public String changerPSWD()
    {
        this.message = "";
        try {
            //VERIFICATION CONFIRMATION
                //=========================
                if(!super.motPass.equals(super.confirmPassWord))
                {
                    this.message = "La confirmation du mot de passe n'est pas le même que le mot de passe";
                    return "psw";
                }
                else{
                    DBConnection conn = new DBConnection();
                    conn.Execute_Query("UPDATE utilisateur SET motdepasse='" + super.motPass + "' "
                            + "where idutilisateur='" + super.idMembre + "'"                    
                    );
                    this.message = "Votre mot de passe a été modifié avec succès";
                }
            
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            this.message = ex.getMessage();
        }
        return "login";
    }
}
