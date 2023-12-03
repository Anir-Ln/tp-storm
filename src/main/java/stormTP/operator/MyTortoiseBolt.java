package stormTP.operator;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import stormTP.core.Manager;
import stormTP.core.Runner;

import java.util.Map;
import java.util.logging.Logger;


public class MyTortoiseBolt extends BaseRichBolt {
    Logger logger;
    private OutputCollector collector;
    @Override
    public void prepare(Map<String, Object> topoConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        logger = Logger.getLogger(MyTortoiseBolt.class.getName());
    }

    @Override
    public void execute(Tuple input) {
        String json =  input.getValueByField("json").toString();
        logger.info("executing tuple " + json);
        Manager manager = new Manager(8, "Lahyane-Denoun");
        Runner turtle = manager.filter(json);
        logger.info("filtered turtule: " + turtle);
        collector.emit(new Values(
                turtle.getId(),
                turtle.getTop(),
                turtle.getNom(),
                turtle.getCellule(),
                turtle.getNbDevant(),
                turtle.getTour()
        ));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("id", "top", "nom", "cellule", "nbDevant", "tour"));
    }
}
