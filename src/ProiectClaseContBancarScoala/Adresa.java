package ProiectClaseContBancarScoala;

public class Adresa implements Cloneable{
    private String localitate, judet, strada, numar;
    private int cod;

    public Adresa() {
    }

    public Adresa(String localitate, String judet, String strada, String numar, int cod) {
        this.localitate = localitate;
        this.judet = judet;
        this.strada = strada;
        this.numar = numar;
        this.cod = cod;
    }

    public String getLocalitate() {
        return localitate;
    }

    public void setLocalitate(String localitate) {
        this.localitate = localitate;
    }

    public String getJudet() {
        return judet;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public String getNumar() {
        return numar;
    }

    public void setNumar(String numar) {
        this.numar = numar;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    @Override
    public String toString() { //daca nu suprascriem metoda toString, aceasta va returna hashCodeul
        return "{" + localitate + ", " + judet + ", " + strada + ", " + numar + ", " + cod + "}";
    }

    @Override
    public Object clone() throws CloneNotSupportedException { //cream aceasta metoda si aici pentru a nu da de problema de shallow copy de la persoana
        return super.clone();
    }
}
