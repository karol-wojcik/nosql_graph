package graph;

import parser.Graph;
import parser.Vertex;

import java.util.List;

public class Operations {

    private boolean hasLabel(Graph graph, Vertex vertex, Label label) {
        throw new RuntimeException("Not implemented");
    }

    // vlr
    public void removeLabel(Vertex vertex, Label label) {

    }

    // vla
    public void addLabel(Vertex vertex, Label label) {

    }

    // vle
    public void replaceLabel(Vertex vertex, Label from, Label to) {
        removeLabel(vertex, from);
        addLabel(vertex, to);
    }

    // vlc (ALL of this kind)
    public void clearLabels(Vertex vertex, Label label) {

    }

    // vlc (ALL in VERTEX)
    public void clearLabels(Vertex vertex) {

    }

    // vlc (ALL)
    public void clearLabels(Graph graph) {

    }

    public List<Label> getLabels(Graph graph, Vertex vertex) {
        throw new RuntimeException("Not implemented");
    }

    public List<Vertex> findVerticesWithSensor(Graph graph, Vertex.Sensor sensor) {
        throw new RuntimeException("Not implemented");
    }

    public List<Vertex> findVerticesWithSensorAndLabel(Graph graph, Vertex.Sensor sensor, Label flag) {
        throw new RuntimeException("Not implemented");
    }

    private List<Vertex> getConfigsInSegment(Graph graph, Vertex segment) {
        return null;
    }

    private List<Vertex> findSegments_2a2b(Graph graph) {
        return null;
    }

    private List<Vertex> findSegments_1a1b(Graph graph) {
        List<Vertex> v2 = findVerticesWithSensor(graph, Vertex.Sensor.s);
        List<Vertex> v3 = findVerticesWithSensorAndLabel(graph, Vertex.Sensor.s, Label.off);
        return null; // v2 - v3
    }

    private List<Vertex> getSensorsInSegment(Graph graph, Vertex segment) {
        return null;
    }

    public void init(Graph graph) {
        clearLabels(graph);
        addLabel(new Vertex(1, Vertex.Sensor.k), Label.falsee);
        addLabel(new Vertex(1, Vertex.Sensor.h), Label.day);
        for(Vertex v : findVerticesWithSensor(graph, Vertex.Sensor.c)) {
            addLabel(v, Label.off);
        }
        for(Vertex v : findVerticesWithSensor(graph, Vertex.Sensor.p)) {
            addLabel(v, Label.falsee);
        }
        for(Vertex v : findVerticesWithSensor(graph, Vertex.Sensor.d)) {
            addLabel(v, Label.falsee);
        }
    }

    /*

    r('1a1b') :-

    v(k,L,false),       v1
    e(k,L,s,J),         e1, v2 -> V(s,J)
    \+ v(s,J,off),      v3
    e(s,J,c,_,_),       e2, v4 -> V(c)
    then,
    vla(s,J,off).

                        v1 --e1--> v2 (bez v3) --e2--> v4
                                        v5


     */
    public void rule_1a1b(Graph graph) {

        List<Vertex> segments = findSegments_1a1b(graph);
        for(Vertex segment : segments) {

            boolean foundSensor = false;
            boolean foundConfig = false;

            List<Vertex> sensors = getSensorsInSegment(graph, segment);
            for(Vertex sensor : sensors) {
                if(sensor.sensor == Vertex.Sensor.k && hasLabel(graph, sensor, Label.falsee)) {
                    foundSensor = true;
                }
            }

            List<Vertex> configs = getConfigsInSegment(graph, segment);
            for(Vertex config : configs) {
                foundConfig = true;
                break;
            }

            if(foundConfig && foundSensor) {
                addLabel(segment, Label.off);
            }
        }
    }

    /*

    r('2a2b') :-
    v(k,L,true),            sensors1
    v(p,M,false),           sensors2
    e(p,M,s,J,in),
    \+ v(s,J,low),          segments -> v(s,J,in) - v(s,J,low)
    e(k,L,s,J),
    e(s,J,c,_,low),
    then,
    vla(s,J,low).

     */

    public void rule_2a2b(Graph graph) {

        List<Vertex> segments = findSegments_2a2b(graph);

        for(Vertex segment : segments) {

            boolean foundSensor1 = false;
            boolean foundSensor2 = false;
            boolean foundConfig = false;

            List<Vertex> sensors = getSensorsInSegment(graph, segment);
            for(Vertex sensor : sensors) {
                if(sensor.sensor == Vertex.Sensor.k && hasLabel(graph, sensor, Label.truee)) {
                    foundSensor1 = true;
                }
                if(sensor.sensor == Vertex.Sensor.p && hasLabel(graph, sensor, Label.falsee)) {
                    foundSensor2 = true;
                }
            }

            List<Vertex> configs = getConfigsInSegment(graph, segment);
            for(Vertex config : configs) {
                if(hasLabel(graph, config, Label.low)) {
                    foundConfig = true;
                    break;
                }
            }

            if(foundConfig && foundSensor1 && foundSensor2) {
                addLabel(segment, Label.low);
            }
        }

    }






}
