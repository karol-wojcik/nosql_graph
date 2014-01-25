package experiments;

import experiments.tools.MicroBenchmarkRunner;
import graph.Graph;
import implementations.MemoryGraph;
import parser.Edge;
import parser.GraphParser;
import parser.Vertex;

import java.io.File;
import java.io.IOException;

public class MemorygraphLoadTest {

    public void readAndAddToList() {

        final Graph container = new MemoryGraph();

        GraphParser parser = new GraphParser();
        parser.parse(new File("assets/100_gs_e.pl"), new GraphParser.Listener<Vertex>() {
                    @Override
                    public void onItem(Vertex item) {
                        container.addVertex(item);
//                        System.out.println(item);
                    }
                }, new GraphParser.Listener<Edge>() {
                    @Override
                    public void onItem(Edge item) {
                        container.addEdge(item);
                    }
                });
    }

    public static void main(String[] args) throws IOException {

        MicroBenchmarkRunner runner = new MicroBenchmarkRunner();
        final MemorygraphLoadTest memoryLoad = new MemorygraphLoadTest();

        MicroBenchmarkRunner.ExecutionLog result = runner.run(new Runnable() {
            @Override
            public void run() {
                memoryLoad.readAndAddToList();
            }
        });

        System.out.println(result);
    }




}
