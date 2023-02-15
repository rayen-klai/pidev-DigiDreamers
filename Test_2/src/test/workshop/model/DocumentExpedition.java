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
    private Colis colis;
    private String statut;

    public DocumentExpedition() {}

    public DocumentExpedition(int id, Colis colis, String statut, Date dateSignature) {
        this.id=id;
        this.colis = colis;
        this.statut = statut;
        this.dateSignature=Date.valueOf(LocalDate.now());
    }
        public DocumentExpedition(Colis colis, String statut, Date dateSignature) {
        this.colis = colis;
        this.statut = statut;
        this.dateSignature=Date.valueOf(LocalDate.now());
    }


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

    public Colis getColis() {
        return colis;
    }

    public void setColis(Colis colis) {
        this.colis = colis;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
        if (statut.equals("Signé")) {
            colis.setStatut("Arrivé");
        }
    }
@Override
public String toString() {
        return "Documents{" + "id=" + id + ", dateSignature=" + dateSignature + ", id_colis" + colis+ "Status ="+statut+"}/n";
    }
}
    

