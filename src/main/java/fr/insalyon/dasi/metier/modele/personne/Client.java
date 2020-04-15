package fr.insalyon.dasi.metier.modele.personne;

import fr.insalyon.dasi.metier.modele.Consultation;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import util.AstroTest;

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
    private List<Consultation> myConsultationHistory;
    private String signeZodiac;
    private String signeAstroChonois;
    private String couleurPorteBonheur;
    private String animalTotem;


    
    public Client(String birthDate, String postcode, String nom, String prenom, String mail, String motDePasse, String telephoneNumber) {
        super(nom, prenom, mail, motDePasse, telephoneNumber);
        AstroTest a=new AstroTest();
        try{
            this.birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);
            List<String> profil=a.getProfil(prenom, this.birthDate);
            String signeZodiaque = profil.get(0);
            String signeChinois = profil.get(1);
            String couleur = profil.get(2);
            String animal = profil.get(3);
            this.signeZodiac=signeZodiaque;
            this.signeAstroChonois=signeChinois;
            this.animalTotem=animal;
            this.couleurPorteBonheur=couleur;
        
        }catch(Exception ex){
            this.birthDate = new Date();
        }
        
       
        
        this.postcode = postcode;
        this.myConsultationHistory = new ArrayList();
    }
    
    
    public String getSigneZodiac() {
        return signeZodiac;
    }

    public String getSigneAstroChonois() {
        return signeAstroChonois;
    }

    public String getCouleurPorteBonheur() {
        return couleurPorteBonheur;
    }

    public String getAnimalTotem() {
        return animalTotem;
    }
    

    public Client() {
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
    
    public List<Consultation> getMyConsultationHistory() {
        return myConsultationHistory;
    }

    public void setMyConsultationHistory(List<Consultation> myconsultationHistory) {
        this.myConsultationHistory = myconsultationHistory;
    }

    @Override
    public String toString() {
        return "Client{" + "birthDate=" + birthDate + ", postcode=" + postcode + ", myConsultationHistory=" + myConsultationHistory.size() + ", signeZodiac=" + signeZodiac + ", signeAstroChonois=" + signeAstroChonois + ", couleurPorteBonheur=" + couleurPorteBonheur + ", animalTotem=" + animalTotem + '}';
    }

    
}
