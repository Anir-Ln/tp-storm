package stormTP.core;

import org.apache.storm.shade.org.json.simple.JSONArray;
import org.apache.storm.shade.org.json.simple.JSONObject;
import org.apache.storm.shade.org.json.simple.parser.JSONParser;
import org.apache.storm.shade.org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Manager {
    public static final int MY_TURTLE_NUMBER = 5;
    public static final String CONST = "C'est mieux!";
    public static final String PROG = "Statu quo";
    public static final String REGR = "ça sera sûrement mieux plus tard!";

    static final Logger logger = Logger.getLogger(Manager.class.getName());

    String nomsBinome = "Lahyane-Dennoun";
    long dossard = -1;

    public Manager(long dossard, String nomsBinome) {
        this.nomsBinome = nomsBinome;
        this.dossard = dossard;
    }

    public static String getPodium(String input) {
        return "PODIUM";
    }

    @SuppressWarnings("unchecked")
    public List<Runner> jsonToList(String input) {
        JSONParser parser = new JSONParser();
        JSONArray jsonRunners;
        try {
            JSONObject obj = (JSONObject) parser.parse(input);
            jsonRunners = (JSONArray) obj.get("item");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return (List<Runner>) jsonRunners.stream().map(
                        runner -> new Runner(
                                MY_TURTLE_NUMBER,
                                this.nomsBinome,
                                Integer.parseInt(((JSONObject) runner).get("nbDevant").toString()),
                                Integer.parseInt(((JSONObject) runner).get("tour").toString()),
                                Integer.parseInt(((JSONObject) runner).get("cellule").toString()),
                                Integer.parseInt(((JSONObject) runner).get("top").toString())
                        )
        ).collect(Collectors.toList());
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
        int numberParticipants = 0;
        try {
            JSONObject obj = (JSONObject) parser.parse(input);
            logger.info("parsed json : " + obj.toJSONString());
            JSONArray runners = (JSONArray) obj.get("item");
            for (Object runner : runners) {
                try {
                    JSONObject turtle = (JSONObject) parser.parse(runner.toString());
                    if (Integer.parseInt(turtle.get("id").toString()) == MY_TURTLE_NUMBER) {
                        myTurtle = turtle;
                    }
                    numberParticipants++;
                    logger.info("Json well parsed: " + runner);
                } catch (Exception ex) {
                    logger.warning("Json turtle not parsed: " + runner.toString());
                    logger.warning("Message: " + ex.getMessage());
                }
            }
        } catch (ParseException e) {
            logger.warning(e.getMessage());
        }
        if (myTurtle == null) {
            logger.warning("Whole json not parsed");
            return null;
        }
        tortoise = new Runner(
                MY_TURTLE_NUMBER,
                this.nomsBinome,
                Integer.parseInt(myTurtle.get("nbDevant").toString()),
                Integer.parseInt(myTurtle.get("tour").toString()),
                Integer.parseInt(myTurtle.get("cellule").toString()),
                Integer.parseInt(myTurtle.get("top").toString())
        );
        tortoise.numberParticipants = numberParticipants;
        return tortoise;
    }

    private int computeDistance(Runner runner) {
        return runner.getTour() * 254 + runner.getCellule();
    }

    public String computeRank(int nbDevant, HashMap<Integer, Boolean> map) {
        String rang = String.valueOf(nbDevant + 1);
        if (map.containsKey(nbDevant + 1)) rang += "ex";
        else map.put(nbDevant + 1, true);
        return rang;
    }

    public void computeRankWithDistance(int id, List<Runner> runners) {
        runners.sort((o1, o2) -> {
            int diff = computeDistance(o1) - computeDistance(o2);
            if (diff > 0) return -1;
            else if (diff < 0) return 1;
            return 0;
        });
        int rang = 1;
        runners.get(0).setRang(String.valueOf(rang));
        for (int i = 1; i < runners.size(); i++) {
            if (computeDistance(runners.get(i)) == computeDistance(runners.get(i - 1))) {
                runners.get(i - 1).setRang(rang + "ex");
                runners.get(i).setRang(rang + "ex");
                continue;
            }
            rang++;
            runners.get(i).setRang(String.valueOf(rang));
        }
    }

    public String getJSON_V2() {
        return "JSON_V2";
    }

    /**
     * computes only bonus, it should be added to the current score
     * @param runner
     * @param numberParticipant
     * @return
     */
    public int computePoints(int top, String rank, int numberParticipant) {
        if (top % 15 != 0) return 0;
        int intRank;
        if (rank.charAt(rank.length() - 1) == 'x') {
            intRank = Integer.parseInt(rank.substring(0, rank.length() - 2));
        } else {
            intRank = Integer.parseInt(rank);
        }
        return numberParticipant - intRank;
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

}
