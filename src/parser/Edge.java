package parser;

/**
 * Created with IntelliJ IDEA.
 * User: Karol
 * Date: 17.12.13
 * Time: 21:51
 */
public class Edge {
    Vertex start;
    Vertex end;
    String flag;

    public Edge(Vertex start, Vertex end, String flag) {
        this.start = start;
        this.end = end;
        this.flag = flag;
    }

    public Edge(Vertex start, Vertex end) {
        this(start, end, null);
    }

    public String toString() {
        return "E: (" + start + ") -> (" + end + ")";
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
    }

    public String getFlag() {
        return flag;
    }
}
