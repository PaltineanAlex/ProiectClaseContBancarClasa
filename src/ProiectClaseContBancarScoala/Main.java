package ProiectClaseContBancarScoala;

import java.text.SimpleDateFormat;

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
            System.out.println(depozit);
        }catch(Exception e){
            System.err.println(e);
        }
    }
}