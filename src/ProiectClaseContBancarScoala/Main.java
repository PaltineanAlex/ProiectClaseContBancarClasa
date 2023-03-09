package ProiectClaseContBancarScoala;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy"); //cream un formator de
    // data pentru a afisa intr-un format cunoscut
    //instantele de tip string sunt salvate si prelucrate intr-o zona de mem numita constant pool(JVM)
    //problema de shallow copy apare atunci cand incercam sa clonam un obiect din interiorul altui obiect
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
            //System.out.println(contCurent);
            //System.out.println(depozit);

            //Depozit depozit1 = new Depozit(), depozit2=depozit;
            //System.out.println(depozit == depozit1);

            //Persoana persoana = new Persoana();
            //persoana.setCnp(3513512351346L);
            //System.out.println(depozit.getTitular().equals(persoana)); --> testare metoda equals si creare cod hash

            Persoana persoana = depozit.getTitular();
            Persoana clona = (Persoana) persoana.clone(); //se face un hsallow copy la care trb facut explicit cast pentru tipul de clasa dorit
            persoana.getAdresa().setLocalitate("Codlea"); //putem observa ca, din cauza ca clona si persoana refera aceeasi referinta a obiectului, modificarile se pot vedea
            //in ambele instante de clasa. Aceasta probleme apare pentru proprietati de tip clasa(ex. Adresa)
            System.out.println(clona);

            //depozit.prelungire(contCurent);
            depozit.lichidare(contCurent);
            System.out.println(contCurent);

        }catch(Exception e){
            System.err.println(e);
        }
    }

    public static void citireClienti(){
        try(Scanner scanner = new Scanner(System.in)){ //incercam sa accesam datele aflate in fisierul csv clienti, plus ca se face automat scanner.close();

        }catch (Exception ex){
            System.err.println(ex);
        }
    }
}