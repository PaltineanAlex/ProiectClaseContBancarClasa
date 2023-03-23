package ProiectClaseContBancarScoala;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Depozit extends Cont implements Cloneable, OperatiuniDepozite{
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

    public Depozit(int codContract) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depozit depozit = (Depozit) o;
        return codContract == depozit.codContract;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codContract);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Depozit clona = (Depozit) super.clone();
        clona.setTitular((Persoana) titular.clone());
        try{
            clona.setDataDeschidere((Date) dataDeschidere.clone());
        }catch (Exception ex){} //try catch redundant, puteam face in schimb un nou obiect de tip Date care sa contina super.clone si pe care sa ii setam data folosind clone
        clona.setImputernicit((Persoana)imputernicit.clone());
        return clona;
    }

    @Override
    public void prelungire(ContCurent contCurent) throws Exception {
        Date dataCurenta = new Date();
        //get time intoarce numarul de milisecunde trecute de la 1970 GME
        long nrZile = TimeUnit.MILLISECONDS.toDays(dataCurenta.getTime() - dataDeschidere.getTime()); //timpul se calculeaza automat in milisecunde
        //pentru a putea prelucra cu alte UM, trebuie sa convertim
        if(nrZile<tipDepozit.getDurataDepozit()){
            throw new Exception("NU a expirat termenul contractat.");
        }
        double dobanda = (valoare * tipDepozit.getRataDobanzii()*nrZile)/(100*365);
        contCurent.depunereNumerar(dobanda);
    }

    @Override
    public void lichidare(ContCurent contCurent) {
        Date dataCurenta = new Date();
        long nrZile = TimeUnit.MILLISECONDS.toDays(dataCurenta.getTime() - dataDeschidere.getTime());
        if(nrZile<tipDepozit.getDurataDepozit()){
            contCurent.depunereNumerar(valoare);
        }else{
            double dobanda = (valoare * tipDepozit.getRataDobanzii()*nrZile)/(100*365);
            contCurent.depunereNumerar(valoare+dobanda);
        }
        esteLichdat=true;
    }
}
