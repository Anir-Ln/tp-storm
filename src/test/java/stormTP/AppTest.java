package stormTP;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.storm.shade.org.json.simple.JSONObject;
import org.apache.storm.shade.org.json.simple.parser.JSONParser;
import org.apache.storm.shade.org.json.simple.parser.ParseException;
import stormTP.core.Manager;
import stormTP.core.Runner;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    final String JSON_RUNNERS2 = """
            {"item":[
                {"id":0,"top":896,"cellule":187,"nbDevant":4,"tour":10},
                {"id":1,"top":896,"cellule":187,"nbDevant":6,"tour":10},
                {"id":2,"top":896,"cellule":199,"nbDevant":2,"tour":10},
                {"id":3,"top":896,"cellule":182,"nbDevant":8,"tour":10},
                {"id":4,"top":896,"cellule":192,"nbDevant":5,"tour":10},
                {"id":5,"top":896,"cellule":182,"nbDevant":9,"tour":10},
                {"id":6,"top":896,"cellule":206,"nbDevant":1,"tour":10},
                {"id":7,"top":896,"cellule":198,"nbDevant":3,"tour":10},
                {"id":8,"top":896,"cellule":187,"nbDevant":7,"tour":10},
                {"id":9,"top":896,"cellule":217,"nbDevant":0,"tour":10}
            ]}
    """;
