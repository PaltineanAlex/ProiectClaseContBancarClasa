package ProiectClaseContBancarScoala;

public enum Moneda {
    LEU(1), EURO(2), DOLAR(3); //declarare elemente enumerare
    private int codValuta;

    Moneda(int codValuta) { //initializarea enumerarii
        this.codValuta = codValuta;
    }

    public int getCodValuta() { //metoda de accest pentru codul unui element din enumeratie
        return codValuta;
    }
}
