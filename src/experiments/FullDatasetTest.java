package experiments;

import graph.Graph;
import graph.Operations;
import implementations.MemoryGraph;
import parser.Edge;
import parser.GraphParser;
import parser.ResultTester;
import parser.Vertex;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Karol
 * Date: 30.12.13
 * Time: 11:44
 */
public class FullDatasetTest {
    public static void main(String[] args) throws IOException {

        while(true) {

            final Graph graph = new MemoryGraph();
//        final CachedMemoryGraph graph = new CachedMemoryGraph();

            GraphParser parser = new GraphParser();
            parser.parse(new File("assets/100_gs_e.pl"), new GraphParser.Listener<Vertex>() {
                        @Override
                        public void onItem(Vertex item) {
                            graph.addVertex(item);
                        }
                    }, new GraphParser.Listener<Edge>() {
                        @Override
                        public void onItem(Edge item) {
                            graph.addEdge(item);
                        }
                    });

            long startTime;

            startTime = System.currentTimeMillis();
            Operations.ex0(graph);
            System.out.println("Running time: " + (System.currentTimeMillis() - startTime));
            ResultTester.generateResultsFile(graph, 0);
            compareWithTheirs(0, false);

            startTime = System.currentTimeMillis();
            Operations.ex1(graph);
            System.out.println("Running time: " + (System.currentTimeMillis() - startTime));
            ResultTester.generateResultsFile(graph, 1);
            compareWithTheirs(1, false);

            startTime = System.currentTimeMillis();
            Operations.ex2(graph);
            System.out.println("Running time: " + (System.currentTimeMillis() - startTime));
            ResultTester.generateResultsFile(graph, 2);
            compareWithTheirs(2, false);

            Operations.ex3(graph);
            ResultTester.generateResultsFile(graph, 3);
            compareWithTheirs(3, false);

            Operations.ex4(graph);
            ResultTester.generateResultsFile(graph, 4);
            compareWithTheirs(4, false);

            Operations.ex5(graph);
            ResultTester.generateResultsFile(graph, 5);
            compareWithTheirs(5, false);

            Operations.ex6(graph);
            ResultTester.generateResultsFile(graph, 6);
            compareWithTheirs(6, false);

            Operations.ex7(graph);
            ResultTester.generateResultsFile(graph, 7);
            compareWithTheirs(7, false);

            Operations.ex8(graph);
            ResultTester.generateResultsFile(graph, 8);
            compareWithTheirs(8, false);

            Operations.ex9(graph);
            ResultTester.generateResultsFile(graph, 9);
            compareWithTheirs(9, false);
        }


    }

    private static void compareWithTheirs(Integer experiment, boolean printDiff) {
        System.out.print("Results of Ex. " + experiment + ": ");
        ResultTester.printResults(
                ResultTester.compare(
                        new File("assets/results/theirs/" + experiment + ".txt"),
                        new File("assets/results/ours/" + experiment + ".txt")
                ), printDiff
        );
    }
}
