/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.interfaces;

import amena.model.Vehicule;
import java.util.List;

/**
 *
 * @author klair
 */
public interface InterfaceCRUD<T> {
    public void ajouter(T t);
    public void modifier(int id,T t);
    public void supprimer(int id) ;
    public List<T> afficher(); 
    public T getByID(int id); 
    
    
}
