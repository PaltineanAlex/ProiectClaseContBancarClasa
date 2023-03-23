package ProiectClaseContBancarScoala;

import java.io.Serializable;
import java.util.Date;

public abstract class Cont implements Serializable, Comparable<Cont> {//implementam Serializable pentru a putea scrie datele din obiect intr-un fisier(inafara de cele statice sau tranzient
    protected Persoana titular;
    protected Date dataDeschidere;
    protected Moneda moneda;
    protected double valoare;
    protected String sucursala;
    //protected int campNou;//daca schimbam structura unei clase fara sa serializez, la deserializare, acesta nu va avea metadatele necesare si v-a arunca o exceptie

    public static final long serialVersionUID = 1L;//nu se genereazu un cod pentru a determina versiunea, ci noi o setam astfel incat sa putem rula ser/deser
    //fara a interfere versiunile de serializare

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

    @Override
    public int compareTo(Cont o) {
        //-1 mai mic, 0 egal, 1 mai mare
        return dataDeschidere.compareTo(o.dataDeschidere);//compareTo deja implementat de clasa Date
    }
}
