package stormTP.operator;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

public class MyTortoiseBolt extends BaseRichBolt {
    private OutputCollector collector;
    @Override
    public void prepare(Map<String, Object> topoConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    @Override
    public void execute(Tuple input) {
        String inputJson = input.getValueByField("json").toString();
        Runner runner5 = Manager.filter(inputJson);
        int id = runner5.getId();
        int top = runner5.getTop();
        String nom = runner5.getNom();
        int position = runner5.getPosition();
        int nbDevant = runner5.getNbDevant();
        int nbTotal = runner5.getTotal;

        // Construire le nom en utilisant le format "nomBinome1-nomBinome2"
        String nom = "Atrovite-Paltan";
        collector.emmit(input, new Values(id, top, nom, position, nbDevant, nbTotal));
        _collector.ack(input)
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
    }
}
