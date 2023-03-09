package ProiectClaseContBancarScoala;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy"); //cream un formator de
    // data pentru a afisa intr-un format cunoscut
    public static void main(String[] args) {

        try{
            Depozit depozit = new Depozit(
                    new Persoana("Pop Adriana", 3513512351346L,
                            new Adresa("Brasov", "Brasov", "1Dec 1918", "10", 34534)),
                    df.parse("12.01.2023"), Moneda.LEU, 10000, "BRD Brasov",
                    TipDepozit.O_LUNA, 1101);

            ContCurent contCurent = new ContCurent();
            contCurent.setDataDeschidere(new Date(depozit.dataDeschidere.getTime())); //preluam data de deschidere de la depozitul creat, instantiand un nou obiect de tip date
            contCurent.setTitular(depozit.getTitular());
            contCurent.setMoneda(Moneda.LEU);
            contCurent.setSucursala("BRD SMB");
            contCurent.setIban("RO00CN887232353423");
            System.out.println(contCurent);
            System.out.println(depozit);
        }catch(Exception e){
            System.err.println(e);
        }
    }
}