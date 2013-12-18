package parser;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: Karol
 * Date: 17.12.13
 * Time: 22:29
 */
public class sqlGraph {
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:db/sql/graph.db";
    public static final String VERTEX_INSERT = "insert into vertex (vertex_id, sensor_kind) values ";
    public static final String EDGE_INSERT = "insert into edge (s_v_id, s_v_kind, e_v_id, e_v_kind, flag) values ";

    private Connection conn;
    private Statement stat;

    private String vertexInsert = VERTEX_INSERT;
    private Integer vertexNo = 0;
    private String edgeInsert = EDGE_INSERT;
    private Integer edgeNo = 0;

    public sqlGraph() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("[open] Connection error");
            e.printStackTrace();
        }

        createTables();
    }

    public boolean insertVertex(Vertex v) {
        if (vertexNo > 0) {
            vertexInsert += ", ";
        }
        vertexInsert += " (" + v.getId() + ", '" + v.getSensor() + "') " ;
        vertexNo++;

        if (vertexNo > 450) {
            commitInsertVertex();
        }

        return true;
    }

    public boolean commitInsertVertex() {
        try {
            if (vertexNo > 0) {
                PreparedStatement prepStmt = conn.prepareStatement(vertexInsert);
                prepStmt.execute();
                vertexInsert = VERTEX_INSERT;
                vertexNo = 0;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage() + " SQL: " + vertexInsert);
            return false;
        }
        return true;
    }

    public boolean insertEdge(Edge e) {
        if (edgeNo > 0) {
            edgeInsert += ", ";
        }
        String flag = null;
        if (e.getFlag() != null) {
            flag = "'" + e.getFlag() + "'";
        }
        edgeInsert += " (" + e.getStart().getId() + ", '" + e.getStart().getSensor() + "', " +
                 + e.getEnd().getId() + ", '" + e.getEnd().getSensor() + "', " + flag + " ) " ;
        edgeNo++;

        if (edgeNo > 450) {
            commitInsertEdge();
        }

        return true;
    }

    public boolean commitInsertEdge() {
        try {
            if (edgeNo > 0) {
                PreparedStatement prepStmt = conn.prepareStatement(edgeInsert);
                prepStmt.execute();
                edgeInsert = EDGE_INSERT;
                edgeNo = 0;
            }
        } catch (SQLException exc) {
            System.err.println(exc.getMessage() + " SQL: " + edgeInsert);
            return false;
        }
        return true;
    }

    public boolean createTables()  {
        String dropVertex = "DROP TABLE IF EXISTS vertex;";
        String dropEdge = "DROP TABLE IF EXISTS edge;";
        String createVertex = "CREATE TABLE vertex (" +
                "vertex_id INTEGER, " +
                "sensor_kind varchar(2) " +
               ")";
        String createEdge = "CREATE TABLE edge (" +
                "s_v_id INTEGER, " +
                "s_v_kind varchar(2), " +
                "e_v_id INTEGER, " +
                "e_v_kind varchar(2), " +
                "flag varchar(255) " +
               ")";
        try {
            stat.execute(dropVertex);
            stat.execute(dropEdge);
            stat.execute(createVertex);
            stat.execute(createEdge);
        } catch (SQLException e) {
            System.err.println("Unable to create tables");
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("[close] Connection error");
            e.printStackTrace();
        }
    }
}
