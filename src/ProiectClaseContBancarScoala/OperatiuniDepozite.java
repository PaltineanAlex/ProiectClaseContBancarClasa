package ProiectClaseContBancarScoala;

public interface OperatiuniDepozite {
    void prelungire(ContCurent contCurent) throws Exception;
    void lichidare(ContCurent contCurent);
}
