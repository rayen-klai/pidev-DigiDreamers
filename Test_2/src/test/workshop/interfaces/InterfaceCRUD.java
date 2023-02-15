/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.interfaces;

/**
 *
 * @author hp
 */
import test.workshop.model.Colis;
import java.util.List;

/**
 *
 * @author belkn
 */
public interface InterfaceCRUD {
    
    public void ajouterColis(Colis c);
    public void modifierColis(Colis c);
    public void supprimerColis(int id) ;
    public List<Colis> afficherColis();
}
    