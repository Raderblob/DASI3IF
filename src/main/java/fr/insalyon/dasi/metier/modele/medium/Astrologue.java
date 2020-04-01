/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele.medium;

import fr.insalyon.dasi.metier.modele.Gender;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Rader
 */
@Entity
public class Astrologue extends Medium implements Serializable {
    private String formation;
    private Integer promotion;

    public Astrologue() {
    }

  

    public Astrologue(String formation, Integer promotion, String name, String Presentation, Gender myGender) {
        super(name, Presentation, myGender);
        this.formation = formation;
        this.promotion = promotion;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public Integer getPromotion() {
        return promotion;
    }

    public void setPromotion(Integer promotion) {
        this.promotion = promotion;
    }
    
    
}
