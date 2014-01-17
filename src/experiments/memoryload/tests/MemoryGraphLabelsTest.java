package experiments.memoryload.tests;

import experiments.memoryload.MemoryGraph;
import graph.Label;
import org.junit.Test;
import parser.Edge;
import parser.Vertex;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MemoryGraphLabelsTest {

    private MemoryGraph getSimpleGraph() {
        MemoryGraph graph = new MemoryGraph();

        Vertex p1 = new Vertex(1, Vertex.Sensor.p); graph.vertices.add(p1);
        Vertex p2 = new Vertex(2, Vertex.Sensor.p); graph.vertices.add(p2);
        Vertex p3 = new Vertex(3, Vertex.Sensor.p); graph.vertices.add(p3);
        Vertex p4 = new Vertex(4, Vertex.Sensor.p); graph.vertices.add(p4);

        Vertex s1 = new Vertex(1, Vertex.Sensor.s); graph.vertices.add(s1);
        Vertex s2 = new Vertex(2, Vertex.Sensor.s); graph.vertices.add(s2);

        Vertex c1 = new Vertex(1, Vertex.Sensor.c); graph.vertices.add(c1);
        Vertex c2 = new Vertex(2, Vertex.Sensor.c); graph.vertices.add(c2);
        Vertex c3 = new Vertex(3, Vertex.Sensor.c); graph.vertices.add(c3);

        Vertex l1 = new Vertex(1, Vertex.Sensor.l); graph.vertices.add(l1);
        Vertex l2 = new Vertex(2, Vertex.Sensor.l); graph.vertices.add(l2);

        graph.edges.add(new Edge(p1, s1));
        graph.edges.add(new Edge(p1, s1));
        graph.edges.add(new Edge(p3, s1));
        graph.edges.add(new Edge(p3, s2));
        graph.edges.add(new Edge(p4, s2));

        graph.edges.add(new Edge(s1, c1));
        graph.edges.add(new Edge(s1, c2));
        graph.edges.add(new Edge(s2, c3));

        graph.edges.add(new Edge(c1, l1));
        graph.edges.add(new Edge(c2, l2));
        graph.edges.add(new Edge(c3, l2));

        return graph;
    }


    @Test
    public void testRemoveLabel() throws Exception {
        MemoryGraph graph = getSimpleGraph();
        graph.addLabel(graph.vertices.get(0), Label.day);
        graph.addLabel(graph.vertices.get(0), Label.falsee);
        graph.addLabel(graph.vertices.get(0), Label.night);

        graph.removeLabel(graph.vertices.get(0), Label.night);

        assertFalse(
                graph.getLabels(graph.vertices.get(0)).contains(Label.night)
        );

        assertTrue(
                graph.getLabels(graph.vertices.get(0)).contains(Label.falsee)
        );

        assertTrue(
                graph.getLabels(graph.vertices.get(0)).contains(Label.day)
        );
    }

    @Test
    public void testRemoveLabel_multi() throws Exception {
        MemoryGraph graph = getSimpleGraph();
        graph.addLabel(graph.vertices.get(0), Label.day);
        graph.addLabel(graph.vertices.get(0), Label.day);
        graph.addLabel(graph.vertices.get(0), Label.day);

        graph.removeLabel(graph.vertices.get(0), Label.day);

        assertTrue(
                graph.getLabels(graph.vertices.get(0)).contains(Label.day)
        );

        assertTrue(
                graph.getLabels(graph.vertices.get(0)).size() == 2
        );
    }

    @Test
    public void testAddLabel() throws Exception {
        MemoryGraph graph = getSimpleGraph();
        graph.addLabel(graph.vertices.get(0), Label.day);

        assertTrue(
                graph.getLabels(graph.vertices.get(0)).contains(Label.day)
        );
    }

    @Test
    public void testReplaceLabel() throws Exception {
        MemoryGraph graph = getSimpleGraph();
        graph.addLabel(graph.vertices.get(0), Label.truee);
        graph.addLabel(graph.vertices.get(0), Label.falsee);
        graph.addLabel(graph.vertices.get(0), Label.night);

        graph.replaceLabel(graph.vertices.get(0), Label.night, Label.day);

        assertFalse(
                graph.getLabels(graph.vertices.get(0)).contains(Label.night)
        );

        assertTrue(
                graph.getLabels(graph.vertices.get(0)).contains(Label.falsee)
        );

        assertTrue(
                graph.getLabels(graph.vertices.get(0)).contains(Label.truee)
        );

        assertTrue(
                graph.getLabels(graph.vertices.get(0)).contains(Label.day)
        );
    }

    @Test
    public void testClearLabels() throws Exception {
        MemoryGraph graph = getSimpleGraph();
        graph.addLabel(graph.vertices.get(0), Label.truee);
        graph.addLabel(graph.vertices.get(0), Label.falsee);
        graph.addLabel(graph.vertices.get(0), Label.night);

        graph.clearLabels(graph.vertices.get(0));

        assertTrue(
                graph.getLabels(graph.vertices.get(0)).size() == 0
        );
    }

    @Test
    public void testClearLabel() throws Exception {
        MemoryGraph graph = getSimpleGraph();
        graph.addLabel(graph.vertices.get(0), Label.truee);
        graph.addLabel(graph.vertices.get(0), Label.truee);
        graph.addLabel(graph.vertices.get(0), Label.truee);

        graph.clearLabel(graph.vertices.get(0), Label.truee);

        assertTrue(
                graph.getLabels(graph.vertices.get(0)).size() == 0
        );
    }

    @Test
    public void testMultiLabels() throws Exception {
        MemoryGraph graph = getSimpleGraph();
        graph.addLabel(graph.vertices.get(0), Label.truee);
        graph.addLabel(graph.vertices.get(0), Label.truee);
        graph.addLabel(graph.vertices.get(0), Label.truee);

        assertTrue(
                graph.getLabels(graph.vertices.get(0)).size() == 3
        );
    }




}
