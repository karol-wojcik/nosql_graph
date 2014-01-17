package graph;

import org.apache.commons.collections4.ListUtils;
import parser.Edge;
import parser.Vertex;

import java.util.ArrayList;
import java.util.List;

public class Rules {

    /*

    r('1a1b') :-

    v(k,L,false),
    e(k,L,s,J),
    \+ v(s,J,off),
    e(s,J,c,_,_),
    then,
    vla(s,J,off).

     */

    public static boolean rule_1a1b(Graph g) {
        boolean found = false;
        for(Vertex segment : g.getSegmentsWithoutLabel(Label.off)) {
            boolean foundSensor = g.segmentHasSensor(segment, Vertex.Sensor.k, Label.falsee);
            boolean foundConfig = g.segmentHas(segment, Vertex.Sensor.c);

            if(foundConfig && foundSensor) {
                found = true;
                g.addLabel(segment, Label.off);
                System.out.println("R_1a1b segment: " + segment.toString());
            }
        }
        return found;
    }

    /*

    r('2a2b') :-
    v(k,L,true),
    v(p,M,false),
    e(p,M,s,J,in),
    \+ v(s,J,low),
    e(k,L,s,J),
    e(s,J,c,_,low),
    then,
    vla(s,J,low).

     */

    public static boolean rule_2a2b(Graph g) {
        boolean found = false;
        for(Vertex segment : g.getSegmentsWithoutLabel(Label.low)) {
            boolean foundSensor1 = g.segmentHasSensor(segment, Vertex.Sensor.k, Label.truee);
            boolean foundSensor2 = g.segmentHasSensor(segment, Vertex.Sensor.p, Label.falsee, Edge.Flag.in);
            boolean foundConfig = g.segmentHasConfiguration(segment, Edge.Flag.low);
            if(foundConfig && foundSensor1 && foundSensor2) {
                found = true;
                g.addLabel(segment, Label.low);
//                System.out.println("R_2a2b segment: " + segment.toString());
            }
        }
        return found;
    }

    /*

    r('3a3b') :-
	v(k,L,true),
	v(p,M,true),
	e(p,M,s,J,in),
	\+ v(s,J,high),
	e(k,L,s,J),
	e(s,J,c,_,high),
	then,
	vla(s,J,high).

     */

    public static boolean rule_3a3b(Graph g) {
        boolean found = false;
        for(Vertex segment : g.getSegmentsWithoutLabel(Label.high)) {
            boolean foundSensor1 = g.segmentHasSensor(segment, Vertex.Sensor.k, Label.truee);
            boolean foundSensor2 = g.segmentHasSensor(segment, Vertex.Sensor.p, Label.truee, Edge.Flag.in);
            boolean foundConfig = g.segmentHasConfiguration(segment, Edge.Flag.high);
            if(foundConfig && foundSensor1 && foundSensor2) {
                found = true;
                g.addLabel(segment, Label.high);
            }
        }
        return found;
    }

    /*

r('4a'):-
	v(k,L,true),
	v(h,1,day),
	v(d,I,true),
	e(d,I,s,J,dir_day), \+ v(s,J,high),
	e(k,L,s,J),
	e(h,1,s,J),
	e(s,J,c,_,high),
	then,
	vla(s,J,high).

     */

    public static boolean rule_4a(Graph g) {
        boolean found = false;
        for(Vertex segment : g.getSegmentsWithoutLabel(Label.high)) {
            boolean foundSensor1 = g.segmentHasSensor(segment, Vertex.Sensor.k, Label.truee);
            boolean foundSensor2 = g.segmentHasSensor(segment, Vertex.Sensor.d, Label.truee, Edge.Flag.dir_day);
            boolean foundSensor3 = g.segmentHasSensor(segment, Vertex.Sensor.h, Label.day, 1);
            boolean foundConfig = g.segmentHasConfiguration(segment, Edge.Flag.high);
            if(foundConfig && foundSensor1 && foundSensor2 && foundSensor3) {
                found = true;
                g.addLabel(segment, Label.high);
            }
        }
        return found;
    }


