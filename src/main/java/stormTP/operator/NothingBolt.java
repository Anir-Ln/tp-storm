package stormTP.operator;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;


/**
 * Sample of stateless operator
 * @author lumineau
 *
 */
public class NothingBolt implements IRichBolt {

	private OutputCollector collector;
	Logger logger = Logger.getGlobal();


	public NothingBolt () {
		
	}
	
	/* (non-Javadoc)
	 * @see backtype.storm.topology.IRichBolt#execute(backtype.storm.tuple.Tuple)
	 */
	public void execute(Tuple t) {
	
		try {
			String n = t.getValueByField("json").toString();
			logger.log(Level.INFO, "treated");
			logger.log(Level.INFO, n);
			collector.emit(t, new Values(n));
			//collector.ack(t);
		}catch (Exception e){
			logger.log(Level.INFO, "Empty tuple");
		}
		return;
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see backtype.storm.topology.IComponent#declareOutputFields(backtype.storm.topology.OutputFieldsDeclarer)
	 */
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		arg0.declare(new Fields("json"));
	}
		

	/* (non-Javadoc)
	 * @see backtype.storm.topology.IComponent#getComponentConfiguration()
	 */
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

	/* (non-Javadoc)
	 * @see backtype.storm.topology.IBasicBolt#cleanup()
	 */
	public void cleanup() {
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see backtype.storm.topology.IRichBolt#prepare(java.util.Map, backtype.storm.task.TopologyContext, backtype.storm.task.OutputCollector)
	 */
	@SuppressWarnings("rawtypes")
	public void prepare(Map arg0, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}
}