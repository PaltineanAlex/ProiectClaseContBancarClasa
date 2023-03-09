package ProiectClaseContBancarScoala;

import java.util.Objects;

public class Persoana implements Cloneable{
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

    @Override
    public boolean equals(Object o) { //metoda equals care compara dous obiecte
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false; //getClass intoarce tipul obiectul curent la rulare din acest Obiect
        //prin cea dea 2-a inecuatie se verifica daaca cele 2 obiecte au acelasi tip
        Persoana persoana = (Persoana) o;
        return cnp == persoana.cnp;
    }

    @Override
    public int hashCode() { //creeaza hash code pentru fiecare obiect dupa CNP (cod teoretic unic dupa anumiti parametrii)
        return Objects.hash(cnp);
    }

    @Override
    public Object clone() throws CloneNotSupportedException { //pentru a putea folosi aceasta metoda suprascrisa, trebuie sa implementam interfata Clonable
        Persoana clona = (Persoana) super.clone();
        //intoarce ceea ce furnizeaza clone din superclasa
        clona.setAdresa((Adresa) adresa.clone()); //folosim aceasta initializare la clonare pentru a nu mai avea problema de shallow copy
        return clona;
    }
}