//    final String JSON_RUNNERS = "{"timestamp":1701622964953,"item":["{\"id\":1,\"top\":5792,\"nbDevant\":32,\"cellule\":74,\"tour\":23}","{\"id\":2,\"top\":5792,\"nbDevant\":29,\"cellule\":103,\"tour\":23}","{\"id\":3,\"top\":5792,\"nbDevant\":15,\"cellule\":181,\"tour\":23}","{\"id\":4,\"top\":5792,\"nbDevant\":30,\"cellule\":91,\"tour\":23}","{\"id\":5,\"top\":5792,\"nbDevant\":18,\"cellule\":160,\"tour\":23}","{\"id\":6,\"top\":5792,\"nbDevant\":2,\"cellule\":15,\"tour\":24}","{\"id\":7,\"top\":5792,\"nbDevant\":22,\"cellule\":154,\"tour\":23}","{\"id\":8,\"top\":5792,\"nbDevant\":7,\"cellule\":218,\"tour\":23}","{\"id\":9,\"top\":5792,\"nbDevant\":12,\"cellule\":197,\"tour\":23}","{\"id\":10,\"top\":5792,\"nbDevant\":0,\"cellule\":98,\"tour\":24}","{\"id\":11,\"top\":5792,\"nbDevant\":31,\"cellule\":79,\"tour\":23}","{\"id\":12,\"top\":5792,\"nbDevant\":1,\"cellule\":42,\"tour\":24}","{\"id\":13,\"top\":5792,\"nbDevant\":11,\"cellule\":205,\"tour\":23}","{\"id\":14,\"top\":5792,\"nbDevant\":13,\"cellule\":195,\"tour\":23}","{\"id\":15,\"top\":5792,\"nbDevant\":14,\"cellule\":191,\"tour\":23}","{\"id\":16,\"top\":5792,\"nbDevant\":6,\"cellule\":236,\"tour\":23}","{\"id\":17,\"top\":5792,\"nbDevant\":33,\"cellule\":43,\"tour\":23}","{\"id\":18,\"top\":5792,\"nbDevant\":20,\"cellule\":156,\"tour\":23}","{\"id\":19,\"top\":5792,\"nbDevant\":3,\"cellule\":12,\"tour\":24}","{\"id\":20,\"top\":5792,\"nbDevant\":26,\"cellule\":109,\"tour\":23}","{\"id\":21,\"top\":5792,\"nbDevant\":25,\"cellule\":139,\"tour\":23}","{\"id\":22,\"top\":5792,\"nbDevant\":8,\"cellule\":212,\"tour\":23}","{\"id\":23,\"top\":5792,\"nbDevant\":5,\"cellule\":238,\"tour\":23}","{\"id\":24,\"top\":5792,\"nbDevant\":34,\"cellule\":32,\"tour\":23}","{\"id\":25,\"top\":5792,\"nbDevant\":18,\"cellule\":160,\"tour\":23}","{\"id\":26,\"top\":5792,\"nbDevant\":9,\"cellule\":206,\"tour\":23}","{\"id\":27,\"top\":5792,\"nbDevant\":28,\"cellule\":105,\"tour\":23}","{\"id\":28,\"top\":5792,\"nbDevant\":23,\"cellule\":153,\"tour\":23}","{\"id\":29,\"top\":5792,\"nbDevant\":9,\"cellule\":206,\"tour\":23}","{\"id\":30,\"top\":5792,\"nbDevant\":17,\"cellule\":162,\"tour\":23}","{\"id\":31,\"top\":5792,\"nbDevant\":24,\"cellule\":140,\"tour\":23}","{\"id\":32,\"top\":5792,\"nbDevant\":4,\"cellule\":248,\"tour\":23}","{\"id\":33,\"top\":5792,\"nbDevant\":21,\"cellule\":155,\"tour\":23}","{\"id\":34,\"top\":5792,\"nbDevant\":26,\"cellule\":109,\"tour\":23}","{\"id\":35,\"top\":5792,\"nbDevant\":16,\"cellule\":175,\"tour\":23}"]}""";
    String JSON_RUNNERS = """
        {"item":[{"id":1,"top":6117,"nbDevant":29,"cellule":156,"tour":24}","{"id":2,"top":6117,"nbDevant":26,"cellule":197,"tour":24}","{"id":3,"top":6117,"nbDevant":17,"cellule":248,"tour":24}","{"id":4,"top":6117,"nbDevant":31,"cellule":146,"tour":24}","{"id":5,"top":6117,"nbDevant":18,"cellule":236,"tour":24}","{"id":6,"top":6117,"nbDevant":6,"cellule":49,"tour":25}","{"id":7,"top":6117,"nbDevant":25,"cellule":200,"tour":24}","{"id":8,"top":6117,"nbDevant":11,"cellule":18,"tour":25}","{"id":9,"top":6117,"nbDevant":13,"cellule":12,"tour":25}","{"id":10,"top":6117,"nbDevant":0,"cellule":213,"tour":25}","{"id":11,"top":6117,"nbDevant":32,"cellule":142,"tour":24}","{"id":12,"top":6117,"nbDevant":1,"cellule":116,"tour":25}","{"id":13,"top":6117,"nbDevant":7,"cellule":33,"tour":25}","{"id":14,"top":6117,"nbDevant":15,"cellule":8,"tour":25}","{"id":15,"top":6117,"nbDevant":14,"cellule":10,"tour":25}","{"id":16,"top":6117,"nbDevant":4,"cellule":65,"tour":25}","{"id":17,"top":6117,"nbDevant":33,"cellule":112,"tour":24}","{"id":18,"top":6117,"nbDevant":19,"cellule":220,"tour":24}","{"id":19,"top":6117,"nbDevant":2,"cellule":79,"tour":25}","{"id":20,"top":6117,"nbDevant":30,"cellule":154,"tour":24}","{"id":21,"top":6117,"nbDevant":26,"cellule":197,"tour":24}","{"id":22,"top":6117,"nbDevant":3,"cellule":68,"tour":25}","{"id":23,"top":6117,"nbDevant":10,"cellule":25,"tour":25}","{"id":24,"top":6117,"nbDevant":34,"cellule":93,"tour":24}","{"id":25,"top":6117,"nbDevant":22,"cellule":210,"tour":24}","{"id":26,"top":6117,"nbDevant":9,"cellule":26,"tour":25}","{"id":27,"top":6117,"nbDevant":28,"cellule":170,"tour":24}","{"id":28,"top":6117,"nbDevant":22,"cellule":210,"tour":24}","{"id":29,"top":6117,"nbDevant":8,"cellule":27,"tour":25}","{"id":30,"top":6117,"nbDevant":24,"cellule":204,"tour":24}","{"id":31,"top":6117,"nbDevant":20,"cellule":212,"tour":24}","{"id":32,"top":6117,"nbDevant":5,"cellule":50,"tour":25}","{"id":33,"top":6117,"nbDevant":12,"cellule":14,"tour":25}","{"id":34,"top":6117,"nbDevant":20,"cellule":212,"tour":24}","{"id":35,"top":6117,"nbDevant":16,"cellule":254,"tour":24}],"timestamp":1701624590705}
        """;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }


    public void test() {
        String test = """
                {"id":25,"top":6482,"nbDevant":22,"cellule":64,"tour":26}""";
        JSONParser parser = new JSONParser();
        try {
            JSONObject turtle = (JSONObject) parser.parse(test);
            turtle.put("nom", "Lahyane-Dennoun");
            int i = Integer.parseInt(turtle.get("id").toString());
//            System.out.println(turtle);
//            System.out.println(i);
            String json = turtle.toJSONString();
            System.out.println(json.length());
            System.out.println(json);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Test pour question 1
     */
    public void testTortoiseFilter() {
        int dossard = 1;
        String nomsBinome = "Toto-Titi";

        Manager tm = new Manager(dossard, nomsBinome);
        String output = "{\"id\":5,\"top\":896,\"nom\":\"" + nomsBinome + "\",\"cellule\":182,\"nbDevant\":9,\"tour\":10}";
        System.out.println("@Test testTortoiseFilter()");
        String result = tm.filter(JSON_RUNNERS).getJSON_V1();
        System.out.println("output: " + output);
        System.out.println("result: " + result);
//        assertEquals(output, result);
    }


    /**
     * Test1 pour question 2
     */
    public void test1TortoiseComputeRank() {
        int dossard = 1;
        String nomsBinome = "Toto-Titi";
        Manager tm = new Manager(dossard, nomsBinome);
        List<Runner> runners = tm.jsonToList(JSON_RUNNERS);

        System.out.println("@Test test1TortoiseComputeRank()");
        runners.forEach(
                runner -> System.out.println(runner.getJSON_V1())
        );
        tm.computeRankWithDistance(5, runners);
        System.out.println("---------");
        runners.forEach(
                runner -> System.out.println(runner.getJSON_V1())
        );

        String[] expectedRangs = {"1", "2", "3", "4", "5", "6ex", "6ex", "6ex", "7ex", "7ex"};
        for (int i=0; i<runners.size(); i++) assertEquals(expectedRangs[i], runners.get(i).getRang());
    }

    /**
     * Test1 pour question 3
     * 4 * ( 4 - 1) = 12
     * (1, 15, 'Toto', '1', 10)     -> 1(x - 1) + 9 = 9
     * (1, 60, 'Toto', '3', 10)     -> 4(y - 3) - 3 + 7 = 30
     * (1, 30, 'Toto', '3ex', 10)   -> 2(z - 3) - 3 + 7 = 15
     * (1, 45, 'Toto', '2', 10)     -> 3(z - 2) - 2 + 8 = 23
     *
     * (1, 15, 'Toto', 9)
     * (1, 60, 'Toto', 30)
     * (1, 30, 'Toto', 15)
     * (1, 45, 'Toto', 23)
     */
    public void test1TortoiseComputePoints() {
        int dossard = 1;
        String nomsBinome = "Toto-Titi";

        Manager tm = new Manager(dossard, nomsBinome);

        // setup
        int numberParticipant = 10;
        int top = 30;
        String rang = "2ex";
        Runner runner = new Runner(
            1, "", 1, 1, 123, top
        );
        runner.setRang(rang);

        System.out.println("@Test test1TortoiseComputePoints()");
        System.out.println("input: " + runner.getJSON_V1());

        int output = tm.computePoints(runner, numberParticipant);
        int result = 8;

        System.out.println("output: " + output);
        System.out.println("result: " + result);

        assertEquals(result, output);
    }


    /**
     * Test2 pour question 3
     */

    public void test2TortoisecomputePoints() {
        int dossard = 1;
        String nomsBinome = "Toto-Titi";

        Manager tm = new Manager(dossard, nomsBinome);

        // setup
        int numberParticipant = 10;
        int top = 45;
        String rang = "1";
        Runner runner = new Runner(
                1, "", 1, 1, 123, top
        );
        runner.setRang(rang);

        System.out.println("@Test test2TortoiseComputePoints()");
        System.out.println("input: " + runner.getJSON_V1());

        int output = tm.computePoints(runner, numberParticipant);
        int result = 9;

        System.out.println("output: " + output);
        System.out.println("result: " + result);

        assertEquals(result, output);
    }


    /**
     * Test pour question 4
     */

    public void testTortoiseSpeed() {
        int dossard = 1;
        String nomsBinome = "Toto-Titi";

        Manager tm = new Manager(dossard, nomsBinome);

        String input = "(20, 35, 10, 17)";


        System.out.println("@Test test1TortoiseComputeSpeed()");

        System.out.println("input: " + input);

        double output = tm.computeSpeed(20, 35, 10, 17);
        double result = 0.46;


        System.out.println("output: " + output);
        System.out.println("result: " + result);
        System.out.println();

        assertEquals(result, output);
    }


    /**
     * Test1 pour question 5
     */

    public void test1TortoiseRankEvolution() {
        int dossard = 1;
        String nomsBinome = "Toto-Titi";

        Manager tm = new Manager(dossard, nomsBinome);

        String input = "2, 2";


        System.out.println("@Test test1TortoiseRankEvolution(" + input + ")");

        System.out.println("input: " + input);

        String output = tm.giveRankEvolution(2, 2);
        String result = Manager.CONST;


        System.out.println("output: " + output);
        System.out.println("result: " + result);
        System.out.println();

        assertEquals(result, output);
    }

    /**
     * Test2 pour question 5
     */

    public void test2TortoiseRankEvolution() {
        int dossard = 1;
        String nomsBinome = "Toto-Titi";

        Manager tm = new Manager(dossard, nomsBinome);

        String input = "1, 3";


        System.out.println("@Test test2TortoiseRankEvolution(" + input + ")");

        System.out.println("input: " + input);

        String output = tm.giveRankEvolution(1, 3);
        String result = Manager.PROG;


        System.out.println("output: " + output);
        System.out.println("result: " + result);
        System.out.println();

        assertEquals(result, output);
    }

    /**
     * Test3 pour question 5
     */

    public void test3TortoiseRankEvolution() {
        int dossard = 1;
        String nomsBinome = "Toto-Titi";

        Manager tm = new Manager(dossard, nomsBinome);

        String input = "6, 2";


        System.out.println("@Test test3TortoiseRankEvolution(" + input + ")");

        System.out.println("input: " + input);

        String output = tm.giveRankEvolution(6, 2);
        String result = Manager.REGR;


        System.out.println("output: " + output);
        System.out.println("result: " + result);
        System.out.println();

        assertEquals(result, output);
    }


    /**
     * Test4 pour question 5
     */

    public void testTortoiseAverageRank() {
        int dossard = 1;
        String nomsBinome = "Toto-Titi";

        Manager tm = new Manager(dossard, nomsBinome);

        String[] input = new String[6];
        input[0] = "2";
        input[1] = "1ex";
        input[2] = "3";
        input[3] = "4ex";
        input[4] = "2ex";
        input[5] = "1";


        System.out.println("@Test testGiveAverageRank()");

        System.out.println("input: " + get(input));

        int output = tm.giveAverageRank(input);
        int result = 2;


        System.out.println("output: " + output);
        System.out.println("result: " + result);
        System.out.println();

        assertEquals(result, output);
    }


    private static String get(String[] t) {
        String res = " ";
        for (int i = 0; i < t.length - 1; i++) {
            res += t[i] + ", ";
        }
        res += t[t.length - 1] + " ";
        return res;
    }


    /**
     * Test pour partie 4
     */
    public void testPodium() {

        String input = "{ \"rabbits\":[ ";
        input += "{\"id\":0,\"top\":896,\"nom\":\"RogerRabbit\",\"position\":194,\"nbDevant\":4,\"nbDerriere\":5,\"total\":10},";
        input += "{\"id\":1,\"top\":896,\"nom\":\"BugsBunny\",\"position\":189,\"nbDevant\":6,\"nbDerriere\":3,\"total\":10},";
        input += "{\"id\":2,\"top\":896,\"nom\":\"Panpan\",\"position\":199,\"nbDevant\":2,\"nbDerriere\":7,\"total\":10},";
        input += "{\"id\":3,\"top\":896,\"nom\":\"Caerbannog\",\"position\":185,\"nbDevant\":8,\"nbDerriere\":1,\"total\":10},";
        input += "{\"id\":4,\"top\":896,\"nom\":\"Oswald\",\"position\":192,\"nbDevant\":5,\"nbDerriere\":4,\"total\":10},";
        input += "{\"id\":5,\"top\":896,\"nom\":\"Jojo\",\"position\":182,\"nbDevant\":9,\"nbDerriere\":0,\"total\":10},";
        input += "{\"id\":6,\"top\":896,\"nom\":\"Coco\",\"position\":206,\"nbDevant\":1,\"nbDerriere\":8,\"total\":10},";
        input += "{\"id\":7,\"top\":896,\"nom\":\"JuddyHopps\",\"position\":198,\"nbDevant\":3,\"nbDerriere\":6,\"total\":10},";
        input += "{\"id\":8,\"top\":896,\"nom\":\"LapinBlanc\",\"position\":187,\"nbDevant\":7,\"nbDerriere\":2,\"total\":10},";
        input += "{\"id\":9,\"top\":896,\"nom\":\"Basil\",\"position\":217,\"nbDevant\":0,\"nbDerriere\":9,\"total\":10}";
        input += "] }";

        String input2 = "{ \"rabbits\":[ ";
        input2 += "{\"id\":0,\"top\":123,\"nom\":\"RogerRabbit\",\"position\":4,\"nbDevant\":8,\"nbDerriere\":1,\"total\":10},";
        input2 += "{\"id\":1,\"top\":123,\"nom\":\"BugsBunny\",\"position\":11,\"nbDevant\":4,\"nbDerriere\":4,\"total\":10},";
        input2 += "{\"id\":2,\"top\":123,\"nom\":\"Panpan\",\"position\":15,\"nbDevant\":1,\"nbDerriere\":7,\"total\":10},";
        input2 += "{\"id\":3,\"top\":123,\"nom\":\"Caerbannog\",\"position\":5,\"nbDevant\":7,\"nbDerriere\":2,\"total\":10},";
        input2 += "{\"id\":4,\"top\":123,\"nom\":\"Oswald\",\"position\":11,\"nbDevant\":4,\"nbDerriere\":4,\"total\":10},";
        input2 += "{\"id\":5,\"top\":123,\"nom\":\"Jojo\",\"position\":14,\"nbDevant\":3,\"nbDerriere\":6,\"total\":10},";
        input2 += "{\"id\":6,\"top\":123,\"nom\":\"Coco\",\"position\":48,\"nbDevant\":0,\"nbDerriere\":9,\"total\":10},";
        input2 += "{\"id\":7,\"top\":123,\"nom\":\"JuddyHopps\",\"position\":8,\"nbDevant\":6,\"nbDerriere\":3,\"total\":10},";
        input2 += "{\"id\":8,\"top\":123,\"nom\":\"LapinBlanc\",\"position\":15,\"nbDevant\":1,\"nbDerriere\":7,\"total\":10},";
        input2 += "{\"id\":9,\"top\":123,\"nom\":\"Basil\",\"position\":1,\"nbDevant\":9,\"nbDerriere\":0,\"total\":10}";
        input2 += "] }";

        String output = "{\"top\":896,\"marcheP1\":[{\"nom\":\"Basil\"}],\"marcheP2\":[{\"nom\":\"Coco\"}],\"marcheP3\":[{\"nom\":\"Panpan\"}]}";
        String output2 = "{\"top\":123,\"marcheP1\":[{\"nom\":\"Coco\"}],\"marcheP2\":[{\"nom\":\"LapinBlanc\"},{\"nom\":\"Panpan\"}],\"marcheP3\":[{\"nom\":\"Jojo\"}]}";

        System.out.println("@Test testPodium()");

        System.out.println("input: " + input);

        String result = Manager.getPodium(input);


        System.out.println("output: " + output);
        System.out.println("result: " + result);
        System.out.println();

        assertEquals(result, output);

        System.out.println("@Test2 testPodium()");

        System.out.println("input: " + input2);

        String result2 = Manager.getPodium(input2);


        System.out.println("output: " + output2);
        System.out.println("result: " + result2);
        System.out.println();

        assertEquals(result2, output2);


    }


}
