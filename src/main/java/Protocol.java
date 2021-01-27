import java.io.IOException;

public class Protocol {
    public void ProtocolSetting() throws IOException, InterruptedException {
        String JsonFile = "C:/Users/td451/Desktop/Vjezba5/target/WaterFlowMeter.json";
        WaterFlowMeter waterFlowMeter = WaterFlowMeter.JsonFun(JsonFile);

        // if mqtt
        //MqttPublisher mqttPublisher = new MqttPublisher();
        //mqttPublisher.publish(waterFlowMeter);

        // if http
        HttpPublisher httpPublisher = new HttpPublisher();
        httpPublisher.publish(waterFlowMeter);
    }
}
