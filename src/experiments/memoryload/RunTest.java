package experiments.memoryload;

import graph.Operations;
import graph.Rules;
import parser.Edge;
import parser.GraphParser;
import parser.Vertex;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Karol
 * Date: 30.12.13
 * Time: 11:44
 */
public class RunTest {
    public static void main(String[] args) throws IOException {
        final MemoryGraph graph = new MemoryGraph();

        GraphParser parser = new GraphParser();
        parser.parse(new File("assets/100_gs_e.pl"), new GraphParser.Listener<Vertex>() {
                    @Override
                    public void onItem(Vertex item) {
                graph.vertices.add(item);
            }
        }, new GraphParser.Listener<Edge>() {
            @Override
            public void onItem(Edge item) {
                graph.edges.add(item);
            }
        });

        Operations.ex0(graph);
        Operations.ex1(graph);
    }
}
