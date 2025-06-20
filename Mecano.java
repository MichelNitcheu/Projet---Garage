package com.example.garage.models;

public class Mecano {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String specialite;

    public Mecano(int id, String nom, String prenom, String email, String telephone, String specialite) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.specialite = specialite;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getTelephone() { return telephone; }
    public String getSpecialite() { return specialite; }

    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setEmail(String email) { this.email = email; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }
}
