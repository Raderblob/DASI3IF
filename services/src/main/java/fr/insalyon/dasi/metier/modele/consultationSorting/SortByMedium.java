/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele.consultationSorting;

import fr.insalyon.dasi.metier.modele.Consultation;
import java.util.Comparator;

/**
 *
 * @author Rader
 */
public class SortByMedium implements Comparator<Consultation>{

    @Override
    public int compare(Consultation o1, Consultation o2) {
        long id1,id2;
        if(o1.getMedium()==null){
            id1=-1;
        }else{
            id1=(o1.getMedium().getId());
        }
        if(o2.getMedium()==null){
            id2=-1;
        }else{
            id2=(o2.getMedium().getId());
        }
        return (int)(id1-id2);
    }
    
}
