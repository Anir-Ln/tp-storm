package stormTP.core;

public class Manager {

    public static final String CONST = "C'est mieux!";
    public static final String PROG = "Statu quo";
    public static final String REGR = "ça sera sûrement mieux plus tard!";

    String nomsBinome = "";
    long dossard = -1;

    public Manager(long dossard, String nomsBinome) {
        this.nomsBinome = nomsBinome;
        this.dossard = dossard;
    }

    public static String getPodium(String input) {
        return "PODIUM";
    }


    /**
     * Permet de filtrer les informations concernant votre tortue
     *
     * @param input
     * @return
     */
    public Runner filter(String input) {
        Runner tortoise = null;

        //@TODO
        return tortoise;
    }

    public Manager computeRank(int i, int i1, String nomsBinome, int i2, int i3, int i4) {
        return this;
    }

    public String getJSON_V2() {
        return "JSON_V2";
    }

    public int computePoints(String number, int i) {
        return 0;
    }

    public double computeSpeed(int i, int i1, int i2, int i3) {
        return 0;
    }

    public String giveRankEvolution(int i, int i1) {
        return "RANK_EVOLUTION";
    }

    public int giveAverageRank(String[] input) {
        return 1;
    }


    //@TODO


}
