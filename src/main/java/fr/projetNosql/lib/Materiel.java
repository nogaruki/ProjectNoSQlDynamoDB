package fr.projetNosql.lib;

public class Materiel {

    String id;
    String nom;
    int prix;
    int caution;
    public Materiel(String id, String nom, int prix, int caution) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.caution = caution;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }


    public int getPrix() {
        return prix;
    }

    public int getCaution() {
        return caution;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setCaution(int caution) {
        this.caution = caution;
    }
}
