package stormTP.topology;

import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import stormTP.operator.Exit2Bolt;
import stormTP.operator.MasterInputStreamSpout;
import stormTP.operator.MyTortoiseBolt;

public class TopologyT2 {
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
        /*Affectation à la topologie du spout*/
        builder.setSpout("masterStream", spout);
        /*Affectation à la topologie du bolt qui ne fait rien, il prendra en input le spout localStream*/
        builder.setBolt("MyTortoiseBolt", new MyTortoiseBolt(), nbExecutors).shuffleGrouping("masterStream");
        /*Affectation à la topologie du bolt qui émet le flux de sortie, il prendra en input le bolt nofilter*/
        builder.setBolt("exit2Bolt", new Exit2Bolt(portOUTPUT, ipmOUTPUT), nbExecutors).shuffleGrouping("MyTortoiseBolt");

        /*Création d'une configuration*/
        Config config = new Config();
        config.setDebug(true);
        /*La topologie est soumise à STORM*/
        StormSubmitter.submitTopology("topoT2", config, builder.createTopology());
    }
}
