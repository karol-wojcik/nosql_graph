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

    /*
        init.
    */

    public static void ex0(Graph g) throws IOException {
        init(g);
        ResultTester.generateResultsFile(g, 0);
        compareWithTheirs(0);
    }

    /*
        ex(1) :-
        vle(k,1,false,true),
        run. % cJ -> on
    */

    public static void ex1(Graph g) throws IOException {
        g.replaceLabel(new Vertex(1, Vertex.Sensor.k), Label.falsee, Label.truee);
        checkRules(g);
        ResultTester.generateResultsFile(g, 1);
        compareWithTheirs(1);
    }

    /*
        ex(2) :-
        vle(p,1,false,true),
        run. % c2 -> off, c1 -> on
    */
    public static void ex2(Graph g) throws IOException {
        g.replaceLabel(new Vertex(1, Vertex.Sensor.p), Label.falsee, Label.truee);
        checkRules(g);
        ResultTester.generateResultsFile(g, 2);
        compareWithTheirs(2);
    }

    /*
        ex(3) :-
        vle(p,6,false,true),
        run. % c14 -> off, c13->on
    */
    public static void ex3(Graph g) throws IOException {
        g.replaceLabel(new Vertex(6, Vertex.Sensor.p), Label.falsee, Label.truee);
        checkRules(g);
        ResultTester.generateResultsFile(g, 3);
        compareWithTheirs(3);
    }

    /*
        ex(4) :-
        vle(d,1,false,true),
        run. % c16 -> off, c15 -> on
    */
    public static void ex4(Graph g) throws IOException {
        g.replaceLabel(new Vertex(1, Vertex.Sensor.d), Label.falsee, Label.truee);
        checkRules(g);
        ResultTester.generateResultsFile(g, 4);
        compareWithTheirs(4);
    }

    /*
        ex(5) :-
        vle(d,8,false,true),
        run. % nothing
    */
    public static void ex5(Graph g) throws IOException {
        g.replaceLabel(new Vertex(8, Vertex.Sensor.d), Label.falsee, Label.truee);
        checkRules(g);
        ResultTester.generateResultsFile(g, 5);
        compareWithTheirs(5);
    }

    /*
        ex(6) :-
        vle(d,1,true,false),
        run. %
    */
    public static void ex6(Graph g) throws IOException {
        g.replaceLabel(new Vertex(1, Vertex.Sensor.d), Label.truee, Label.falsee);
        checkRules(g);
        ResultTester.generateResultsFile(g, 6);
        compareWithTheirs(6);
    }

    /*
        ex(7) :-
        vle(p,1,true,false),
        run. % c1 -> off, c2 -> on
    */
    public static void ex7(Graph g) throws IOException {
        g.replaceLabel(new Vertex(1, Vertex.Sensor.p), Label.truee, Label.falsee);
        checkRules(g);
        ResultTester.generateResultsFile(g, 7);
        compareWithTheirs(7);
    }

    /*
        ex(8) :-
        vle(d,8,true,false),
        run. % c15 -> off, c16 -> on, c19 -> off
    */
    public static void ex8(Graph g) throws IOException {
        g.replaceLabel(new Vertex(8, Vertex.Sensor.d), Label.truee, Label.falsee);
        checkRules(g);
        ResultTester.generateResultsFile(g, 8);
        compareWithTheirs(8);
    }

    /*
        ex(9) :-
        vle(p,6,true,false),
        run. % c13 -> off, c14 -> on
    */
    public static void ex9(Graph g) throws IOException {
        g.replaceLabel(new Vertex(6, Vertex.Sensor.p), Label.truee, Label.falsee);
        checkRules(g);
        ResultTester.generateResultsFile(g, 9);
        compareWithTheirs(9);
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
