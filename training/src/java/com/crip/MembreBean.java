
package com.crip;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;


@Named(value = "membreBean")
@RequestScoped
public class MembreBean {

   
    public MembreBean() {
        
    }
    private String idMembre;
    private String nomMembre;
    private String postnomMembre;
    private String prenomMembre;
    private String sexeMembre;

    
    public String getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(String idMembre) {
        this.idMembre = idMembre;
    }

    public String getNomMembre() {
        return nomMembre;
    }

    public void setNomMembre(String nomMembre) {
        this.nomMembre = nomMembre;
    }

    public String getPostnomMembre() {
        return postnomMembre;
    }

    public void setPostnomMembre(String postnomMembre) {
        this.postnomMembre = postnomMembre;
    }

    public String getPrenomMembre() {
        return prenomMembre;
    }

    public void setPrenomMembre(String prenomMembre) {
        this.prenomMembre = prenomMembre;
    }
   
    public String getSexeMembre() {
        return sexeMembre;
    }
    
    public void setSexeMembre(String sexeMembre) {
        this.sexeMembre = sexeMembre;
    }
    
}
