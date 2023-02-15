/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.model;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author hp
 */
public class DocumentExpedition {
    private int id;
    private Date dateSignature;
    private int id_colis;
    private String statut;
    
    public DocumentExpedition(int id, Date dateSignature, int id_colis, String statut) {
        this.id = id;
        this.dateSignature = Date.valueOf(LocalDate.now());
        this.id_colis = id_colis;
        this.statut = statut;
    }
    
    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateSignature() {
        return dateSignature;
    }

    public void setDateSignature(Date dateSignature) {
        this.dateSignature = dateSignature;
    }

    public int getId_colis() {
        return id_colis;
    }

    public void setId_colis(int id_colis) {
        this.id_colis = id_colis;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

public DocumentExpedition ( ){
}

@Override
public String toString() {
        return "Document d'expedition{" + "id=" + id + ", DateSignature=" +dateSignature+ ", Status=" +statut+ "Id_Colis="+id_colis+"}/n";
    }
}
