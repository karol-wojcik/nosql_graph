package implementations;

import graph.Label;
import parser.Edge;
import parser.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemoryGraph extends SimpleGraph {

    public List<Vertex> vertices = new ArrayList<Vertex>();
    public List<Edge> edges = new ArrayList<Edge>();

    public HashMap<Vertex, ArrayList<Label>> vertexLabels = new HashMap<Vertex, ArrayList<Label>>();

    @Override
    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    @Override
    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    @Override
    public ArrayList<Label> getLabels(Vertex v) {
        ArrayList<Label> labels = vertexLabels.get(v);
        if(labels == null) {
            labels = new ArrayList<Label>();
            vertexLabels.put(v, labels);
        }
        return labels;
    }

    @Override
    public void removeLabel(Vertex vertex, Label label) {
        getLabels(vertex).remove(label);
    }

    @Override
    public void addLabel(Vertex vertex, Label label) {
        getLabels(vertex).add(label);
    }

    @Override
    public void replaceLabel(Vertex vertex, Label from, Label to) {
        removeLabel(vertex, from);
        addLabel(vertex, to);
    }

    @Override
    public void clearLabel(Vertex vertex, Label label) {
        while(true) {
            boolean found = getLabels(vertex).remove(label);
            if(!found) break;;
        }
    }

    @Override
    public void clearLabels(Vertex vertex) {
        vertexLabels.put(vertex, new ArrayList<Label>());
    }

    @Override
    public void clearLabels() {
        vertexLabels = new HashMap<Vertex, ArrayList<Label>>();
    }

    @Override
    public List<Vertex> getVertices() {
        return vertices;
    }

    @Override
    public List<Edge> getEdges() {
        return edges;
    }

}
