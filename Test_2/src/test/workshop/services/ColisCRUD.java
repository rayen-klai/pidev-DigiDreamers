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
public class ColisCRUD implements InterfaceCRUD {
Statement ste;
Connection conn = MyConnection.getInstance().getConn();
@Override
public void ajouterColis(Colis c) {
    System.out.println(c.getDateExpedition());
        try {
    PreparedStatement stmt = conn.prepareStatement("INSERT INTO colis (nomExpediteur, adresseExpediteur, nomDestinataire, adresseDestinataire, poids, statut, dateExpedition) VALUES (?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
     stmt.setString(1, c.getNomExpediteur());
        stmt.setString(2, c.getAdresseExpediteur());
        stmt.setString(3, c.getNomDestinataire());
        stmt.setString(4, c.getAdresseDestinataire());
        stmt.setFloat(5, c.getPoids());
        stmt.setString(6, c.getStatut());
        stmt.setObject(7, c.getDateExpedition());
        stmt.executeUpdate();
        
        // Obtenir l'id du colis inséré
        ResultSet generatedKeys = stmt.getGeneratedKeys();
        int colisId = -1;
        if (generatedKeys.next()) {
            c.setId(generatedKeys.getInt(1));
        }
        
        // Inserer le document d'expédition associé au colis
        DocumentExpedition documentExpedition = new DocumentExpedition(colisId, c, "Non signé", null);
        stmt = conn.prepareStatement("INSERT INTO documentExpedition (colis_id, statut, dateSignature) VALUES (?,?,?)");
        stmt.setInt(1, documentExpedition.getColis().getId());
        stmt.setString(2, documentExpedition.getStatut());
        stmt.setObject(3, documentExpedition.getDateSignature());
        stmt.executeUpdate();
        
        System.out.println("Colis ajouté avec succès");
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
public void supprimerColis(int id)  {
    try {
    PreparedStatement statement1 = conn.prepareStatement("DELETE FROM documentexpedition WHERE colis_id = ?");
    statement1.setInt(1, id);
    statement1.executeUpdate();
    String sql = "DELETE FROM colis WHERE id = ?";
    PreparedStatement preparedStatement = conn.prepareStatement(sql);
    preparedStatement.setInt(1, id);
    preparedStatement.executeUpdate();
    System.out.println("Colis deleted !");
}catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}

@Override
public void modifierColis(Colis colis) {
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
public List<Colis> afficherColis() {
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

public List<Colis> filterByStatut(int e) {
    List<Colis> list = new ArrayList<>();

    try {
        String req = "Select * from colis where statut = "+e;
        Statement st = conn.createStatement();

        ResultSet RS= st.executeQuery(req);
        while(RS.next()){
        Colis c = new Colis();
        c.setId(RS.getInt(1));
        c.setNomExpediteur(RS.getString(2));
        c.setAdresseExpediteur(RS.getString(3));
        c.setNomDestinataire(RS.getString(4));
        c.setAdresseDestinataire(RS.getString(5));
        c.setPoids(RS.getFloat(6));
        c.setStatut(RS.getString(7));
        c.setDateExpedition(RS.getDate(8));
        list.add(c);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return list ; 
}












}
