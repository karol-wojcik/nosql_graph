package parser;


import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;
import graph.Graph;
import graph.Label;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ResultTester {

    public static void generateResultsFile(Graph g, Integer fileName) throws IOException {
        File file = new File("assets/results/ours/" + fileName + ".txt");

        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        for(Vertex segment : g.getSegments()) {
            for(Vertex config : g.segmentGetConfiguration(segment, Edge.Flag.high)) {
                Label label = g.configGetLabel(config);
                bw.write("s" + segment.id + "-high-c" + config.id + " " + label);
                bw.newLine();
            }
            for(Vertex config : g.segmentGetConfiguration(segment, Edge.Flag.low)) {
                Label label = g.configGetLabel(config);
                bw.write("s" + segment.id + "-low-c" + config.id + " " + label);
                bw.newLine();
            }
        }
        bw.close();
    }

    public static Patch compare(File expected, File ours) {
        List<String> our = fileToLines(ours);
        List<String> theirs = fileToLines(expected);
        return DiffUtils.diff(our, theirs);
    }

    public static void printResults(Patch patch, boolean printDiff) {
        List<Delta> deltas = patch.getDeltas();
        System.out.println(deltas.size() + " differences");
        if(printDiff) {
            for (Delta delta: deltas) {
                System.out.println(delta);
            }
        }
    }

    private static List<String> fileToLines(File file) {
        List<String> lines = new LinkedList<String>();
        String line = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }
}
