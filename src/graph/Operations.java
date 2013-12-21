package graph;

import parser.Vertex;

public class Operations {

    public static void init(Graph g) {
        g.clearLabels();
        g.addLabel(new Vertex(1, Vertex.Sensor.k), Label.falsee);
        g.addLabel(new Vertex(1, Vertex.Sensor.h), Label.day);
        for(Vertex v : g.findVerticesWithSensor(Vertex.Sensor.c)) {
            g.addLabel(v, Label.off);
        }
        for(Vertex v : g.findVerticesWithSensor(Vertex.Sensor.p)) {
            g.addLabel(v, Label.falsee);
        }
        for(Vertex v : g.findVerticesWithSensor(Vertex.Sensor.d)) {
            g.addLabel(v, Label.falsee);
        }
    }

    public static void ex1(Graph g) {
        init(g);
    }





}
