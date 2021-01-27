
import org.eclipse.paho.client.mqttv3.MqttException;

public interface Publisher extends WaterFlowMeterInterface {
    public default void publish() {};
}
