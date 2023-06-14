package fr.projetNosql.lib;

public class TypeBateau {

    String nomTypeBateau;

    String idTypeBateau;

    public TypeBateau(String idTypeBateau, String nomTypeBateau) {
        this.idTypeBateau = idTypeBateau;
        this.nomTypeBateau = nomTypeBateau;
    }

    public String getIdTypeBateau() {
        return idTypeBateau;
    }

    public String getNomTypeBateau() {
        return nomTypeBateau;
    }

}

