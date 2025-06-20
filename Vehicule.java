package com.example.garage.models;

public class Vehicule {
    private int id;
    private String marque;
    private String modele;
    private int annee;
    private String immatriculation;
    private int clientId;

    public Vehicule(int id, String marque, String modele, int annee, String immatriculation, int clientId) {
        this.id = id;
        this.marque = marque;
        this.modele = modele;
        this.annee = annee;
        this.immatriculation = immatriculation;
        this.clientId = clientId;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getMarque() { return marque; }
    public String getModele() { return modele; }
    public int getAnnee() { return annee; }
    public String getImmatriculation() { return immatriculation; }
    public int getClientId() { return clientId; }

    public void setId(int id) { this.id = id; }
    public void setMarque(String marque) { this.marque = marque; }
    public void setModele(String modele) { this.modele = modele; }
    public void setAnnee(int annee) { this.annee = annee; }
    public void setImmatriculation(String immatriculation) { this.immatriculation = immatriculation; }
    public void setClientId(int clientId) { this.clientId = clientId; }
}
