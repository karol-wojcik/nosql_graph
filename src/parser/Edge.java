package parser;

/**
 * Created with IntelliJ IDEA.
 * User: Karol
 * Date: 17.12.13
 * Time: 21:51
 */
public class Edge {
    public Vertex start;
    public Vertex end;
    Flag flag = null;

    public Edge(Vertex start, Vertex end, String flag) {
        this.start = start;
        this.end = end;
        if(flag != null) {
            this.flag = Flag.valueOf(flag);
        }
    }

    public Edge(Vertex start, Vertex end) {
        this(start, end, null);
    }

    public String toString() {
        return "E: (" + start + ") -> (" + end + ") " + "f: " + flag;
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
    }

    public String getFlag() {
        if(flag != null) {
            return flag.toString();
        }
        return null;
    }

    public static enum Flag {
        interior, par21, par20, in, dir_night, dir_day, par9, par18, par19, par4, par14, par3, par15, par2, par16, par1, high, par17, par8, low, par10, par7, par11, par6, par12, par5, par13
    }
}
