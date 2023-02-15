/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.services;

/**
 *
 * @author hp
 */
import test.workshop.interfaces.InterfacesDCRUD;
import test.workshop.model.DocumentExpedition;
import test.workshop.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
import test.workshop.model.Colis;


public class DocumentExpeditionCRUD implements InterfacesDCRUD {
Statement ste;
Connection conn = MyConnection.getInstance().getConn();
@Override   
public void supprimerDocument(int id)  {
    try {
    String sql = "DELETE FROM DocumentExpedition WHERE id = ?";
    PreparedStatement preparedStatement = conn.prepareStatement(sql);
    preparedStatement.setInt(1, id);
    preparedStatement.executeUpdate();
    System.out.println("Document deleted !");
}catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}

@Override
public void modifierDocument(DocumentExpedition d) {
     try {
     String sql = "UPDATE DocumentExpedition SET dateSignature=?, colis_id=?, statut=? WHERE id=?";
    PreparedStatement preparedStmt = conn.prepareStatement(sql);
    preparedStmt.setDate(1,d.getDateSignature());
    preparedStmt.setString(3, d.getStatut());
    preparedStmt.setInt(4, (int) d.getId());
    preparedStmt.executeUpdate();

System.out.println("Colis updated !");
} catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}

    /**
     *
     * me
     * 
     * 
     * @param id
     * @return
     */
}

    
