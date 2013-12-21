package parser;


import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;
import graph.Graph;
import graph.Label;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ResultTester {

    public static Patch compare(File expected, Graph g) {
        List<String> our = new ArrayList<String>();
        for(Vertex segment : g.getSegments()) {
            for(Vertex config : g.segmentGetConfiguration(segment, Edge.Flag.high)) {
                Label label = g.configGetLabel(config);
                our.add("s" + segment.id + "-high-c" + config.id + " " + label);
            }
            for(Vertex config : g.segmentGetConfiguration(segment, Edge.Flag.low)) {
                Label label = g.configGetLabel(config);
                our.add("s" + segment.id + "-low-c" + config.id + " " + label);
            }
        }

        List<String> theirs = fileToLines(expected);
        return DiffUtils.diff(our, theirs);
    }

    public static void printResults(Patch patch) {
        for (Delta delta: patch.getDeltas()) {
            System.out.println(delta);
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
