package com.example.garage.models;

import java.time.LocalDateTime;

public class Reparation {
    private int id;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String statut;
    private String description;
    private double cout;
    private RendezVous rendezVous;
    private Mecano mecano;

    public Reparation(int id, LocalDateTime dateDebut, LocalDateTime dateFin, String statut,
                      String description, double cout, RendezVous rendezVous, Mecano mecano) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
        this.description = description;
        this.cout = cout;
        this.rendezVous = rendezVous;
        this.mecano = mecano;
    }

    // Getters and Setters
    public int getId() { return id; }
    public LocalDateTime getDateDebut() { return dateDebut; }
    public LocalDateTime getDateFin() { return dateFin; }
    public String getStatut() { return statut; }
    public String getDescription() { return description; }
    public double getCout() { return cout; }
    public RendezVous getRendezVous() { return rendezVous; }
    public Mecano getMecano() { return mecano; }

    public void setId(int id) { this.id = id; }
    public void setDateDebut(LocalDateTime dateDebut) { this.dateDebut = dateDebut; }
    public void setDateFin(LocalDateTime dateFin) { this.dateFin = dateFin; }
    public void setStatut(String statut) { this.statut = statut; }
    public void setDescription(String description) { this.description = description; }
    public void setCout(double cout) { this.cout = cout; }
    public void setRendezVous(RendezVous rendezVous) { this.rendezVous = rendezVous; }
    public void setMecano(Mecano mecano) { this.mecano = mecano; }
}