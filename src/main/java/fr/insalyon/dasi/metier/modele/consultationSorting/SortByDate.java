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
        return o1.getDate().compareTo(o2.getDate());
    }
    
}
