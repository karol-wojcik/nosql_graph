package experiments.memoryload;

import graph.Graph;
import graph.Label;
import parser.Edge;
import parser.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MemoryGraph implements Graph {

    public List<Vertex> vertices = new ArrayList<Vertex>();
    public List<Edge> edges = new ArrayList<Edge>();

    public HashMap<Vertex, ArrayList<Label>> vertexLabels = new HashMap<Vertex, ArrayList<Label>>();


    private ArrayList<Label> getLabels(Vertex v) {
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
        //FIXME should work if From not found ?
        ArrayList<Label> labels = getLabels(vertex);
        if(labels != null) {
            if(labels.contains(from)) {
                labels.remove(from);
                labels.add(to);
            }
        }
    }

    @Override
    public void clearLabels(Vertex vertex, Label label) {
        //FIXME is this different then removeLabel() ?
        getLabels(vertex).remove(label);
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
    public void configAddLabel(Vertex vertex, Label label) {
        addLabel(vertex, label);
    }

    @Override
    public void configRemoveLabel(Vertex vertex) {
        clearLabels(vertex);
    }

    @Override
    public List<Vertex> findVerticesWithSensor(Vertex.Sensor sensor) {
        List<Vertex> ret = new ArrayList<Vertex>();
        for(Vertex v : vertices) {
            if(v.sensor == sensor) {
                ret.add(v);
            }
        }
        return ret;
    }

    @Override
    public List<Vertex> getSegmentsWithoutLabel(Label excludedLabel) {
        List<Vertex> ret = new ArrayList<Vertex>();
        List<Vertex> segments = findVerticesWithSensor(Vertex.Sensor.s);
        for(Vertex v : segments) {
            if(!getLabels(v).contains(excludedLabel)) {
                ret.add(v);
            }
        }
        return ret;
    }


    @Override
    public boolean segmentHasSensor(Vertex segment, Vertex.Sensor sensor, Label label, long id) {
        for(Edge e : edges) {
            if(e.getStart().equals(segment)) {
                if(e.getEnd().sensor == sensor) {
                    if(getLabels(e.getEnd()).contains(label)) {
                        if(e.getEnd().id == id) {
                            return true;
                        }
                    }
                }
            }
            if(e.getEnd().equals(segment)) {
                if(e.getStart().sensor == sensor) {
                    if(getLabels(e.getStart()).contains(label)) {
                        if(e.getStart().id == id) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean segmentHasSensor(Vertex segment, Vertex.Sensor sensor, Label label, Edge.Flag flag) {
        for(Edge e : edges) {
            if(e.getStart().equals(segment)) {
                if(e.getEnd().sensor == sensor) {
                    if(getLabels(e.getEnd()).contains(label)) {
                        if(e.flag == flag) {
                            return true;
                        }
                    }
                }
            }
            if(e.getEnd().equals(segment)) {
                if(e.getStart().sensor == sensor) {
                    if(getLabels(e.getStart()).contains(label)) {
                        if(e.flag == flag) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean segmentHasSensor(Vertex segment, Vertex.Sensor sensor, Label label) {
        for(Edge e : edges) {
            if(e.getStart().equals(segment)) {
                if(e.getEnd().sensor == sensor) {
                    if(getLabels(e.getEnd()).contains(label)) {
                        return true;
                    }
                }
            }
            if(e.getEnd().equals(segment)) {
                if(e.getStart().sensor == sensor) {
                    if(getLabels(e.getStart()).contains(label)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean segmentHas(Vertex segment, Vertex.Sensor sensor) {
        for(Edge e : edges) {
            if(e.getStart().equals(segment)) {
                if(e.getEnd().sensor == sensor) {
                    return true;
                }
            }
            if(e.getEnd().equals(segment)) {
                if(e.getStart().sensor == sensor) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean segmentHasConfiguration(Vertex segment, Edge.Flag flag) {
        for(Edge e : edges) {
            if(e.getStart().equals(segment)) {
                if(e.flag == flag) {
                    return true;
                }
            }
            if(e.getEnd().equals(segment)) {
                if(e.flag == flag) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Vertex> segmentGetConfiguration(Vertex segment) {
        return null;
    }

    @Override
    public List<Vertex> segmentGetConfiguration(Vertex segment, Edge.Flag flag) {
        return null;
    }

    @Override
    public List<Vertex> getSegmentsWithLabel(Label high) {
        return null;
    }

    @Override
    public List<Vertex> getSegments() {
        return null;
    }

    @Override
    public Label configGetLabel(Vertex config) {
        return null;
    }
}
