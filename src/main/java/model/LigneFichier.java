package model;

import java.time.LocalTime;

public class LigneFichier
{
    private int idLigne;
    private int numLigne;
    private String debut;
    private String fin;
    private String time;
    private String texte;


    public LigneFichier(int numLigne, String time, String texte)
    {
        this.numLigne = numLigne;
        this.time = time;
        this.texte = texte;
    }

    public int getIdLigne() {
        return idLigne;
    }

    public void setIdLigne(int idLigne) {
        this.idLigne = idLigne;
    }

    public int getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(int numLigne) {
        this.numLigne = numLigne;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }
}
