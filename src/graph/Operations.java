package graph;


import parser.Vertex;
import java.io.IOException;

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

        int changes = 1;
        while(changes > 0) {
            changes = 0;

            if(Rules.rule_1a1b(graph))
                changes++;

            if(Rules.rule_2a2b(graph))
                changes++;

            if(Rules.rule_3a3b(graph))
                changes++;

            if(Rules.rule_4a(graph))
                changes++;

            if(Rules.rule_4b(graph))
                changes++;

            if(Rules.rule_5a(graph))
                changes++;

            if(Rules.rule_5b(graph))
                changes++;

            if(Rules.rule_6(graph))
                changes++;

            if(Rules.rule_7(graph))
                changes++;

            System.out.println("Made " + changes + " changes this round");
        }

        Rules.profile_high(graph);
        Rules.profile_low(graph);
        Rules.profile_off(graph);
    }

    public static void ex0(Graph g) throws IOException {
        init(g);
        checkRules(g);
    }

    public static void ex1(Graph g) throws IOException {
        g.replaceLabel(new Vertex(1, Vertex.Sensor.k), Label.falsee, Label.truee);
        checkRules(g);
    }

    public static void ex2(Graph g) throws IOException {
        g.replaceLabel(new Vertex(1, Vertex.Sensor.p), Label.falsee, Label.truee);
        checkRules(g);
    }





}
