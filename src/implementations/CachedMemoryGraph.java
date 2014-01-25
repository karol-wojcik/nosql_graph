package implementations;

import graph.Label;
import implementations.MemoryGraph;
import parser.Edge;
import parser.Vertex;

import java.util.HashSet;
import java.util.List;

public class CachedMemoryGraph extends MemoryGraph {

    public static final int MAXID = 5000;

    Object[] findVerticesWithSensor = new Object[Vertex.Sensor.values().length];

    @Override
    public List<Vertex> findVerticesWithSensor(Vertex.Sensor sensor) {
        List<Vertex> cachedResult = (List<Vertex>)findVerticesWithSensor[sensor.ordinal()];
        if(cachedResult == null) {
            cachedResult = super.findVerticesWithSensor(sensor);
            findVerticesWithSensor[sensor.ordinal()] = cachedResult;
        }

        return cachedResult;
    }

    Boolean segmentHas[] = new Boolean[MAXID];

    @Override
    public boolean segmentHas(Vertex segment, Vertex.Sensor sensor) {
        if(sensor == Vertex.Sensor.c) {
            Boolean cachedResult = segmentHas[segment.id];
            if(cachedResult == null) {
                cachedResult = super.segmentHas(segment, sensor);
                segmentHas[sensor.ordinal()] = cachedResult;
            }
            return cachedResult;
        }

        return super.segmentHas(segment, sensor);
    }

    Object[] segmentGetConfiguration1 = new Object[MAXID];

    @Override
    public List<Vertex> segmentGetConfiguration(Vertex segment) {
        List<Vertex> cachedResult = (List<Vertex>)segmentGetConfiguration1[segment.id];
        if(cachedResult == null) {
            cachedResult = super.segmentGetConfiguration(segment);
            segmentGetConfiguration1[segment.id] = cachedResult;
        }

        return cachedResult;
    }

    Object[] segmentGetConfiguration2 = new Object[MAXID * Edge.Flag.values().length];

    @Override
    public List<Vertex> segmentGetConfiguration(Vertex segment, Edge.Flag flag) {
        List<Vertex> cachedResult = (List<Vertex>)segmentGetConfiguration2[segment.id + flag.ordinal() * MAXID];
        if(cachedResult == null) {
            cachedResult = super.segmentGetConfiguration(segment, flag);
            segmentGetConfiguration2[segment.id + flag.ordinal() * MAXID] = cachedResult;
        }
        return cachedResult;
    }

    HashSet<String> segmentHasSensor2 = new HashSet<String>();

    @Override
    public boolean segmentHasSensor2(Vertex segment, Vertex.Sensor sensor, Label label, Edge.Flag flag) {


        return super.segmentHasSensor2(segment, sensor, label, flag);
    }

    @Override
    public boolean segmentHasSensor1(Vertex segment, Vertex.Sensor sensor, Label label, long id) {
        return super.segmentHasSensor1(segment, sensor, label, id);
    }

    @Override
    public boolean segmentHasSensor3(Vertex segment, Vertex.Sensor sensor, Label label) {
        return super.segmentHasSensor3(segment, sensor, label);
    }
}
