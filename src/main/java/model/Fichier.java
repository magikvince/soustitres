package model;

import java.util.ArrayList;

public class Fichier
{
    private int idFichier;
    private String nom;
    private String description;
    private ArrayList<LigneFichier> lignes;

    public Fichier(String nom, String description)
    {
        this.nom = nom;
        this.description = description;
        this.lignes = new ArrayList<LigneFichier>();
    }

    public int getIdFichier() {
        return idFichier;
    }

    public void setIdFichier(int idFichier) {
        this.idFichier = idFichier;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
