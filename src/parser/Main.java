package parser;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Karol
 * Date: 17.12.13
 * Time: 21:34
 */
public class Main {

    public static void main(String[] args) throws IOException {
        final sqlGraph db = new sqlGraph();
        GraphParser parser = new GraphParser();
        parser.parse(new File("assets/100_gs_e.pl"), new GraphParser.Listener<Vertex>() {
                    @Override
                    public void onItem(Vertex item) {
                        db.insertVertex(item);
                        System.out.println(item);
                    }
                }, new GraphParser.Listener<Edge>() {
                    @Override
                    public void onItem(Edge item) {
                        db.insertEdge(item);
                        System.out.println(item);
                    }
                });

        db.commitInsertVertex();
        db.commitInsertEdge();
    }
}
