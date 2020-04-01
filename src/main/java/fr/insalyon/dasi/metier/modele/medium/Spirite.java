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
public class Spirite extends Medium implements Serializable {
    private String support;

 

    public Spirite() {
    }

    public Spirite(String support, String name, String Presentation, Gender myGender) {
        super( name, Presentation, myGender);
        this.support = support;
    }
    
    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }
    
}
