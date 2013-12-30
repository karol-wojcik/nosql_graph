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

    public static void checkRules(Graph graph) {
        Rules.rule_1a1b(graph);
//        Rules.rule_2a2b(graph);
//        Rules.rule_3a3b(graph);
//        Rules.rule_4a(graph);
//        Rules.rule_4b(graph);
//        Rules.rule_5a(graph);
//        Rules.rule_5b(graph);
//        Rules.rule_6(graph);
//        Rules.rule_7(graph);
        // todo: add other rules
    }

    public static void ex0(Graph g) {
        init(g);
    }

    public static void ex1(Graph g) {
        g.replaceLabel(new Vertex(1, Vertex.Sensor.k), Label.falsee, Label.truee);
        checkRules(g);
    }





}
