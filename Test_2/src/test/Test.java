/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import java.util.List;
import test.workshop.services.ColisCRUD;
import test.workshop.model.Colis;
import test.workshop.utils.MyConnection;
/**
 *
 * @author hp
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
            MyConnection conn = MyConnection.getInstance();
            Colis colis = new Colis("Expediteur 1", "Adresse 1", "Destinataire 1", "Adresse 2", 10); 
            ColisCRUD a = new ColisCRUD();
             a.ajouterColis(colis);
            System.out.println( a.afficherColis());

        
    }
    
}
