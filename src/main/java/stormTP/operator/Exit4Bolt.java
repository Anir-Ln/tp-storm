package stormTP.operator;

import org.apache.storm.shade.org.json.simple.JSONObject;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import stormTP.stream.StreamEmiter;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.logging.Logger;

public class Exit4Bolt extends BaseRichBolt {
    private OutputCollector collector;
    Logger logger = null;

    String ipM = "";
    int port = -1;
    StreamEmiter semit = null;

    public Exit4Bolt (int port, String ip) {
        this.port = port;
        this.ipM = ip;
        this.semit = new StreamEmiter(this.port,this.ipM);
    }

    @Override
    public void prepare(Map<String, Object> topoConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        logger = Logger.getLogger(Exit3Bolt.class.getName());
    }

    @Override
    public void execute(Tuple tuple) {
        logger.info("Exit4Bolt " + tuple.toString());
        JSONObject obj = new JSONObject();
        obj.put("id", tuple.getIntegerByField("id"));
        obj.put("top", tuple.getIntegerByField("top"));
        obj.put("nom", tuple.getStringByField("nom"));
        obj.put("score", tuple.getIntegerByField("score"));

        String json = obj.toJSONString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
        logger.info(sdf.format(System.currentTimeMillis()) + ":" + json);
        // Emit JSON object
        this.semit.send(json);
        collector.ack(tuple);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("json"));
    }
}
