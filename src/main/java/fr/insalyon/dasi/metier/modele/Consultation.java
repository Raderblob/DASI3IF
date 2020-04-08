/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import fr.insalyon.dasi.metier.modele.medium.Medium;
import fr.insalyon.dasi.metier.modele.personne.Client;
import fr.insalyon.dasi.metier.modele.personne.Employee;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Rader
 */
@Entity
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    private String comment;
    private Integer ConsultationLength;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    private Boolean accepted;
    
    private Client caller;
    
    private Employee acceptor;
    
    private Medium medium;

    public Consultation(){
    }
    
    public Consultation( Client caller, Medium medium) {
        this.accepted = false;
        this.caller = caller;
        this.medium =  medium;
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public Integer getConsultationLength() {
        return ConsultationLength;
    }

    public Date getDate() {
        return date;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public Client getCaller() {
        return caller;
    }

    public Employee getAcceptor() {
        return acceptor;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setConsultationLength(Integer ConsultationLength) {
        this.ConsultationLength = ConsultationLength;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public void setCaller(Client caller) {
        this.caller = caller;
    }

    public void setAcceptor(Employee acceptor) {
        this.acceptor = acceptor;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Consultation{" + "id=" + id + ", comment=" + comment + ", ConsultationLength=" + ConsultationLength + ", date=" + date + ", accepted=" + accepted + ", caller=" + caller + ", acceptor=" + acceptor + ", medium=" + medium + '}';
    }
      
    
    
}
