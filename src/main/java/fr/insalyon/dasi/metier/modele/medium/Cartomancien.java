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
public class Cartomancien extends Medium implements Serializable {

    public Cartomancien() {
    }

    public Cartomancien( String name, String Presentation, Gender myGender) {
        super( name, Presentation, myGender);
    }
    
}
