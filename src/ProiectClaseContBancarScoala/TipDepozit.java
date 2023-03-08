package ProiectClaseContBancarScoala;

public enum TipDepozit {
    O_LUNA(30,3), DOUA_LUNI(60,3), TREI_LUNI(90,5), UN_AN(365,10);

    private int durataDepozit;
    private double rataDobanzii;

    TipDepozit(int durataDepozit, double rataDobanzii) {
        this.durataDepozit = durataDepozit;
        this.rataDobanzii = rataDobanzii;
    }

    public int getDurataDepozit() {
        return durataDepozit;
    }

    public double getRataDobanzii() {
        return rataDobanzii;
    }

    public void setRataDobanzii(double rataDobanzii) {
        this.rataDobanzii = rataDobanzii;
    }
}
