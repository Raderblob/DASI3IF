package fr.insalyon.dasi.metier.modele;

import fr.insalyon.dasi.metier.modele.Gender;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-01T13:50:11")
@StaticMetamodel(Employee.class)
public class Employee_ extends Personne_ {

    public static volatile SingularAttribute<Employee, Gender> myGender;
    public static volatile SingularAttribute<Employee, Boolean> available;
    public static volatile SingularAttribute<Employee, Integer> numConsultations;

}