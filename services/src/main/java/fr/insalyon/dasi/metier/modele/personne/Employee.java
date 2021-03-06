package fr.insalyon.dasi.metier.modele.personne;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Gender;
import fr.insalyon.dasi.metier.modele.personne.Personne;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author DASI Team
 */
@Entity
public class Employee extends Personne implements Serializable  {


    private Gender myGender;
    private Integer numConsultations;
    private Boolean available;
    
    @OneToMany
    private List<Consultation> consultations;
    
    
    public Employee(Gender myGender, Integer numConsultations, Boolean available, String nom, String prenom, String mail, String motDePasse, String telephoneNumber) {
        super(nom, prenom, mail, motDePasse, telephoneNumber);
        this.myGender = myGender;
        this.numConsultations = numConsultations;
        this.available = available;
    }
   

    
    
    public Employee() {
    }
    
    public Gender getMyGender() {
        return myGender;
    }

    public void setMyGender(Gender myGender) {
        this.myGender = myGender;
    }

    public Integer getNumConsultations() {
        return numConsultations;
    }

    public void setNumConsultations(Integer numConsultations) {
        this.numConsultations = numConsultations;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

   

    

   

 
    
    

    
}
