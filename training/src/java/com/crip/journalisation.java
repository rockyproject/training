
package com.crip;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.json.simple.parser.ParseException;
import org.primefaces.model.chart.MeterGaugeChartModel;

@ManagedBean
@RequestScoped
public class journalisation {
    
    private Date dateop=new Date();
    private String idop;
    private String opJourn;
    private Membre membre = new Membre();
    private String roleMbre;
    private double montant;
    private String monnaie;
    private String mouvement;
    private String libelle;
    private String message;
    private List<Legende> legende=new ArrayList<>();
    //private MeterGaugeChartModel model;
    List<String> couleurs = new ArrayList<String>(){{
                                    add("C32239");
                                    add("A56770");
                                    add("7837BB");
                                    add("75718D");
                                    add("57C9D1");
                                    add("46A99B");
                                    add("66cc66");
                                    add("34D292");
                                    add("44EC80");
                                    add("25C724");
                                }};
    
    public journalisation() {
    }

    public Date getDateop() {
        return dateop;
    }

    public void setDateop(Date dateop) {
        this.dateop = dateop;
    }

    public String getIdop() {
        return idop;
    }

    public void setIdop(String idop) {
        this.idop = idop;
    }

    public String getOpJourn() {
        return opJourn;
    }

    public void setOpJourn(String opJourn) {
        this.opJourn = opJourn;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public String getRoleMbre() {
        return roleMbre;
    }

    public void setRoleMbre(String roleMbre) {
        this.roleMbre = roleMbre;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getMonnaie() {
        return monnaie;
    }

    public void setMonnaie(String monnaie) {
        this.monnaie = monnaie;
    }

    public String getMouvement() {
        return mouvement;
    }

    public void setMouvement(String mouvement) {
        this.mouvement = mouvement;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Legende> getLegende() {
        return legende;
    }

    public void setLegende(List<Legende> legende) {
        this.legende = legende;
    }
    
                
    public MeterGaugeChartModel diagrammeSituation()
    {
        MeterGaugeChartModel model;
        
        //String pour stocker la serie des couleurs
        String serieCouleurs="";
        
        //liste des montants
        List<Number> montants = new ArrayList<>();
        
        try{
            DBConnection conn = new DBConnection();
            ResultSet result = conn.Data_Source(
                    "select "
                            + "round((sum(case when monnaie='FC' then montant/taux else montant end))::numeric,2) as mont, "
                            + "roleMbre "
                            + "from journalisation "
                            + "where idmembre='1' "
                            + "group by roleMbre "
                            + "ORDER BY mont"
            );
            
            double somme=0;
            int i=0;            
            while(result.next())
            {
                double mont = Double.parseDouble(result.getString("mont"));
                                
                //Serie des couleurs
                if(i==0)serieCouleurs=serieCouleurs+couleurs.get(i);
                else serieCouleurs=serieCouleurs + "," + couleurs.get(i);
                
                //Alimentation des montants dans la liste                
                montants.add(mont);
                
                //Calcul de la somme
                somme=somme + mont;
                
                i++;
                                
            }
            
            //Ajout de la somme à la liste
            montants.add(Math.ceil(somme));
                        
        }catch(ClassNotFoundException | SQLException | IOException | ParseException ex){
             message=ex.getMessage();
        }
                
        //Personalisation du diagramme
        model = new MeterGaugeChartModel(3, montants);
        model.setTitle("SITUATION FINANCIERE DU MEMBRE");
        model.setSeriesColors(serieCouleurs);
        model.setGaugeLabel("En $");
        model.setGaugeLabelPosition("bottom");
        model.setShowTickLabels(true);
        model.setLabelHeightAdjust(110);
        model.setIntervalOuterRadius(130);
        
        return model;
    }
    
    /*
    METHODE POUR AFFICHER LA LEGENDE
    ================================
    */
    public List<Legende> affichageLegende()
    {
        List<Legende> lst=new ArrayList<>();
        try{
            DBConnection conn = new DBConnection();
            ResultSet result = conn.Data_Source(
                    "select "
                            + "round((sum(case when monnaie='FC' then montant/taux else montant end))::numeric,2) as mont, "
                            + "roleMbre "
                            + "from journalisation "
                            + "where idmembre='1' "
                            + "group by roleMbre "
                            + "ORDER BY mont"
            );
            
            int i=0;            
            while(result.next())
            {
                //Alimentation des donnees dans la legende
                lst.add(new Legende(
                        "#" + couleurs.get(i),
                        result.getString("roleMbre"),
                        Double.parseDouble(result.getString("mont"))
                        )
                );
                
                
                i++;
                                
            }
            
                        
        }catch(ClassNotFoundException | SQLException | IOException | ParseException ex){
             message=ex.getMessage();
        }
        return lst;
    }
}
