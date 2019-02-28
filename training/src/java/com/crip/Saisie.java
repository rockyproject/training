
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
    private Formation formation=new Formation();
    private Evaluation evaluation=new Evaluation();
    private Paiement paiement=new Paiement();
    private Module module=new Module();
    private Enseigner enseigner=new Enseigner();
    private Universite universite=new Universite();
    private Formateur formateur=new Formateur();

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
    
    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public Paiement getPaiement() {
        return paiement;
    }

    public void setPaiement(Paiement paiement) {
        this.paiement = paiement;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Enseigner getEnseigner() {
        return enseigner;
    }

    public void setEnseigner(Enseigner enseigner) {
        this.enseigner = enseigner;
    }

    public Universite getUniversite() {
        return universite;
    }

    public void setUniversite(Universite universite) {
        this.universite = universite;
    }

    public Formateur getFormateur() {
        return formateur;
    }

    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
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
    
     public String EnregistrerFormation(){
         try{
             Formation form=new Formation();
             form.Enregistrer(formation);
             message = "Enregistrement effectué avec succès";
             formation.setIdMembre("");
             formation.setIdFormateur("");
             formation.setIdModule("");
 //            formation.setHeure();
  //           formation.setDateDebut("");
  //           formation.setDateFin("");
             formation.setUniversite("");
             
         }catch(ClassNotFoundException | SQLException | IOException | ParseException ex){
             message=ex.getMessage();
         }
         return "formation";
     }
     
     public String EnregistrerEvaluation(){
         try{
             Evaluation eval=new Evaluation();
             eval.Enregistrer(evaluation);
             message="Enregistrement effectué avec succès";
             evaluation.setIdMembre("");
             evaluation.setIdModule("");
             evaluation.setIdEvaluateur("");
             //evaluation.setDate(date);
             evaluation.setCote(0);
             //evaluation.setDate(date);
         }catch(ClassNotFoundException | SQLException | IOException | ParseException ex){
             message=ex.getMessage();
         }
         return "evaluation";
     }
     
     public String EnregistrerPaiement(){
         try{
             Paiement paie=new Paiement();
             paie.Enregistrer(paiement);
             message="Enregistrement effectué avec succès";
             paiement.setNumBord("");
             paiement.setIdMembre("");
             paiement.setIdModule("");
//             paiement.setDateP(dateP);
             paiement.setMontant(0);
             paiement.setMonnaie("");
             paiement.setTaux("");
         }catch(ClassNotFoundException | SQLException | IOException | ParseException ex){
             message=ex.getMessage();
         }
         return "paiement";
     }
     
     public String EnregistrerModule(){
         try{
             Module mod=new Module();
             mod.Enregistrer(module);
             message="Enregistrement effectué avec succès";
             module.setIdModule("");
             module.setDesignation("");
             module.setNbrePage(0);
         }catch(ClassNotFoundException | SQLException | IOException | ParseException ex){
             message=ex.getMessage();
         }
         return "module";
     }
     
     
     
     public String EnregistrerEnseigner(){
         try{
             Enseigner ens=new Enseigner();
             ens.Enregistrer(enseigner);
             message="Enregistrement effectué avec succès";
             enseigner.setIdFormateur("");
             enseigner.setIdModule("");
         }catch(ClassNotFoundException | SQLException | IOException | ParseException ex){
             message=ex.getMessage();
         }
         return "enseigner";
     }
     
     public String EnregistrerUniversite(){
         try{
             Universite univ=new Universite();
             univ.Enregistrer(universite);
             message="Enregistrement effectué avec succès";
             universite.setNomUniv("");
         }catch(ClassNotFoundException | SQLException | IOException | ParseException ex){
             message=ex.getMessage();
         }
         return "universite";
     }
     
     public String EnregistrerFormateur(){
         try{
             Formateur form=new Formateur();
             form.Enregistrer(formateur);
             message="Enregistrement effectué avec succès";
             formateur.setIdFormateur("");
         }catch(ClassNotFoundException | SQLException | IOException | ParseException ex){
             message=ex.getMessage();
         
         }
         return "formateur";
     }
    
    public Saisie() {
    }
    
}
