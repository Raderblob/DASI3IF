package fr.insalyon.dasi.metier.modele.personne;

import fr.insalyon.dasi.metier.modele.Consultation;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author DASI Team
 */
@Entity
public class Client extends Personne implements Serializable  {


    
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private String postcode;
    @OneToMany
    private List<Consultation> myconsultationHistory;

    public Client() {
    }

    public Client(String birthDate, String postcode, String nom, String prenom, String mail, String motDePasse, String telephoneNumber) {
        super(nom, prenom, mail, motDePasse, telephoneNumber);
        
        try{
            this.birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);  ;
        }catch(Exception ex){
            this.birthDate = new Date();
        }
        
        this.postcode = postcode;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    
    

    
}
