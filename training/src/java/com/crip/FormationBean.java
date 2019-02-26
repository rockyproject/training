
package com.crip;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Date;

@ManagedBean
@RequestScoped
public class FormationBean {

    public FormationBean() {
       
    }
    
    private String idMembre;
    private String idFormation;
    private Date dateDebut;
    private Date dateFin;

    public String getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(String idMembre) {
        this.idMembre = idMembre;
    }

    public String getIdFormation() {
        return idFormation;
    }
    
    public void setIdFormation(String idFormation) {
        this.idFormation = idFormation;
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
}
