package experiments.memoryload;

import graph.Operations;
import graph.Rules;
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

        System.out.println("Running ex0");
        Operations.ex0(graph);

        ResultTester.generateResultsFile(graph, 0);
        compareWithTheirs(0, false);

        System.out.println("Running ex1");
        Operations.ex1(graph);

        ResultTester.generateResultsFile(graph, 1);
        compareWithTheirs(1, false);

        System.out.println("Running ex2");
        Operations.ex2(graph);

        ResultTester.generateResultsFile(graph, 2);
        compareWithTheirs(2, false);
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
