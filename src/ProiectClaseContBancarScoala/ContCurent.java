package ProiectClaseContBancarScoala;

import java.util.Date;
import java.util.Objects;

public class ContCurent extends Cont implements Operatiuni{
    private String iban;

    public ContCurent() {
    }

    public ContCurent(Persoana titular, Date dataDeschidere, Moneda moneda, double valoare, String sucursala, String iban) throws Exception {
        super(titular, dataDeschidere, moneda, valoare, sucursala);
        this.iban = iban;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public String toString() {
        return "ContCurent{" + iban  + "}\n" + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContCurent that = (ContCurent) o;
        return Objects.equals(iban, that.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban);
    }

    @Override
    public void depunereNumerar(double suma) {
        setValoare(suma+getValoare());
    }
}
