import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HttpPublisher implements Publisher {
    private Sensor sensor;


    public void publish(WaterFlowMeter waterFlowMeter) throws InterruptedException {
        ArrayList<Sensor> sensors = waterFlowMeter.getSensors();

        while (true) {
            Thread.sleep(3500);
            try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
                HttpPost request = new HttpPost("https://httpbin.org/post");
                request.setHeader("User-Agent", "Java client");

                for (Sensor sensor : sensors) {

                    //request.setEntity(new StringEntity("hello meeee "));
                    sensor.setSensorValue();
                    ObjectMapper mapper = new ObjectMapper();
                    sensor.setSensorValue();
                    String output = mapper.writeValueAsString(sensor);
                    request.setEntity(new StringEntity(output));

                    HttpResponse response = client.execute(request);

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    StringBuilder builder = new StringBuilder();
                    String line;

                    while ((line = buffer.readLine()) != null) {

                        builder.append(line);
                        builder.append(System.lineSeparator());
                    }

                    System.out.println(builder);
                }
            } catch (IOException e){}
        }
    }
}