    /*

r('4b'):-
	v(h,1,night),
	v(k,L,true),
	v(d,I,true),
	e(d,I,s,J,dir_night), \+ v(s,J,high),
	e(k,L,s,J),
	e(h,1,s,J),
	e(s,J,c,_,high),
	then,
	vla(s,J,high).

     */

    public static boolean rule_4b(Graph g) {
        boolean found = false;
        for(Vertex segment : g.getSegmentsWithoutLabel(Label.high)) {
            boolean foundSensor1 = g.segmentHasSensor(segment, Vertex.Sensor.k, Label.truee);
            boolean foundSensor2 = g.segmentHasSensor(segment, Vertex.Sensor.d, Label.truee, Edge.Flag.dir_night);
            boolean foundSensor3 = g.segmentHasSensor(segment, Vertex.Sensor.h, Label.night, 1);
            boolean foundConfig = g.segmentHasConfiguration(segment, Edge.Flag.high);
            if(foundConfig && foundSensor1 && foundSensor2 && foundSensor3) {
                found = true;
                g.addLabel(segment, Label.high);
            }
        }
        return found;
    }


    /*

r('5a'):-
	v(k,L,true),
	v(h,1,day),
	e(d,I,s,J,dir_day),  \+ v(s,J,off)
	v(d,I,false),
	e(k,L,s,J),
	e(h,1,s,J),
	e(s,J,c,_,high),
	then,
	vla(s,J,off).

     */

    public static boolean rule_5a(Graph g) {
        boolean found = false;
        for(Vertex segment : g.getSegmentsWithoutLabel(Label.off)) {
            boolean foundSensor1 = g.segmentHasSensor(segment, Vertex.Sensor.k, Label.truee);
            boolean foundSensor2 = g.segmentHasSensor(segment, Vertex.Sensor.d, Label.falsee, Edge.Flag.dir_day);
            boolean foundSensor3 = g.segmentHasSensor(segment, Vertex.Sensor.h, Label.day, 1);
            boolean foundConfig = g.segmentHasConfiguration(segment, Edge.Flag.high);
            if(foundConfig && foundSensor1 && foundSensor2 && foundSensor3) {
                found = true;
                g.addLabel(segment, Label.off);
            }
        }
        return found;
    }

/*
r('5b'):-
	v(k,L,true),
	v(h,1,night),
	e(d,I,s,J,dir_night), \+ v(s,J,off),
	v(d,I,false),
	e(k,L,s,J),
	e(h,1,s,J),
	e(s,J,c,_,high),
	then,
	vla(s,J,off).

            */

    public static boolean rule_5b(Graph g) {
        boolean found = false;
        for(Vertex segment : g.getSegmentsWithoutLabel(Label.off)) {
            boolean foundSensor1 = g.segmentHasSensor(segment, Vertex.Sensor.k, Label.truee);
            boolean foundSensor2 = g.segmentHasSensor(segment, Vertex.Sensor.d, Label.falsee, Edge.Flag.dir_night);
            boolean foundSensor3 = g.segmentHasSensor(segment, Vertex.Sensor.h, Label.night, 1);
            boolean foundConfig = g.segmentHasConfiguration(segment, Edge.Flag.high);
            if(foundConfig && foundSensor1 && foundSensor2 && foundSensor3) {
                found = true;
                g.addLabel(segment, Label.off);
            }
        }
        return found;
    }


/*
r(6) :-
	v(h,1,day),
	v(d,I,true),
	e(d,I,s,J,interior),
	e(h,1,s,J),
	\+ v(s,J,high),
	e(s,J,c,_,high),
	then,
	vla(s,J,high).

            */

    public static boolean rule_6(Graph g) {
        boolean found = false;
        for(Vertex segment : g.getSegmentsWithoutLabel(Label.high)) {
            if(g.segmentHasSensor(segment, Vertex.Sensor.h, Label.day, 1) &&
                    g.segmentHasSensor(segment, Vertex.Sensor.d, Label.truee, Edge.Flag.interior) &&
                    g.segmentHasConfiguration(segment, Edge.Flag.high))
            {
                found = true;
                g.addLabel(segment, Label.high);
            }
        }
        return found;
    }

