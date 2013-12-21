package experiments.memoryload;

import experiments.MicroBenchmarkRunner;
import parser.Edge;
import parser.GraphParser;
import parser.Vertex;

import java.io.File;
import java.io.IOException;

public class Starter {

    public void readAndAddToList() {

        final MemoryGraph container = new MemoryGraph();

        GraphParser parser = new GraphParser();
        parser.parse(new File("assets/100_gs_e.pl"), new GraphParser.Listener<Vertex>() {
                    @Override
                    public void onItem(Vertex item) {
                        container.vertices.add(item);
//                        System.out.println(item);
                    }
                }, new GraphParser.Listener<Edge>() {
                    @Override
                    public void onItem(Edge item) {
                        container.edges.add(item);
                    }
                });
    }

    public static void main(String[] args) throws IOException {

        MicroBenchmarkRunner runner = new MicroBenchmarkRunner();
        final Starter memoryLoad = new Starter();

        MicroBenchmarkRunner.ExecutionLog result = runner.run(new Runnable() {
            @Override
            public void run() {
                memoryLoad.readAndAddToList();
            }
        });

        System.out.println(result);
    }




}
