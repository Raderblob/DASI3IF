/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import fr.insalyon.dasi.metier.modele.personne.Client;
import fr.insalyon.dasi.metier.modele.personne.Employee;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private Boolean accepted;
    private Client caller;
    private Employee acceptor;

    public Consultation(){
        
    }
    public Consultation(String comment, Integer ConsultationLength, Boolean accepted, Client caller, Employee acceptor) {
        this.comment = comment;
        this.ConsultationLength = ConsultationLength;
        this.accepted = accepted;
        this.caller = caller;
        this.acceptor = acceptor;
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
      
    
    
}
