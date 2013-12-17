package parser;

/**
 * Created with IntelliJ IDEA.
 * User: Karol
 * Date: 17.12.13
 * Time: 21:51
 */
public class Vertex {
    String sensor;
    Integer id;


    public Vertex(Integer id, String sensor) {
        this.sensor = sensor;
        this.id = id;
    }

    public String getSensor() {
        return sensor;
    }

    public Integer getId() {
        return id;
    }

    public String toString() {
        return "id: " + id + ", sensor: " + sensor;
    }
}
