package com.example.garage.models;

public class Panne {
    private int id;
    private String nom;
    private double prix;

    public Panne(int id, String nom, double prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public double getPrix() { return prix; }

    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrix(double prix) { this.prix = prix; }
}