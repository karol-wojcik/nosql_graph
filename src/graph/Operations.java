package graph;

import parser.ResultTester;
import parser.Vertex;

import java.io.File;
import java.io.IOException;

public class Operations {
    private static Boolean compareWithTheirs = true;

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
        Rules.rule_2a2b(graph);
        Rules.rule_3a3b(graph);
        Rules.rule_4a(graph);
        Rules.rule_4b(graph);
        Rules.rule_5a(graph);
        Rules.rule_5b(graph);
        Rules.rule_6(graph);
        Rules.rule_7(graph);
        Rules.profile_high(graph);
        Rules.profile_low(graph);
        Rules.profile_off(graph);
    }

    public static void ex0(Graph g) throws IOException {
        init(g);
        ResultTester.generateResultsFile(g, 0);
        compareWithTheirs(0);
    }

    public static void ex1(Graph g) throws IOException {
        g.replaceLabel(new Vertex(1, Vertex.Sensor.k), Label.falsee, Label.truee);
        checkRules(g);
        ResultTester.generateResultsFile(g, 1);
        compareWithTheirs(1);
    }

    public static void ex2(Graph g) throws IOException {
        g.replaceLabel(new Vertex(1, Vertex.Sensor.p), Label.falsee, Label.truee);
        checkRules(g);
        ResultTester.generateResultsFile(g, 1);
        compareWithTheirs(1);
    }




    private static void compareWithTheirs(Integer experiment) {
        if (compareWithTheirs) {
            System.out.print("Results of Ex. " + experiment + ": ");
            ResultTester.printResults(
                    ResultTester.compare(
                            new File("assets/results/theirs/" + experiment + ".txt"),
                            new File("assets/results/ours/" + experiment + ".txt")
                    )
            );
        }
    }
}
