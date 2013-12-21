package graph;

import parser.Edge;
import parser.Vertex;

import java.util.List;

public interface Graph {

    // vlr
    public void removeLabel(Vertex vertex, Label label);

    // vla
    public void addLabel(Vertex vertex, Label label);

    // vle
    public void replaceLabel(Vertex vertex, Label from, Label to);

    // vlc (ALL of this kind)
    public void clearLabels(Vertex vertex, Label label);

    // vlc (ALL in VERTEX)
    public void clearLabels(Vertex vertex);

    // vlc (ALL)
    public void clearLabels();

    // vla
    public void configAddLabel(Vertex vertex, Label label);

    // vlr
    public void configRemoveLabel(Vertex vertex);

    public List<Vertex> findVerticesWithSensor(Vertex.Sensor sensor);

    public List<Vertex> getSegmentsWithoutLabel(Label excludedLabel);

    public boolean segmentHasSensor(Vertex segment, Vertex.Sensor sensor, Label label, long id);

    public boolean segmentHasSensor(Vertex segment, Vertex.Sensor sensor, Label label, Edge.Flag flag);

    public boolean segmentHasSensor(Vertex segment, Vertex.Sensor sensor, Label label);

    public boolean segmentHas(Vertex segment, Vertex.Sensor sensor);

    public boolean segmentHasConfiguration(Vertex segment, Edge.Flag flag);

    public List<Vertex> segmentGetConfiguration(Vertex segment);

    public List<Vertex> segmentGetConfiguration(Vertex segment, Edge.Flag flag);

    public List<Vertex> getSegmentsWithLabel(Label high);

    public  List<Vertex> getSegments();

    public Label configGetLabel(Vertex config);
}
