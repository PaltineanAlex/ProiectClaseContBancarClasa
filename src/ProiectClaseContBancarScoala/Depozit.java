package ProiectClaseContBancarScoala;

import java.util.Date;

public class Depozit extends Cont{
    private Persoana imputernicit;
    private TipDepozit tipDepozit;
    private int codContract;
    private boolean esteLichdat = true; //valoarea neutra a unui boolean este false

    public Depozit() {
    }

    public Depozit(Persoana titular, Date dataDeschidere, Moneda moneda, double valoare, String sucursala, Persoana imputernicit, TipDepozit tipDepozit, int codContract) throws Exception {
        super(titular, dataDeschidere, moneda, valoare, sucursala);
        this.imputernicit = imputernicit;
        this.tipDepozit = tipDepozit;
        this.codContract = codContract;
    }

    public Depozit(Persoana titular, Date dataDeschidere, Moneda moneda, double valoare, String sucursala, TipDepozit tipDepozit, int codContract) throws Exception {
        super(titular, dataDeschidere, moneda, valoare, sucursala);
        this.tipDepozit = tipDepozit;
        this.codContract = codContract;
    }

    public Persoana getImputernicit() {
        return imputernicit;
    }

    public void setImputernicit(Persoana imputernicit) {
        this.imputernicit = imputernicit;
    }

    public TipDepozit getTipDepozit() {
        return tipDepozit;
    }

    public void setTipDepozit(TipDepozit tipDepozit) {
        this.tipDepozit = tipDepozit;
    }

    public int getCodContract() {
        return codContract;
    }

    public void setCodContract(int codContract) {
        this.codContract = codContract;
    }

    @Override
    public String toString() {
        return super.toString() + "\nDepozit{" +
                imputernicit + ", " + tipDepozit + ", " + codContract + "}" ;
    }
}
