package parser;

/**
 * Created with IntelliJ IDEA.
 * User: Karol
 * Date: 17.12.13
 * Time: 21:51
 */
public class Vertex {
    public Sensor sensor;
    public int id;

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

    @Override
    public boolean equals(Object arg0) {
        if(arg0 instanceof Vertex) {
            Vertex v = (Vertex)arg0;
            return v.id == id && v.sensor == sensor;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (sensor.toString() + Integer.toString(id)).hashCode();
    }




}
