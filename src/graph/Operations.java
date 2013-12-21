package graph;

import org.apache.commons.collections4.ListUtils;
import parser.Edge;
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

    private List<Vertex> getVerticesInSegment(Graph graph, Vertex segment, Vertex.Sensor sensor) {
        throw new RuntimeException("Not implemented");
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

    private List<Vertex> getSegmentsWithoutLabel(Label excludedLabel) {
        throw new RuntimeException("Not implemented");
    }

    /*
    Propably useless.
     */
    private boolean segmentHasSensor(Vertex segment, Vertex.Sensor sensor, Label label, long id) {
        throw new RuntimeException("Not implemented");
    }

    private boolean segmentHasSensor(Vertex segment, Vertex.Sensor sensor, Label label, Edge.Flag flag) {
        throw new RuntimeException("Not implemented");
    }

    private boolean segmentHasSensor(Vertex segment, Vertex.Sensor sensor, Label label) {
        throw new RuntimeException("Not implemented");
    }

    private boolean segmentHasConfiguration(Vertex segment, Edge.Flag flag) {
        throw new RuntimeException("Not implemented");
    }

    private boolean segmentHas(Vertex segment, Vertex.Sensor sensor) {
        throw new RuntimeException("Not implemented");
    }

    private List<Vertex> segmentGetConfiguration(Vertex segment) {
        return null;
    }


    // vla
    private void configAddLabel(Vertex vertex, Label label) {

    }

    // vlr
    private void configRemoveLabel(Vertex vertex) {

    }

    private List<Vertex> segmentGetConfiguration(Vertex segment, Edge.Flag flag) {
        return null;
    }

    private List<Vertex> getSegmentsWithLabel(Label high) {
        return null;

    }

    private Iterable<? extends Vertex> getSegments() {
        return null;
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

    public boolean rule_1a1b() {
        boolean found = false;
        for(Vertex segment : getSegmentsWithoutLabel(Label.off)) {
            boolean foundSensor = segmentHasSensor(segment, Vertex.Sensor.k, Label.falsee);
            boolean foundConfig = segmentHas(segment, Vertex.Sensor.c);

            if(foundConfig && foundSensor) {
                found = true;
                addLabel(segment, Label.off);
            }
        }
        return found;
    }

    /*

    r('2a2b') :-
    v(k,L,true),            sensors1
    v(p,M,false),           sensors2
    e(p,M,s,J,in),           //FIXME
    \+ v(s,J,low),          segments -> v(s,J,in) - v(s,J,low)
    e(k,L,s,J),
    e(s,J,c,_,low),
    then,
    vla(s,J,low).

     */

    public boolean rule_2a2b() {
        boolean found = false;
        for(Vertex segment : getSegmentsWithoutLabel(Label.low)) {
            boolean foundSensor1 = segmentHasSensor(segment, Vertex.Sensor.k, Label.truee);
            boolean foundSensor2 = segmentHasSensor(segment, Vertex.Sensor.p, Label.falsee, Edge.Flag.in);
            boolean foundConfig = segmentHasConfiguration(segment, Edge.Flag.low);
            if(foundConfig && foundSensor1 && foundSensor2) {
                found = true;
                addLabel(segment, Label.low);
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

    public boolean rule_3a3b() {
        boolean found = false;
        for(Vertex segment : getSegmentsWithoutLabel(Label.high)) {
            boolean foundSensor1 = segmentHasSensor(segment, Vertex.Sensor.k, Label.truee);
            boolean foundSensor2 = segmentHasSensor(segment, Vertex.Sensor.p, Label.truee, Edge.Flag.in);
            boolean foundConfig = segmentHasConfiguration(segment, Edge.Flag.high);
            if(foundConfig && foundSensor1 && foundSensor2) {
                found = true;
                addLabel(segment, Label.high);
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

    public boolean rule_4a() {
        boolean found = false;
        for(Vertex segment : getSegmentsWithoutLabel(Label.high)) {
            boolean foundSensor1 = segmentHasSensor(segment, Vertex.Sensor.k, Label.truee);
            boolean foundSensor2 = segmentHasSensor(segment, Vertex.Sensor.d, Label.truee, Edge.Flag.dir_day);
            boolean foundSensor3 = segmentHasSensor(segment, Vertex.Sensor.h, Label.day, 1);
            boolean foundConfig = segmentHasConfiguration(segment, Edge.Flag.high);
            if(foundConfig && foundSensor1 && foundSensor2 && foundSensor3) {
                found = true;
                addLabel(segment, Label.high);
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

    public boolean rule_4b() {
        boolean found = false;
        for(Vertex segment : getSegmentsWithoutLabel(Label.high)) {
            boolean foundSensor1 = segmentHasSensor(segment, Vertex.Sensor.k, Label.truee);
            boolean foundSensor2 = segmentHasSensor(segment, Vertex.Sensor.d, Label.truee, Edge.Flag.dir_night);
            boolean foundSensor3 = segmentHasSensor(segment, Vertex.Sensor.h, Label.night, 1);
            boolean foundConfig = segmentHasConfiguration(segment, Edge.Flag.high);
            if(foundConfig && foundSensor1 && foundSensor2 && foundSensor3) {
                found = true;
                addLabel(segment, Label.high);
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
	e(s,J,c,_,high),     //FIXME
	then,
	vla(s,J,off).

     */

    public boolean rule_5a() {
        boolean found = false;
        for(Vertex segment : getSegmentsWithoutLabel(Label.off)) {
            boolean foundSensor1 = segmentHasSensor(segment, Vertex.Sensor.k, Label.truee);
            boolean foundSensor2 = segmentHasSensor(segment, Vertex.Sensor.d, Label.falsee, Edge.Flag.dir_day);
            boolean foundSensor3 = segmentHasSensor(segment, Vertex.Sensor.h, Label.day, 1);
            boolean foundConfig = segmentHasConfiguration(segment, Edge.Flag.high);
            if(foundConfig && foundSensor1 && foundSensor2 && foundSensor3) {
                found = true;
                addLabel(segment, Label.off);
            }
        }
        return found;
    }

/*
r('5b'):-
	v(k,L,true),
	v(h,1,night),
	e(d,I,s,J,dir_night), \+ v(s,J,off),     //FIXME
	v(d,I,false),
	e(k,L,s,J),
	e(h,1,s,J),
	e(s,J,c,_,high),         //FIXME
	then,
	vla(s,J,off).

            */

    public boolean rule_5b() {
        boolean found = false;
        for(Vertex segment : getSegmentsWithoutLabel(Label.off)) {
            boolean foundSensor1 = segmentHasSensor(segment, Vertex.Sensor.k, Label.truee);
            boolean foundSensor2 = segmentHasSensor(segment, Vertex.Sensor.d, Label.falsee, Edge.Flag.dir_night);
            boolean foundSensor3 = segmentHasSensor(segment, Vertex.Sensor.h, Label.night, 1);
            boolean foundConfig = segmentHasConfiguration(segment, Edge.Flag.high);
            if(foundConfig && foundSensor1 && foundSensor2 && foundSensor3) {
                found = true;
                addLabel(segment, Label.off);
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

    public boolean rule_6() {
        boolean found = false;
        for(Vertex segment : getSegmentsWithoutLabel(Label.high)) {
            if(segmentHasSensor(segment, Vertex.Sensor.h, Label.day, 1) &&
                    segmentHasSensor(segment, Vertex.Sensor.d, Label.truee, Edge.Flag.interior) &&
                    segmentHasConfiguration(segment, Edge.Flag.high))
            {
                found = true;
                addLabel(segment, Label.off);
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

    public boolean rule_7() {
        boolean found = false;
        for(Vertex segment : getSegmentsWithoutLabel(Label.off)) {
            if(segmentHasSensor(segment, Vertex.Sensor.h, Label.day, 1) &&
                    segmentHasSensor(segment, Vertex.Sensor.d, Label.falsee, Edge.Flag.interior) &&
                    segmentHasConfiguration(segment, Edge.Flag.high))
            {
                found = true;
                addLabel(segment, Label.off);
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

    public void profile_high() {
        for(Vertex segment : getSegmentsWithLabel(Label.high)) {

            clearLabels(segment);

            for(Vertex config : segmentGetConfiguration(segment, Edge.Flag.high)) {
                configRemoveLabel(config);
                configAddLabel(config, Label.on);
            }

            for(Vertex config : segmentGetConfiguration(segment, Edge.Flag.low)) {
                configRemoveLabel(config);
                configAddLabel(config, Label.off);
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

    public void profile_low() {
        List<Vertex> low = getSegmentsWithLabel(Label.low);
        List<Vertex> high = getSegmentsWithLabel(Label.high);
        List<Vertex> segments = ListUtils.subtract(low, high);

        for(Vertex segment : segments) {

            clearLabels(segment);

            for(Vertex config : segmentGetConfiguration(segment, Edge.Flag.low)) {
                configRemoveLabel(config);
                configAddLabel(config, Label.on);
            }

            for(Vertex config : segmentGetConfiguration(segment, Edge.Flag.low)) {
                configRemoveLabel(config);
                configAddLabel(config, Label.off);
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

    public void profile_off() {
        List<Vertex> off = getSegmentsWithLabel(Label.off);
        List<Vertex> low = getSegmentsWithLabel(Label.low);
        List<Vertex> high = getSegmentsWithLabel(Label.high);
        List<Vertex> segments = ListUtils.subtract(off, high);
        segments = ListUtils.subtract(segments, low);

        for(Vertex segment : segments) {

            clearLabels(segment);

            for(Vertex config : segmentGetConfiguration(segment)) {
                configRemoveLabel(config);
                configAddLabel(config, Label.off);
            }


        }
    }




}
