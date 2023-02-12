/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena;

import amena.model.Vehicule;
import amena.services.VehiculeCRUD;

/**
 *
 * @author klair
 */
public class Amena {

    public static void main(String[] args) {
        Vehicule v1 = new Vehicule("Voiture", "110 tun 1225", false,"0",0, "bmw","rouge",150);
        Vehicule v2 = new Vehicule("Moto", "55456", false,"0",0, "103","noir",75);
        Vehicule v3 = new Vehicule("Velo","", false,"0",0, "Btwin","vert",30);
        
        VehiculeCRUD vc = new VehiculeCRUD(); 
       
      /*  vc.ajouter(v1) ;
        vc.ajouter(v2) ;
        vc.ajouter(v3) ;
        System.out.println(vc.afficher());
      */  
       Vehicule v4 = new Vehicule("Voiture", "110 tun 1225", true,"10000",0, "bmw","rouge",150);
        vc.modifier(7, v4);
      /*  System.out.println(vc.afficher());
        
        vc.supprimer(10);
        vc.supprimer(11);
        vc.supprimer(12);
        vc.supprimer(13);
        vc.supprimer(9);

        */
        System.out.println(vc.afficher());

        System.out.println(vc.getByID(14));
        
        System.out.println(vc.filterByEtat(0));


        

    }
    
}
