package fr.projetNosql.lib;

public class Bateau extends TypeBateau {

    private String idBateau;
    private int nbPlace;
    private int puissance;
    private int caution;

    public Bateau(String idBateau, int nbPlace, int puissance, int caution, String idTypeBateau, String nomTypeBateau) {
        super(idTypeBateau, nomTypeBateau);
        this.idBateau = idBateau;
        this.nbPlace = nbPlace;
        this.puissance = puissance;
        this.caution = caution;
    }

    public String getIdBateau() {
        return idBateau;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public int getPuissance() {
        return puissance;
    }

    public int getCaution() {
        return caution;
    }

    public void setNbPersonne(int nbPersonne) {
        this.nbPlace = nbPersonne;
    }

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    public void setCaution(int caution) {
        this.caution = caution;
    }

    public void setTypeBateau(TypeBateau typeBateau) {
        this.idTypeBateau = typeBateau.getIdTypeBateau();
        this.nomTypeBateau = typeBateau.getNomTypeBateau();
    }
}
