package ProiectClaseContBancarScoala;

public class Persoana {
    private String nume;
    private long cnp;
    private Adresa adresa;

    public Persoana() {
    }

    public Persoana(String nume, long cnp, Adresa adresa) {
        this.nume = nume;
        this.cnp = cnp;
        this.adresa = adresa;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public long getCnp() {
        return cnp;
    }

    public void setCnp(long cnp) {
        this.cnp = cnp;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "{" +
                 nume + ", " +
                 cnp +
                ", " + adresa +
                '}';
    }
}
