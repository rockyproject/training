/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crip;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author JUSTIN KABANZA
 */
@ManagedBean
@SessionScoped
public class Evaluation {
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    

    /**
     * Creates a new instance of Evaluation
     */
    public Evaluation() {
    }
    
    public String selectMembre(){
        return "selectMembre";
    }
    
}
