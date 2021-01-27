import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.beans.ConstructorProperties;
import java.util.Random;

public class Sensor {

    private String name;
    private int factor;
    private double minRangeValue;
    private double maxRangeValue;
    private String unit;
    private double Value;

    public String getName() {
        return name;
    }
    public int getFactor() { return factor; }
    public float getMinRangeValue() {
        return (float) minRangeValue;
    }
    public float getMaxRangeValue() {
        return (float) maxRangeValue;
    }
    public String getUnit() {
        return unit;
    }
    public double getValue() {
        return Value;
    }

    @JsonCreator
    public Sensor(@JsonProperty("name") String name,@JsonProperty("factor") int factor,@JsonProperty("minRangeValue") double minRangeValue,@JsonProperty("maxRangeValue") double maxRangeValue,@JsonProperty("unit") String unit) {
        this.name = name;
        this.factor = factor;
        this.minRangeValue = minRangeValue;
        this.maxRangeValue = maxRangeValue;
        this.unit = unit;
        this.Value = setSensorValue();
    }

    public double setSensorValue() {
        Random rand = new Random();
        int temp = rand.ints((int)minRangeValue, (int)maxRangeValue).findAny().getAsInt();
        if(factor != 0) {
            return (double) temp / factor;
        }
        return (double) temp;
    }

}