    /*

r(7) :-
	v(h,1,day),
	e(d,I,s,J,interior),  \+ v(s,J,off),
	v(d,I,false),
	e(s,J,c,_,high),
	e(h,1,s,J),
	then,
	vla(s,J,off).

            */

    public static boolean rule_7(Graph g) {
        boolean found = false;
        for(Vertex segment : g.getSegmentsWithoutLabel(Label.off)) {
            if(g.segmentHasSensor(segment, Vertex.Sensor.h, Label.day, 1) &&
                    g.segmentHasSensor(segment, Vertex.Sensor.d, Label.falsee, Edge.Flag.interior) &&
                    g.segmentHasConfiguration(segment, Edge.Flag.high))
            {
                found = true;
                g.addLabel(segment, Label.off);
            }
        }
        return found;
    }

    /*

p(high) :-
	v(s,I,high),
	e(s,I,c,K,high),
	then,
	vlc(s,I,_),
	forall(e(s,I,c,J,_),(vlr(c,J,_),vla(c,J,off))),
	vlr(c,K,_),
	vla(c,K,on).

     */

    public static void profile_high(Graph g) {
        List<Vertex> segmentConf;
        for(Vertex segment : g.getSegmentsWithLabel(Label.high)) {
            segmentConf = g.segmentGetConfiguration(segment, Edge.Flag.high);
            if(segmentConf.size() > 0) {

                g.clearLabels(segment);

                for(Vertex config : g.segmentGetConfiguration(segment)) {
                    g.configRemoveLabel(config);
                    g.configAddLabel(config, Label.off);
                }

                for(Vertex config : segmentConf) {
                    g.configRemoveLabel(config);
                    g.configAddLabel(config, Label.on);
                }

            }
        }
    }


        /*

p(low) :-
	v(s,I,low),
	\+ v(s,I,high),
	e(s,I,c,K,low),
	then,
	vlc(s,I,_),
	forall(e(s,I,c,J,_),(vlr(c,J,_),vla(c,J,off))),
	vlr(c,K,_),
	vla(c,K,on).

     */

    public static void profile_low(Graph g) {
        List<Vertex> low = g.getSegmentsWithLabel(Label.low);
        List<Vertex> high = g.getSegmentsWithLabel(Label.high);
        List<Vertex> segments = ListUtils.subtract(low, high);
        List<Vertex> segmentConf;

        for(Vertex segment : segments) {

            segmentConf = g.segmentGetConfiguration(segment, Edge.Flag.low);
            if(segmentConf.size() > 0) {

                g.clearLabels(segment);

                for(Vertex config : g.segmentGetConfiguration(segment)) {
                    g.configRemoveLabel(config);
                    g.configAddLabel(config, Label.off);
                }

                for(Vertex config : segmentConf) {
                    g.configRemoveLabel(config);
                    g.configAddLabel(config, Label.on);
                }
            }
        }
    }

        /*

p(off) :-
	v(s,I,off),
	\+ v(s,I,high),
	\+ v(s,I,low),
	then,
	vlc(s,I,_),
	forall(e(s,I,c,J,_),(vlr(c,J,_),vla(c,J,off))).


     */

    public static void profile_off(Graph g) {
        List<Vertex> off = g.getSegmentsWithLabel(Label.off);
        List<Vertex> low = g.getSegmentsWithLabel(Label.low);
        List<Vertex> high = g.getSegmentsWithLabel(Label.high);
        List<Vertex> segments = ListUtils.subtract(off, high);
        segments = ListUtils.subtract(segments, low);

        for(Vertex segment : segments) {

            g.clearLabels(segment);

            for(Vertex config : g.segmentGetConfiguration(segment)) {
                g.configRemoveLabel(config);
                g.configAddLabel(config, Label.off);
            }


        }
    }

}
