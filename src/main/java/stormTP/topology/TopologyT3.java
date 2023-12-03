package stormTP.topology;

import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import stormTP.operator.*;

public class TopologyT3 {
    public static void main(String[] args) throws Exception {
        final int nbExecutors = 1;
        final int room = Integer.parseInt(args[0]);
        final int portINPUT = 9000 + room;
        final int portOUTPUT = 9005;
        String ipmINPUT = "224.0.0." + room;
        String ipmOUTPUT = "225.0.0." + room;

        /*Création du spout*/
        MasterInputStreamSpout spout = new MasterInputStreamSpout(portINPUT, ipmINPUT);
        /*Création de la topologie*/
        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout("masterStream", spout);
        builder.setBolt("MyTortoiseBolt", new MyTortoiseBolt(), nbExecutors).shuffleGrouping("masterStream");
        builder.setBolt("GiveRankBolt", new GiveRankBolt(), nbExecutors).shuffleGrouping("MyTortoiseBolt");
        builder.setBolt("Exit3Bolt", new Exit3Bolt(portOUTPUT, ipmOUTPUT), nbExecutors).shuffleGrouping("GiveRankBolt");

        /*Création d'une configuration*/
        Config config = new Config();
        config.setDebug(true);
        /*La topologie est soumise à STORM*/
        StormSubmitter.submitTopology("topoT3", config, builder.createTopology());
    }
}
