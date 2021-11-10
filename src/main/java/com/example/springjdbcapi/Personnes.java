package com.example.springjdbcapi;

public class Personnes {

    private int id;
    private String prenom;
    private String nom;

    public Personnes(int id, String prenom, String nom) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return String.format(
                "Personnes[id=%d, prenom='%s', nom='%s']",
                id, prenom, nom);
    }
}
