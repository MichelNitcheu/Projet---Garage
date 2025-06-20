package com.example.garage.models;

import java.time.LocalDateTime;

public class RendezVous {
    private int id;
    private LocalDateTime dateHeure;
    private String statut;
    private Vehicule vehicule;
    private Panne panne;

    public RendezVous(int id, LocalDateTime dateHeure, String statut, Vehicule vehicule, Panne panne) {
        this.id = id;
        this.dateHeure = dateHeure;
        this.statut = statut;
        this.vehicule = vehicule;
        this.panne = panne;
    }

    // Getters and Setters
    public int getId() { return id; }
    public LocalDateTime getDateHeure() { return dateHeure; }
    public String getStatut() { return statut; }
    public Vehicule getVehicule() { return vehicule; }
    public Panne getPanne() { return panne; }

    public void setId(int id) { this.id = id; }
    public void setDateHeure(LocalDateTime dateHeure) { this.dateHeure = dateHeure; }
    public void setStatut(String statut) { this.statut = statut; }
    public void setVehicule(Vehicule vehicule) { this.vehicule = vehicule; }
    public void setPanne(Panne panne) { this.panne = panne; }
}