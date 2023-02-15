/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.services;

import java.sql.Connection;
import test.workshop.interfaces.InterfaceCRUD;
import test.workshop.utils.MyConnection;
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
import test.workshop.model.DocumentExpedition;

/**
 *
 * @author hp
 */
public class DocumentExpeditionCRUD implements InterfaceCRUD<DocumentExpedition> {
    private Connection conn = MyConnection.getInstance().getConn();

    @Override
    public void ajouter(DocumentExpedition d) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO documentexpedition (dateSignature, id_colis, statut) VALUES (?,?,?)");
            stmt.setObject(1, d.getDateSignature());
            stmt.setInt(2, d.getId_colis());
            stmt.setString(3, d.getStatut());
            stmt.executeUpdate();
            System.out.println("Document d'expédition ajouté");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String sql = "DELETE FROM documentexpedition WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Document d'expédition supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(DocumentExpedition d) {
        try {
            String sql = "UPDATE documentexpedition SET dateSignature = ?, id_colis = ?, statut = ? WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setObject(1, d.getDateSignature());
            preparedStatement.setInt(2, d.getId_colis());
            preparedStatement.setString(3, d.getStatut());
            preparedStatement.setInt(4, d.getId());
            preparedStatement.executeUpdate();
            System.out.println("Document d'expédition modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<DocumentExpedition> afficher() {
        List<DocumentExpedition> list = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM documentexpedition");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DocumentExpedition d = new DocumentExpedition();
                d.setId(resultSet.getInt("id"));
                d.setDateSignature(resultSet.getDate("dateSignature"));
                d.setId_colis(resultSet.getInt("id_colis"));
                d.setStatut(resultSet.getString("statut"));
                list.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public DocumentExpedition getByID(int id) {
        DocumentExpedition d = null;
        String query = "SELECT * FROM documentexpedition WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                d = new DocumentExpedition();
                d.setId(resultSet.getInt("id"));
                d.setDateSignature(resultSet.getDate("dateSignature"));
                d.setId_colis(resultSet.getInt("id_colis"));
                d.setStatut(resultSet.getString("statut"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }
    public List<DocumentExpedition> trierParDateExpedition() {
    List<DocumentExpedition> documentsTries = new ArrayList<>();
    try {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM document_expedition ORDER BY dateExpedition ASC");
        ResultSet result = stmt.executeQuery();
        while (result.next()) {
            DocumentExpedition doc = new DocumentExpedition();
            doc.setId(result.getInt("id"));
            doc.setStatut(result.getString("statut"));
            doc.setId_colis(result.getInt("colis_id"));
            doc.setDateSignature((Date) result.getObject("dateSignature"));
            documentsTries.add(doc);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return documentsTries;
}
}