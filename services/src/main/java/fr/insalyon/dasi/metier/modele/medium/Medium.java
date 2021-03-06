/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele.medium;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Gender;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

/**
 *
 * @author Rader
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Medium implements Serializable, Comparable< Medium > {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(unique = true)
    protected String name;
    protected String Presentation;
    protected Gender myGender;
    
    @OneToMany
    private List<Consultation> consultations;
    
    public Medium() {
    }

    
    
    public Medium(String name, String Presentation, Gender myGender) {
        this.name = name;
        this.Presentation = Presentation;
        this.myGender = myGender;
        consultations = new ArrayList();
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

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    @Override
    public String toString() {
        return "Medium{" + "id=" + id + ", name=" + name + ", Presentation=" + Presentation + ", myGender=" + myGender + '}' + " numConsult=" + consultations.size() + ' ' ;
    }
    
    @Override
    public int compareTo(Medium o) {
        return -this.getConsultations().size()+o.getConsultations().size();
    }
    
}
