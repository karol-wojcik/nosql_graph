package parser;

/**
 * Created with IntelliJ IDEA.
 * User: Karol
 * Date: 17.12.13
 * Time: 21:51
 */
public class Vertex {
    public Sensor sensor;
    int id;

    public Vertex(int id, String sensor) {
        this.sensor = Sensor.valueOf(sensor);
        this.id = id;
    }

    public Vertex(int id, Sensor sensor) {
        this.sensor = sensor;
        this.id = id;
    }

    public String getSensor() {
        return sensor.toString();
    }

    public Integer getId() {
        return id;
    }

    public String toString() {
        return "V: " + id + ", s: " + sensor;
    }

    public static enum Sensor {a,s,c,k,d,p,h,l}
}
