import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.ArrayList;

public class WaterFlowMeter implements WaterFlowMeterInterface {
    private String brokerURL;
    private String topic;
    private ArrayList<Sensor> sensors;

    @JsonCreator
    public WaterFlowMeter(@JsonProperty("brokerURL") String brokerURL, @JsonProperty("topic") String topic, @JsonProperty("sensors") ArrayList<Sensor> sensors) {
        this.brokerURL = brokerURL;
        this.topic = topic;
        this.sensors = sensors; }

    public String getBrokerURL() {
        return this.brokerURL;
    }

    public String getTopic() {
        return this.topic;
    }

    public ArrayList<Sensor> getSensors() {
        return this.sensors;
    }


    public static WaterFlowMeter JsonFun(String JsonFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        WaterFlowMeter wfm = mapper.readValue(new File(JsonFile), WaterFlowMeter.class);
        return wfm; }
}

