
package com.crip;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.util.Date;

@Named(value = "evaluationBean")
@RequestScoped
public class EvaluationBean {

    public EvaluationBean() {
    }
    
    private String idMembre;
    private String idModule;
    private String idEvaluateur;
    private Date date;
    private int cote;

    public String getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(String idMembre) {
        this.idMembre = idMembre;
    }

    public String getIdModule() {
        return idModule;
    }

    public void setIdModule(String idModule) {
        this.idModule = idModule;
    }

    public String getIdEvaluateur() {
        return idEvaluateur;
    }

    public void setIdEvaluateur(String idEvaluateur) {
        this.idEvaluateur = idEvaluateur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCote() {
        return cote;
    }

    public void setCote(int cote) {
        this.cote = cote;
    }
}
