package implementations;

import graph.Graph;
import graph.Label;
import parser.Edge;
import parser.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public abstract class SimpleGraph implements Graph {

    //
    // LABELS
    //

    @Override
    public abstract ArrayList<Label> getLabels(Vertex v);

    /**
     * remove/retract vertex label (RETRACT)
     */
    @Override
    public abstract void removeLabel(Vertex vertex, Label label);

    /**
     * add/assert vertex label
     */
    @Override
    public abstract void addLabel(Vertex vertex, Label label);

    /**
     * change labels From To
     */
    @Override
    public abstract void replaceLabel(Vertex vertex, Label from, Label to);

    /**
     * clear selected labels (RETRACT ALL)
     */
    @Override
    public abstract void clearLabel(Vertex vertex, Label label);

    @Override
    public abstract void clearLabels(Vertex vertex);

    @Override
    public abstract void clearLabels();

    public abstract List<Vertex> getVertices();
    public abstract List<Edge> getEdges();

    //
    // VERTICES
    //

    @Override
    public List<Vertex> findVerticesWithSensor(Vertex.Sensor sensor) {
        List<Vertex> ret = new ArrayList<Vertex>();
        for(Vertex v : getVertices()) {
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
    public boolean segmentHasSensor1(Vertex segment, Vertex.Sensor sensor, Label label, long id) {
        for(Edge e : getEdges()) {
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
    public boolean segmentHasSensor2(Vertex segment, Vertex.Sensor sensor, Label label, Edge.Flag flag) {
        for(Edge e : getEdges()) {
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
    public boolean segmentHasSensor3(Vertex segment, Vertex.Sensor sensor, Label label) {
        for(Edge e : getEdges()) {
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
        for(Edge e : getEdges()) {
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
        for(Edge e : getEdges()) {
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
        for(Edge e : getEdges()) {
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
        for(Edge e : getEdges()) {
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
