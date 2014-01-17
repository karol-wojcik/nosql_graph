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

        Operations.ex0(graph);
        ResultTester.generateResultsFile(graph, 0);
        compareWithTheirs(0, false);

        Operations.ex1(graph);
        ResultTester.generateResultsFile(graph, 1);
        compareWithTheirs(1, false);

        Operations.ex2(graph);
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
