package graph;

import parser.Graph;
import parser.Vertex;

import java.util.List;

public class Operations {

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

    public List<Vertex> findVerticesWithSensor(Graph graph, Vertex.Sensor sensor) {
        throw new RuntimeException("Not implemented");
    }

    public void init(Graph graph) {
        clearLabels(graph);
        addLabel(new Vertex(1, Vertex.Sensor.k), Label.falsee);
        addLabel(new Vertex(1, Vertex.Sensor.h), Label.day);
        for(Vertex v : findVerticesWithSensor(graph, Vertex.Sensor.c)) {
            addLabel(v, Label.off);
        }
        //... TODO
    }


}
