/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele.medium;

import fr.insalyon.dasi.metier.modele.Gender;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Rader
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Medium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(unique = true)
    protected String name;
    protected String Presentation;
    protected Gender myGender;

    public Medium() {
    }

    
    
    public Medium(String name, String Presentation, Gender myGender) {
        this.name = name;
        this.Presentation = Presentation;
        this.myGender = myGender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPresentation() {
        return Presentation;
    }

    public void setPresentation(String Presentation) {
        this.Presentation = Presentation;
    }

    public Gender getMyGender() {
        return myGender;
    }

    public void setMyGender(Gender myGender) {
        this.myGender = myGender;
    }

    public Long getId() {
        return id;
    }
    
    
}
