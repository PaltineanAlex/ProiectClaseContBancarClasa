package ProiectClaseContBancarScoala;
//Serializare: metoda de a transforma continutul unui obiect intr-un sir de octeti
//Fluxuri utilizate: ObjectOutputStream si FileOutputStream
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy"); //cream un formator de
    // data pentru a afisa intr-un format cunoscut
    //instantele de tip string sunt salvate si prelucrate intr-o zona de mem numita constant pool(JVM)
    //problema de shallow copy apare atunci cand incercam sa clonam un obiect din interiorul altui obiect
    private Persoana[] clienti;
    private List<Persoana> listaClienti = new ArrayList<>(); //List - interfata, ArrayList - clasa
    private List<Depozit> listaDepozite = new ArrayList<>();

    public List<Persoana> getListaClienti() {
        return listaClienti;
    }

    public List<Depozit> getListaDepozite() {
        return listaDepozite;
    }

    public static void main(String[] args) {

        try{
//            Depozit depozit = new Depozit(
//                    new Persoana("Pop Adriana", 3513512351346L,
//                            new Adresa("Brasov", "Brasov", "1Dec 1918", "10", 34534)),
//                    df.parse("12.01.2023"), Moneda.LEU, 10000, "BRD Brasov",
//                    TipDepozit.O_LUNA, 1101);
//
//            ContCurent contCurent = new ContCurent();
//            contCurent.setDataDeschidere(new Date(depozit.dataDeschidere.getTime())); //preluam data de deschidere de la depozitul creat, instantiand un nou obiect de tip date
//            contCurent.setTitular(depozit.getTitular());
//            contCurent.setMoneda(Moneda.LEU);
//            contCurent.setSucursala("BRD SMB");
//            contCurent.setIban("RO00CN887232353423");
//            //System.out.println(contCurent);
//            //System.out.println(depozit);
//
//            //Depozit depozit1 = new Depozit(), depozit2=depozit;
//            //System.out.println(depozit == depozit1);
//
//            //Persoana persoana = new Persoana();
//            //persoana.setCnp(3513512351346L);
//            //System.out.println(depozit.getTitular().equals(persoana)); --> testare metoda equals si creare cod hash
//
//            Persoana persoana = depozit.getTitular();
//            Persoana clona = (Persoana) persoana.clone(); //se face un hsallow copy la care trb facut explicit cast pentru tipul de clasa dorit
//            persoana.getAdresa().setLocalitate("Codlea"); //putem observa ca, din cauza ca clona si persoana refera aceeasi referinta a obiectului, modificarile se pot vedea
//            //in ambele instante de clasa. Aceasta probleme apare pentru proprietati de tip clasa(ex. Adresa)
//            System.out.println(clona);
//
//            //depozit.prelungire(contCurent);
//            depozit.lichidare(contCurent);
//            System.out.println(contCurent);

            Main app = new Main();
            app.citireClienti(System.in);
            //app.printClientList();
            for(Persoana persoana : app.clienti){ //adaugam membrii din vectorul clienti la Lista
                app.listaClienti.add(persoana);
            }

            //app.printList("Lista_Clienti: ", app.listaClienti);
            app.citireDepozite();
            app.printList("Depozite: ", app.listaDepozite);

            app.depositSave("raportDepozite.csv");
            System.out.println("\n\n\n");
            //Serializare
            //app.salvare();

            //Deserializare
            app.restaurare();
            app.printList("Lista depozite restaurate: ", app.listaDepozite);

            //Cautare
            Depozit depozit = new Depozit(111);
            List<Depozit> lista = app.listaDepozite;
            //metoda contains cauta obiectul dorit in lista prin egalitate (equals si hashCode)
            System.out.println("\n\n\n" + (lista.contains(depozit) ? "Exista depozitul cu codul " : "Nu exista depozitul cu codul ") + depozit.getCodContract() + ".");

            //Selectie
            int k = lista.indexOf(depozit);
            if(k == -1){
                System.out.println("Nu exista depozitul cu codul " + depozit.getCodContract());
            }else{
                System.out.println("Depozitul cu codul " + depozit.getCodContract() + ": ");
                System.out.println(lista.get(k));
            }

            //Sortare dupa data deschidere(Comparable)
            Collections.sort(lista);//putem folosi aceasta metoda deoarece am implementat Comparable in clasa Cont
            app.printList("\n\nLista depozite sortate dupa data deschidere: ", lista);

            //Sortare dupa numele titularului(Comparator)
            //Interfata funcitonala este o interfata cu o singura metoda abstracta(cum merge si cu functiile lambda)
            //Metodele default sunt metode implementate la nivel de interfata
            Collections.sort(lista, new Comparator<Depozit>() { //putem observa un exemplu de clasa anonima
                //sortarea se face pe lista curenta, nu pe o copie noua
                @Override
                public int compare(Depozit o1, Depozit o2) {
                    return o1.getTitular().getNume().compareTo(o2.getTitular().getNume());
                }
            });

            app.printList("\n\nLista depozite sortata dupa titular: ", lista);

            //Sortare dupa sucursala prin List
            lista.sort(new Comparator<Depozit>() {
                @Override
                public int compare(Depozit o1, Depozit o2) {
                    return o1.getSucursala().compareTo(o2.getSucursala());
                }
            });

            app.printList("\n\nLista depozite sortata dupa sucursala: ", lista);

            //Cautare binara dupa cod contract
            Comparator<Depozit> comparatorCodContract = new Comparator<Depozit>() {
                @Override
                public int compare(Depozit o1, Depozit o2) {
                    return Integer.compare(o1.getCodContract(), o2.getCodContract());
                }
            };
            Collections.sort(lista, comparatorCodContract);
            k = Collections.binarySearch(lista, depozit, comparatorCodContract);//intoarce indexul elementului cautat(in cazul nostru obiectul depozit)
            //daca nu gaseste elemntul cautat, va returna -1, iar in rest, pt val >= 0, reprezinta poz din lista
            System.out.println("\n\n");
            if(k >= 0){
                System.out.println("Depozitul cu codul " + depozit.getCodContract());
                System.out.println(lista.get(k));
            }else{
                System.out.println("Nu exista depozitul cu codul " + depozit.getCodContract());
                System.out.println("Pozitie de inserare " + (-k-1));
            }



        }catch(Exception e){
            System.err.println(e);
        }
    }

    public void printClientList(){
        System.out.println("Clienti:");
        for(Persoana client : clienti){
            System.out.println(client);
        }
    }

    public void printList(String message, List listaClienti){
        System.out.println(message);
        for(Object element : listaClienti){
            System.out.println(element);
        }
    }

    public void citireClienti(String fisier){//citire clienti din fisier csv dand numele fisierului direct, fara redirectare inputului din configuratie
        try(FileInputStream in = new FileInputStream(fisier)){
            citireClienti(in);
            for(Persoana persoana : clienti){
                listaClienti.add(persoana);
            }
        }catch(Exception ex){
            System.err.println(ex);
        }
    }

    public void citireClienti(InputStream in){// citire din fisier csv atunci cand avem redirectat standard inputul in configuration file
        try(Scanner scanner = new Scanner(in)){ //incercam sa accesam datele aflate in fisierul csv clienti, plus ca se face automat scanner.close();
            clienti = new Persoana[0]; //scapam de problema initializerii cu NULL implicit si se aloca o zona in heap(scapan de NullPointerException)
            while(scanner.hasNextLine()){
                String[] t = scanner.nextLine().split(","); //preluam o linie pe care o spargen in main multe stringuri separate printr-un caracter regex ","
                Persoana client = new Persoana();
                client.setCnp(Long.parseLong(t[0].trim())); //elimina caractere redundante precum spatiul
                client.setNume(t[1].trim());
                Adresa adresa = new Adresa();
                adresa.setLocalitate(t[2].trim());
                adresa.setJudet(t[3].trim());
                adresa.setStrada(t[4].trim());
                adresa.setNumar(t[5].trim());
                adresa.setCod(Integer.parseInt(t[6].trim()));
                client.setAdresa(adresa);
                clienti = Arrays.copyOf(clienti, clienti.length+1); //cream un nou vector, copiem elementele cu dim n+1, iar ultimul elem din vector va fi clientul nou
                clienti[clienti.length-1] = client;
            }
        }catch (Exception ex){
            System.err.println(ex);
        }
    }

    public void citireDepozite(){//citire din fisiere text folosind un buffer
        try(BufferedReader in = new BufferedReader(new FileReader("depozite.csv"))){
            String row;
            while((row = in.readLine()) != null){ //cat timp in buffer nu avem null
                String[] t = row.split(",");
                Persoana persoana = new Persoana(Long.parseLong(t[0].trim())); //instantiere obiect persoana folosind constructor cu parametru CNP
                int k = listaClienti.indexOf(persoana); //cauta persoana facand comparatii folosind equals
                if(k!=-1){
                    persoana = listaClienti.get(k);
                }else{
                    throw new Exception("CNP Titular eronat!");
                }
                Depozit depozit = new Depozit();
                depozit.setTitular(persoana);
                depozit.setDataDeschidere(df.parse(t[1].trim()));
                depozit.setMoneda(Moneda.valueOf(t[2].trim().toUpperCase()));
                depozit.setValoare(Double.parseDouble(t[3].trim()));
                depozit.setSucursala(t[4].trim());
                if(!t[5].trim().isEmpty()){ //daca pe campul imputernicitului nu este null atunci il adaugam
                    persoana = new Persoana(Long.parseLong(t[5].trim()));
                    k = listaClienti.indexOf(persoana);
                    if(k!=-1){
                        persoana = listaClienti.get(k);
                    }else{
                        throw new Exception("CNP Imputernicit eronat!");
                    }
                    depozit.setImputernicit(persoana);
                }
                depozit.setTipDepozit(TipDepozit.valueOf(t[6].trim().toUpperCase()));
                depozit.setCodContract(Integer.parseInt(t[7].trim()));
                listaDepozite.add(depozit);
            }
        }catch (Exception ex){
            System.err.println(ex);
        }
    }

    public void depositSave(String fisier){//salvare continutul unei liste intr-un fisier text
        try(PrintWriter out = new PrintWriter(fisier)){ //PrintWriter - flux de output catre un fisier text
            out.println("Nume,Cod Contract,Valoare");
            for(Depozit depozit : listaDepozite){
                out.println(depozit.getTitular().getNume() + ", " + depozit.getCodContract() + ", " + depozit.getValoare());
            }
        }catch (Exception ex){
            System.err.println(ex);
        }
    }

    public void salvare(){ //aici putem observa conceptul de serializare(metadate clasa + informatii propriu-zise obiecte pentru scriere in fisiere binare)
        //acesta se realizaeaza daca clasa si toate clasele componenete ale acesteia implementeaza interfata de tip flag Serializable
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("depozite.dat"))){ //ObjectOutputStream: Primitive types & objects -> binary files
            for(Depozit depozit : listaDepozite){
                out.writeObject(depozit);
            }
        }catch (Exception ex){
            System.err.println(ex);
        }
    }

    public void restaurare(){
        listaDepozite.clear();//metoda clear sterge continutul listei
        try(FileInputStream inputStream = new FileInputStream("depozite.dat");
        ObjectInputStream in = new ObjectInputStream(inputStream)){
            while(inputStream.available() != 0){//verificam daca mai sunt obiecte ramase de citit
                listaDepozite.add((Depozit) in.readObject());//adaugam elementele din fisierul .dat in lista
            }
        }catch (Exception ex){
            System.err.println(ex);
        }
    }
}