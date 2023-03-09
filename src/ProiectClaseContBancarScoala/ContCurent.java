package ProiectClaseContBancarScoala;

import java.util.Date;

public class ContCurent extends Cont{
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
}
