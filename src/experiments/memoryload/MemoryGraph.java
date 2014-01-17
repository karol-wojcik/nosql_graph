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

    //
    // LABELS
    //

    public ArrayList<Label> getLabels(Vertex v) {
        ArrayList<Label> labels = vertexLabels.get(v);
        if(labels == null) {
            labels = new ArrayList<Label>();
            vertexLabels.put(v, labels);
        }
        return labels;
    }

    /**
     * remove/retract vertex label (RETRACT)
     */
    @Override
    public void removeLabel(Vertex vertex, Label label) {
        getLabels(vertex).remove(label);
    }

    /**
     * add/assert vertex label
     */
    @Override
    public void addLabel(Vertex vertex, Label label) {
        getLabels(vertex).add(label);
    }

    /**
     * change labels From To
     */
    @Override
    public void replaceLabel(Vertex vertex, Label from, Label to) {
        removeLabel(vertex, from);
        addLabel(vertex, to);
    }

    /**
     * clear selected labels (RETRACT ALL)
     */
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
    public void configAddLabel(Vertex vertex, Label label) {
        addLabel(vertex, label);
    }

    @Override
    public void configRemoveLabel(Vertex vertex) {
        clearLabels(vertex);
    }

    //
    // VERTICES
    //

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
        List<Vertex> ret = new ArrayList<Vertex>();
        for(Edge e : edges) {
            if(e.getStart().equals(segment)) {
                if (e.getEnd().sensor == Vertex.Sensor.c) {
                    ret.add(e.getEnd());
                }
            }
            if(e.getEnd().equals(segment)) {
                if (e.getStart().sensor == Vertex.Sensor.c) {
                    ret.add(e.getStart());
                }
            }
        }
        return ret;
    }

    @Override
    public List<Vertex> segmentGetConfiguration(Vertex segment, Edge.Flag flag) {
        List<Vertex> ret = new ArrayList<Vertex>();
        for(Edge e : edges) {
            if(e.getStart().equals(segment)) {
                if (e.flag == flag) {
                    ret.add(e.getEnd());
                }
            }
            if(e.getEnd().equals(segment)) {
                if (e.flag == flag) {
                    ret.add(e.getStart());
                }
            }
        }
        return ret;
    }

    @Override
    public List<Vertex> getSegmentsWithLabel(Label label) {
        List<Vertex> ret = new ArrayList<Vertex>();
        List<Vertex> segments = findVerticesWithSensor(Vertex.Sensor.s);
        for(Vertex v : segments) {
            if(getLabels(v).contains(label)) {
                ret.add(v);
            }
        }
        return ret;
    }

    @Override
    public List<Vertex> getSegments() {
        return findVerticesWithSensor(Vertex.Sensor.s);
    }

    @Override
    public Label configGetLabel(Vertex config) {
        return getLabels(config).get(0);
    }
}
