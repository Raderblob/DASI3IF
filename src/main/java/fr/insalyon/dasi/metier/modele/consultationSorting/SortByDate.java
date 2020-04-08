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
public class SortByDate implements Comparator<Consultation>{

    @Override
    public int compare(Consultation o1, Consultation o2) {
        long d1,d2;
        
        if(o1.getDate()==null){
            d1=Long.MAX_VALUE;
        }else{
            d1 = o1.getDate().getTime();
        }
        if(o2.getDate()==null){
            d2=Long.MAX_VALUE;
        }else{
            d2 = o2.getDate().getTime();
        }
        
        
        return -(int)(d1-d2);
    }
    
}
