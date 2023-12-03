package stormTP.operator;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.ITuple;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import stormTP.core.Manager;
import stormTP.core.Runner;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class GiveRankBolt extends BaseRichBolt {
    private Logger logger;
    private OutputCollector collector;
    private HashMap<Integer, Boolean> map;
    @Override
    public void prepare(Map<String, Object> topoConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        logger = Logger.getLogger(GiveRankBolt.class.getName());
        map = new HashMap<>();
    }

    @Override
    public void execute(Tuple input) {
        int id = input.getIntegerByField("id");
        int top = input.getIntegerByField("top");
        int nbDevant = input.getIntegerByField("nbDevant");
        String nom = input.getStringByField("nom");

        Manager manager = new Manager(8, "Lahyane-Denoun");
        String rank = manager.computeRank(nbDevant, map);

        logger.info("calculating rank (=" + rank + ") for " + input);
        collector.emit(new Values(
                id,
                top,
                nom,
                rank
        ));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("id", "top", "nom", "rang"));
    }
}
