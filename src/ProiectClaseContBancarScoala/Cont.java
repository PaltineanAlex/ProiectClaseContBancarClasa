package ProiectClaseContBancarScoala;

import java.io.Serializable;
import java.util.Date;

public abstract class Cont implements Serializable {//implementam Serializable pentru a putea scrie datele din obiect intr-un fisier(inafara de cele statice sau tranzient
    protected Persoana titular;
    protected Date dataDeschidere;
    protected Moneda moneda;
    protected double valoare;
    protected String sucursala;

    public Cont() {
    }

    public Cont(Persoana titular, Date dataDeschidere, Moneda moneda, double valoare, String sucursala) throws Exception{
        Date dataCurenta = new Date();
        if(dataDeschidere.after(dataCurenta)){
            throw new Exception("Data deschidere incorecta!");
        }
        this.titular = titular;
        this.dataDeschidere = dataDeschidere;
        this.moneda = moneda;
        this.valoare = valoare;
        this.sucursala = sucursala;
    }

    public Persoana getTitular() {
        return titular;
    }

    public void setTitular(Persoana titular) {
        this.titular = titular;
    }

    public Date getDataDeschidere() {
        return dataDeschidere;
    }

    public void setDataDeschidere(Date dataDeschidere) throws Exception{
        Date dataCurenta = new Date();
        if(dataDeschidere.after(dataCurenta)){
            throw new Exception("Data deschidere incorecta!");
        }
        this.dataDeschidere = dataDeschidere;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public double getValoare() {
        return valoare;
    }

    public void setValoare(double valoare) {
        this.valoare = valoare;
    }

    public String getSucursala() {
        return sucursala;
    }

    public void setSucursala(String sucursala) {
        this.sucursala = sucursala;
    }

    @Override
    public String toString() {
        return "Cont {" +
                 titular + ", " + (dataDeschidere == null ? "" : Main.df.format(dataDeschidere)) +
                ", " + moneda + ", " + valoare + ", " + sucursala + '}';
    }
}
