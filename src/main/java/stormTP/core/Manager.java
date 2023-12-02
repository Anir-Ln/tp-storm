package stormTP.core;

import org.apache.storm.shade.org.json.simple.JSONArray;
import org.apache.storm.shade.org.json.simple.JSONObject;
import org.apache.storm.shade.org.json.simple.parser.JSONParser;
import org.apache.storm.shade.org.json.simple.parser.ParseException;

public class Manager {
    public static final int MY_TURTLE_NUMBER = 5;
    public static final String CONST = "C'est mieux!";
    public static final String PROG = "Statu quo";
    public static final String REGR = "ça sera sûrement mieux plus tard!";

    String nomsBinome = "Lahyane-Dennoun";
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
     * @param input = "[{\"id\":1,\"top\":420,\"nbDevant\":0,\"cellule\":190,\"tour\":2}]"
     * @return
     */
    public Runner filter(String input) {
        Runner tortoise = null;
        JSONParser parser = new JSONParser();
        JSONObject myTurtle = null;
        try {
            JSONObject obj = (JSONObject) parser.parse(input);
            JSONArray runners = (JSONArray) obj.get("item");
            for (Object runner: runners) {
                JSONObject turtle = (JSONObject) runner;
                if (Integer.parseInt(turtle.get("id").toString()) == MY_TURTLE_NUMBER) {
                     myTurtle = turtle;
                }
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
//        JsonReader reader = Json.createReader(new StringReader(input));
//        JsonObject jsonObject = reader.readObject();
//        JsonArray tortoises = jsonObject.getJsonArray("item");
//        Optional<JsonValue> myTurtle = tortoises.stream().filter(
//                jsonValue -> ((JsonObject) jsonValue).getInt("id") == MY_TURTLE_NUMBER
//        ).findFirst();
        tortoise = new Runner(
                MY_TURTLE_NUMBER,
                this.nomsBinome,
                Integer.parseInt(myTurtle.get("nbDevant").toString()),
                Integer.parseInt(myTurtle.get("tour").toString()),
                Integer.parseInt(myTurtle.get("cellule").toString()),
                Integer.parseInt(myTurtle.get("top").toString())
        );
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
