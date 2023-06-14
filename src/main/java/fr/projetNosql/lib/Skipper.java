package fr.projetNosql.lib;

public class Skipper {

    String id;
    String nom;
    String prenom;
    int tarif;

    public Skipper(String id, String nom, String prenom, int tarif) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tarif = tarif;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getTarif() {
        return tarif;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.nom = prenom;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }
}
