package implementations;


import com.foundationdb.*;
import com.foundationdb.async.AsyncIterable;
import com.foundationdb.async.Function;
import com.foundationdb.tuple.Tuple;
import graph.Label;
import parser.Edge;
import parser.GraphParser;
import parser.Vertex;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FoundationGraph extends SimpleGraph {
    Database db;

    public FoundationGraph() {
        FDB fdb = FDB.selectAPIVersion(100);
        db = fdb.open();
    }

    @Override
    public void buildGraph(final List<Vertex> vertices, final List<Edge> edges) {
        db.run(new Function<Transaction, Void>() {
            public Void apply(final Transaction tr) {

                for(Vertex item : vertices) {
                    tr.set(Tuple.from("v",item.getSensor(), item.getId()).pack(), Tuple.from("").pack());
                }

                for(Edge item : edges) {
                    tr.set(Tuple.from("e",
                            item.getStart().getSensor(), item.getStart().getId(),
                            item.getEnd().getSensor(), item.getEnd().getId()
                    ).pack(), Tuple.from("").pack());
                }

                return null;
            }
        });
    }

    @Override
    public void addVertex(final Vertex vertex) {
        db.run(new Function<Transaction, Void>() {
            public Void apply(final Transaction tr) {
                tr.set(
                        Tuple.from("vertex", vertex.getSensor(), vertex.getId()
                ).pack(), Tuple.from("").pack());
                return null;
            }
        });
    }

    @Override
    public void addEdge(final Edge edge) {
        db.run(new Function<Transaction, Void>() {
            public Void apply(final Transaction tr) {
                tr.set(Tuple.from(
                        "edge",
                        edge.getStart().getSensor(), edge.getStart().getId(),
                        edge.getEnd().getSensor(), edge.getEnd().getId(),
                        edge.flag.toString()
                ).pack(), Tuple.from("").pack());
                return null;
            }
        });
    }

    @Override
    public ArrayList<Label> getLabels(Vertex vertex) {
        Transaction tr = db.createTransaction();
        AsyncIterable<KeyValue> iter = tr.getRange(Range.startsWith(
                Tuple.from("label", vertex.getSensor(), vertex.getId()).pack()
        ));
        ArrayList<Label> list = new ArrayList<Label>();
        for(KeyValue kv : iter) {
            Tuple tuple  = Tuple.fromBytes(kv.getKey());
            list.add(Label.valueOf(tuple.getString(3)));
        }
        return list;
    }

    @Override
    public void removeLabel(final Vertex vertex, final Label label) {
        db.run(new Function<Transaction, Void>() {
            public Void apply(final Transaction tr) {
                tr.clear(Tuple.from("label", vertex.getSensor(), vertex.getId(), label.toString()).pack());     //TODO multiset
                return null;
            }
        });
    }

    @Override
    public void addLabel(final Vertex vertex, final Label label) {
        db.run(new Function<Transaction, Void>() {
            public Void apply(final Transaction tr) {
                tr.set(Tuple.from(
                        "label", vertex.getSensor(), vertex.getId(), label.toString()   //TODO multiset!
                ).pack(), Tuple.from("").pack());
                return null;
            }
        });
    }

    @Override
    public void replaceLabel(Vertex vertex, Label from, Label to) {
        removeLabel(vertex, from);
        addLabel(vertex, to);
    }

    @Override
    public void clearLabel(Vertex vertex, Label label) {
        removeLabel(vertex, label); //TODO multiset
    }

    @Override
    public void clearLabels(final Vertex vertex) {
        db.run(new Function<Transaction, Void>() {
            public Void apply(final Transaction tr) {
                tr.clear(Range.startsWith(Tuple.from("label", vertex.getSensor(), vertex.getId()).pack()));
                return null;
            }
        });
    }

    @Override
    public void clearLabels() {
        db.run(new Function<Transaction, Void>() {
            public Void apply(final Transaction tr) {
                tr.clear(Range.startsWith(Tuple.from("label").pack()));
                return null;
            }
        });
    }

    @Override
    public List<Vertex> getVertices() {
        Transaction tr = db.createTransaction();
        AsyncIterable<KeyValue> iter = tr.getRange(Range.startsWith(
                Tuple.from("vertex").pack()
        ));
        ArrayList<Vertex> list = new ArrayList<Vertex>();
        for(KeyValue kv : iter) {
            Tuple tuple  = Tuple.fromBytes(kv.getKey());
            list.add(new Vertex((int)tuple.getLong(2), tuple.getString(1)));
        }
        return list;
    }

    /*
                        "edge",
                        edge.getStart().getSensor(), edge.getStart().getId(),
                        edge.getEnd().getSensor(), edge.getEnd().getId(),
                        edge.flag.toString()

     */

    @Override
    public List<Edge> getEdges() {
        Transaction tr = db.createTransaction();
        AsyncIterable<KeyValue> iter = tr.getRange(Range.startsWith(
                Tuple.from("edge").pack()
        ));
        ArrayList<Edge> list = new ArrayList<Edge>();
        for(KeyValue kv : iter) {
            Tuple tuple  = Tuple.fromBytes(kv.getKey());
            list.add(new Edge(
                    new Vertex((int)tuple.getLong(2), tuple.getString(1)),
                    new Vertex((int)tuple.getLong(4), tuple.getString(3)),
                    tuple.getString(5)
            ));
        }
        return list;
    }
}
