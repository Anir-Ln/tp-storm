package stormTP.core;

import javax.json.*;
import java.io.StringReader;
import java.util.Optional;
import java.util.stream.Stream;

public class Manager {
    public static final int MY_TURTLE_NUMBER = 5;
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
        JsonReader reader = Json.createReader(new StringReader(input));
        JsonObject jsonObject = reader.readObject();
        JsonArray tortoises = jsonObject.getJsonArray("tortoises");
        Optional<JsonValue> myTurtle = tortoises.stream().filter(
                jsonValue -> ((JsonObject) jsonValue).getInt("id") == MY_TURTLE_NUMBER
        ).findFirst();
        if (myTurtle.isEmpty()) return null;
        JsonObject myTurtleValue = (JsonObject) myTurtle.get();
        tortoise = new Runner(
                MY_TURTLE_NUMBER,
                this.nomsBinome,
                myTurtleValue.getInt("nbDevant"),
                myTurtleValue.getInt("nbDerriere"),
                myTurtleValue.getInt("total"),
                myTurtleValue.getInt("position"),
                myTurtleValue.getInt("top")
        );
        System.out.println(tortoise);
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
