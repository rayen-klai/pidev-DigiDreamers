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
import static com.oracle.jrockit.jfr.ContentType.Timestamp;
import test.workshop.interfaces.InterfaceCRUD;
import test.workshop.model.Colis;
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
import test.workshop.model.DocumentExpedition;



/**
 *
 * @author hp
 */
public class ColisCRUD implements InterfaceCRUD<Colis> {
Statement ste;
Connection conn = MyConnection.getInstance().getConn();

@Override
public void ajouter(Colis c) {
    System.out.println(c.getDateExpedition());
    try {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO colis (nomExpediteur, adresseExpediteur, nomDestinataire, adresseDestinataire, poids, statut, dateExpedition) VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, c.getNomExpediteur());
        stmt.setString(2, c.getAdresseExpediteur());
        stmt.setString(3, c.getNomDestinataire());
        stmt.setString(4, c.getAdresseDestinataire());
        stmt.setFloat(5, c.getPoids());
        stmt.setString(6, c.getStatut());
        stmt.setObject(7,c.getDateExpedition());
        int affectedRows = stmt.executeUpdate();
        
        if (affectedRows == 0) {
            throw new SQLException("Insertion du colis a échoué, aucune ligne affectée.");
        }
        
        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int colisId = generatedKeys.getInt(1);
                
                PreparedStatement stmtDoc = conn.prepareStatement("INSERT INTO documentexpedition (colis_id, dateSignature, statut) VALUES (?,?,?)");
                stmtDoc.setInt(1, colisId);
                stmtDoc.setDate(2, new java.sql.Date(System.currentTimeMillis()));
                stmtDoc.setString(3, "Non signé");
                stmtDoc.executeUpdate();  
                System.out.println("Le document d'expédition a été créé pour le colis avec l'ID " + colisId);
            } else {
                throw new SQLException("Insertion du colis a échoué, aucun ID généré.");
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

    /**
     *
     * @param id
     * @throws SQLException
     */
@Override
public void supprimer(int id)  {
    try {
   /* PreparedStatement statement1 = conn.prepareStatement("DELETE FROM documentexpedition WHERE colis_id = ?");
    statement1.setInt(1, id);
    statement1.executeUpdate();*/
    String sql = "DELETE FROM colis WHERE id = ?";
    PreparedStatement preparedStatement = conn.prepareStatement(sql);
    preparedStatement.setInt(1, id);
    preparedStatement.executeUpdate();
    System.out.println("Colis deleted !");
}catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}

@Override
public void modifier(Colis colis) {
     try {
String sql = "UPDATE colis SET nomExpediteur = ?, adresseExpediteur = ?, nomDestinataire = ?, adresseDestinataire = ?, poids = ?, statut = ?, dateExpedition = ? WHERE id = ?";
PreparedStatement preparedStatement = conn.prepareStatement(sql);
preparedStatement.setString(1, colis.getNomExpediteur());
preparedStatement.setString(2, colis.getAdresseExpediteur());
preparedStatement.setString(3, colis.getNomDestinataire());
preparedStatement.setString(4, colis.getAdresseDestinataire());
preparedStatement.setFloat(5, colis.getPoids());
preparedStatement.setString(6, colis.getStatut());
if (colis.getDateExpedition() != null) {
preparedStatement.setDate(7,(colis.getDateExpedition()));
} else {
preparedStatement.setDate(7, null);
}
preparedStatement.setInt(8, colis.getId());
preparedStatement.executeUpdate();
System.out.println("Colis updated !");
}catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}

    /**
     *
     * @param id
     * @return
     */

@Override
public List<Colis> afficher() {
    List<Colis> list = new ArrayList<>();
    try {
      PreparedStatement statement = conn.prepareStatement("Select * from colis");
      ResultSet resultSet = statement.executeQuery();
      while(resultSet.next()){
          Colis colis = new Colis(
          resultSet.getInt("id_Colis"),
          resultSet.getString("nomExpediteur"),
          resultSet.getString("adresseExpediteur"),
          resultSet.getString("nomDestinataire"),
          resultSet.getString("adresseDestinataire"),
          resultSet.getFloat("poids")
        );
        colis.setStatut(resultSet.getString("statut"));
        colis.setDateExpedition(resultSet.getDate("dateExpedition"));
        list.add(colis);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
return list;
}
 public ColisCRUD() {
    }

    /**
     *
     * @param id
     * @return
     */

@Override
public Colis getByID(int id){
    Colis colis = null;
  String query = "SELECT * FROM colis WHERE id_Colis = ?";
  try (PreparedStatement statement = conn.prepareStatement(query)) {
    statement.setInt(1, id);
    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      colis = new Colis(
          resultSet.getInt("id_Colis"),
          resultSet.getString("nomExpediteur"),
          resultSet.getString("adresseExpediteur"),
          resultSet.getString("nomDestinataire"),
          resultSet.getString("adresseDestinataire"),
          resultSet.getFloat("poids")
      );
      colis.setStatut(resultSet.getString("statut"));
      colis.setDateExpedition(resultSet.getDate("dateExpedition"));
    }
  } catch (SQLException e) {
    e.printStackTrace();
  }
  return colis;
}
public List<Colis> trierParDateExpedition() {
    List<Colis> colisTries = new ArrayList<>();
    try {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM colis ORDER BY dateExpedition ASC");
        ResultSet result = stmt.executeQuery();
        while (result.next()) {
            Colis c = new Colis();
            c.setId(result.getInt("id"));
            c.setNomExpediteur(result.getString("nomExpediteur"));
            c.setAdresseExpediteur(result.getString("adresseExpediteur"));
            c.setNomDestinataire(result.getString("nomDestinataire"));
            c.setAdresseDestinataire(result.getString("adresseDestinataire"));
            c.setPoids(result.getFloat("poids"));
            c.setStatut(result.getString("statut"));
            c.setDateExpedition((Date) result.getObject("dateExpedition"));
            colisTries.add(c);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return colisTries;
}

}
