package fr.projetNosql.lib;

public class Promotion {

    String id;
    String code;
    int montant;

    public Promotion(String id, String code, int montant) {
        this.id = id;
        this.code = code;
        this.montant = montant;
    }

        public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public int getMontant() {
        return montant;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
}
