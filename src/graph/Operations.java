package graph;

import parser.ResultTester;
import parser.Vertex;

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

    /*
        init.
    */

    public static void ex0(Graph g) throws IOException {
        init(g);
        checkRules(g);
    }

    /*
        ex(1) :-
        vle(k,1,false,true),
        run. % cJ -> on
    */

    public static void ex1(Graph g) throws IOException {
        g.replaceLabel(new Vertex(1, Vertex.Sensor.k), Label.falsee, Label.truee);
        checkRules(g);
    }

    /*
        ex(2) :-
        vle(p,1,false,true),
        run. % c2 -> off, c1 -> on
    */
    public static void ex2(Graph g) throws IOException {
        g.replaceLabel(new Vertex(1, Vertex.Sensor.p), Label.falsee, Label.truee);
        checkRules(g);
    }

    /*
        ex(3) :-
        vle(p,6,false,true),
        run. % c14 -> off, c13->on
    */
    public static void ex3(Graph g) throws IOException {
        g.replaceLabel(new Vertex(6, Vertex.Sensor.p), Label.falsee, Label.truee);
        checkRules(g);
    }

    /*
        ex(4) :-
        vle(d,1,false,true),
        run. % c16 -> off, c15 -> on
    */
    public static void ex4(Graph g) throws IOException {
        g.replaceLabel(new Vertex(1, Vertex.Sensor.d), Label.falsee, Label.truee);
        checkRules(g);
    }

    /*
        ex(5) :-
        vle(d,8,false,true),
        run. % nothing
    */
    public static void ex5(Graph g) throws IOException {
        g.replaceLabel(new Vertex(8, Vertex.Sensor.d), Label.falsee, Label.truee);
        checkRules(g);
    }

    /*
        ex(6) :-
        vle(d,1,true,false),
        run. %
    */
    public static void ex6(Graph g) throws IOException {
        g.replaceLabel(new Vertex(1, Vertex.Sensor.d), Label.truee, Label.falsee);
        checkRules(g);
    }

    /*
        ex(7) :-
        vle(p,1,true,false),
        run. % c1 -> off, c2 -> on
    */
    public static void ex7(Graph g) throws IOException {
        g.replaceLabel(new Vertex(1, Vertex.Sensor.p), Label.truee, Label.falsee);
        checkRules(g);
    }

    /*
        ex(8) :-
        vle(d,8,true,false),
        run. % c15 -> off, c16 -> on, c19 -> off
    */
    public static void ex8(Graph g) throws IOException {
        g.replaceLabel(new Vertex(8, Vertex.Sensor.d), Label.truee, Label.falsee);
        checkRules(g);
    }

    /*
        ex(9) :-
        vle(p,6,true,false),
        run. % c13 -> off, c14 -> on
    */
    public static void ex9(Graph g) throws IOException {
        g.replaceLabel(new Vertex(6, Vertex.Sensor.p), Label.truee, Label.falsee);
        checkRules(g);
    }
}
