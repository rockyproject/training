
package com.crip;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.simple.parser.ParseException;

/**
 *
 * @author JUSTIN KABANZA
 */
public class Formation {
    private String idMembre;
    private String idFormateur;
    private Date dateDebut;
    private Date dateFin;
    private String idModule;
    private Date heure;
    private String universite;

    public String getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(String idMembre) {
        this.idMembre = idMembre;
    }

    public String getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(String idFormateur) {
        this.idFormateur = idFormateur;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getIdModule() {
        return idModule;
    }

    public void setIdModule(String idModule) {
        this.idModule = idModule;
    }

    public Date getHeure() {
        return heure;
    }

    public void setHeure(Date heure) {
        this.heure = heure;
    }

    public String getUniversite() {
        return universite;
    }

    public void setUniversite(String universite) {
        this.universite = universite;
    }
    
    public Formation(){
        
    }
    
    public Formation(String idMembre, String idModule,String idFormateur, Date heure, Date dateDebut, Date dateFin, String universite){
       this.idMembre= idMembre;
       this.idModule=idModule;
       this.idFormateur=idFormateur;
       this.heure=heure;
       this.dateDebut=dateDebut;
       this.dateFin=dateFin;
       this.universite=universite;
    }
    
    public void Enregistrer(Formation form)throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, ParseException{
        DBConnection cnx=new DBConnection();
        cnx.Execute_Query("Insert into Formation (IdMembre,IdFormateur,IdModule,"
                + "Heure,DateDebut,DateFin,universite) VALUES ("
                + "'"+ form.idMembre +"','"+ form.idFormateur +"','"+ form.idModule +"','"+ form.heure +"'"
                + "'"+ form.dateDebut +"','"+ form.dateFin +"','"+ form.universite +"' )"
        );
        
    }
    
    public List<Formation> listeFormation() throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, ParseException{
        List<Formation> lst = new ArrayList<>();
        
            DBConnection conn = new DBConnection();
            ResultSet result = conn.Data_Source(
                    "SELECT "
                            +"select (select concat (nom,' ',postnom,' ',prenom) from Membre \n" +
                            "where IdMembre=Formation.IdFormateur)as Apprenant,\n" +
                            "(select concat (nom,' ',postnom,' ',prenom) from Membre inner join \n" +
                            "Formateur on Membre.IdMembre=Formateur.IdFormateur \n" +
                            "where IdFormateur=Formation.IdFormateur)as formateur,\n" +
                            "design,\n" +
                            "Heure,DateDebut,DateFin,universite from Formation inner join Module\n" +
                            "on Formation.IdModule=Module.IdModule"
                        );
            
            while(result.next())
            {
                lst.add(new Formation(
                        result.getString("Apprenant"),
                        result.getString("design"),
                        result.getString("formateur"),                       
                        result.getDate("Heure"),
                        result.getDate("DateDebut"),
                        result.getDate("DateFin"),
                        result.getString("universite")
                        )
                );
            }
        return lst;
    }
}
