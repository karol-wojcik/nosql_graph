package experiments;

import graph.Graph;
import graph.Label;
import graph.Operations;
import implementations.CachedMemoryGraph;
import implementations.FoundationGraph;
import implementations.MemoryGraph;
import parser.Edge;
import parser.GraphParser;
import parser.ResultTester;
import parser.Vertex;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Karol
 * Date: 30.12.13
 * Time: 11:44
 */
public class FullDatasetTest {
    public static void main(String[] args) throws IOException {


//        while(true) {

                final Graph graph = new FoundationGraph();
//            final Graph graph = new CachedMemoryGraph();
            final ArrayList<Vertex> vertices = new ArrayList<Vertex>();
            final ArrayList<Edge> edges = new ArrayList<Edge>();

            GraphParser parser = new GraphParser();
            parser.parse(new File("assets/1_gs_e.pl"),
                    new GraphParser.Listener<Vertex>() {
                        @Override
                        public void onItem(Vertex item) {
                            vertices.add(item);
                        }
                    }, new GraphParser.Listener<Edge>() {
                        @Override
                        public void onItem(Edge item) {
                            edges.add(item);
                        }
                    });

            graph.buildGraph(vertices, edges);


            long startTime;
            long results[] = new long[10];

            for(int i = 0; i < 10; i++) {
                System.out.println("Running test no. " + i);

                startTime = System.currentTimeMillis();
                runOperation(i, graph);
                results[i] = System.currentTimeMillis() - startTime;
                System.out.println("Running time: " + results[i]);
                ResultTester.generateResultsFile(graph, i);
                boolean passed = compareWithTheirs(i, true);

                if(!passed) {
                    System.out.println("!!!TEST FAILED!!!");
                    break;
                }
            }

            System.out.println("Times: ");
            long sum = 0;
            for(long time : results) {
                System.out.print(time + " ");
                sum += time;
            }

            System.out.println(" ");
            System.out.println("Sum: " + sum);

//        }
    }

    private static void runOperation(int nr, Graph graph) throws IOException {
        if(nr == 0) {
            Operations.ex0(graph);
        } else if(nr == 1) {
            Operations.ex1(graph);
        } else if(nr == 2) {
            Operations.ex2(graph);
        } else if(nr == 3) {
            Operations.ex3(graph);
        } else if(nr == 4) {
            Operations.ex4(graph);
        } else if(nr == 5) {
            Operations.ex5(graph);
        } else if(nr == 6) {
            Operations.ex6(graph);
        } else if(nr == 7) {
            Operations.ex7(graph);
        } else if(nr == 8) {
            Operations.ex8(graph);
        } else if(nr == 9) {
            Operations.ex9(graph);
        }
    }

    private static boolean compareWithTheirs(Integer experiment, boolean printDiff) {
        boolean passed = ResultTester.printResults(
                ResultTester.compare(
                        new File("assets/results/self_gen_1/" + experiment + ".txt"),
                        new File("assets/results/ours/" + experiment + ".txt")
                ), printDiff
        );
        return passed;
    }
}
