
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.ArrayList;
import java.util.UUID;

public class MqttPublisher implements Publisher {
    private Sensor sensor;


    //@Override
    public void publish(WaterFlowMeter waterFlowMeter) {
        String brokerURL = waterFlowMeter.getBrokerURL();
        ArrayList<Sensor> sensors = waterFlowMeter.getSensors();
        String topic = waterFlowMeter.getTopic();

        try {
            MqttClient Client = new MqttClient(brokerURL, UUID.randomUUID().toString(), new MemoryPersistence());
            while (true) {
                Thread.sleep(3500);
                MqttConnectOptions connectionOptions = new MqttConnectOptions();
                connectionOptions.setCleanSession(true);
                Client.connect(connectionOptions);
                if (!Client.isConnected()) {
                    System.out.println("Client not connected. ");
                    continue;
                } else {
                    System.out.println("Broker " + brokerURL + " connected. ");
                }

                for (Sensor sensor : sensors) {
                    ObjectMapper mapper = new ObjectMapper();
                    sensor.setSensorValue();
                    String output = mapper.writeValueAsString(sensor);
                    MqttMessage message = new MqttMessage(output.getBytes());
                    message.setQos(0);
                    Client.publish(topic, message); }
                Client.disconnect();
            }
        } catch (InterruptedException | JsonProcessingException e) { e.printStackTrace(); }
        catch (MqttException e){}

    }
}
