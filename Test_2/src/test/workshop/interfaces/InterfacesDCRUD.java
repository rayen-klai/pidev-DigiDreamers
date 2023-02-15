/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.interfaces;

import java.sql.SQLException;
import java.util.List;
import test.workshop.model.DocumentExpedition;

/**
 *
 * @author hp
 */
public interface InterfacesDCRUD {
    public void modifierDocument(DocumentExpedition d);
    public void supprimerDocument(int id) ;
}
    