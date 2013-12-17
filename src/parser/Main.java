package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Karol
 * Date: 17.12.13
 * Time: 21:34
 */
public class Main {

    public static void main(String[] args) {
        BufferedReader br = null;
        sqlGraph db = new sqlGraph();
        try {
            String sCurrentLine;
            String firstChar;
            String[] splitted;
            br = new BufferedReader(new FileReader("assets/100_gs_e.pl"));

            while ((sCurrentLine = br.readLine()) != null) {
                if (sCurrentLine.length() < 1)
                    continue;

                System.out.println(sCurrentLine);
                firstChar = sCurrentLine.substring(0, 1);
                if (firstChar.equals("v") ) {
                    String sensor;
                    Integer id;
                    sCurrentLine = getParenthesisContent(sCurrentLine);
                    if (sCurrentLine.length() == 0) {
                        continue;
                    }

                    splitted = sCurrentLine.split(", ");
                    if (splitted.length > 0 && splitted[0] != null) {
                        sensor = splitted[0];
                        if (splitted.length > 1 && splitted[1] != null) {
                            id = Integer.parseInt(splitted[1]);
                            db.insertVertex(new Vertex(id, sensor));
                        }
                    }
                } else if (firstChar.equals("e")) {
                    String sensor;
                    Vertex sV;
                    Vertex eV;
                    String kind = null;
                    sCurrentLine = getParenthesisContent(sCurrentLine);
                    if (sCurrentLine.length() == 0) {
                        continue;
                    }

                    splitted = sCurrentLine.split(", ");
                    if (splitted.length > 0 && splitted[0] != null) {
                        sensor = splitted[0];
                        if (splitted.length > 1 && splitted[1] != null) {
                            sV = new Vertex(Integer.parseInt(splitted[1]), sensor);
                        } else { continue; }
                    } else { continue; }

                    if (splitted.length > 2 && splitted[2] != null) {
                        sensor = splitted[2];
                        if (splitted.length > 3 && splitted[3] != null) {
                            eV = new Vertex(Integer.parseInt(splitted[3]), sensor);
                        } else { continue; }
                    } else { continue; }

                    if (splitted.length > 4 && splitted[4] != null) {
                        kind = splitted[4];
                    }

                    db.insertEdge(new Edge(sV, eV, kind));
                }
            }

            db.commitInsertVertex();
            db.commitInsertEdge();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static String getParenthesisContent(String line) {
        String pattern = "\\((.*?)\\)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern, Pattern.DOTALL);

        // Now create matcher object.
        Matcher m = r.matcher(line);
        if (m.find( )) {
            return m.group(1);
        } else {
            return null;
        }
    }
}
