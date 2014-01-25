package experiments;

import com.foundationdb.Database;
import com.foundationdb.FDB;
import com.foundationdb.KeyValue;
import com.foundationdb.Transaction;
import com.foundationdb.async.AsyncIterable;
import com.foundationdb.async.Function;
import com.foundationdb.tuple.Tuple;
import parser.Edge;
import parser.GraphParser;
import parser.Vertex;

import java.io.File;

public class FoundationLoadTest {
    public static void main(String[] args) {
        FDB fdb = FDB.selectAPIVersion(100);
        Database db = fdb.open();

        final GraphParser parser = new GraphParser();

        // CAN'T TAKE NO MORE THEN 5 SECONDS, see more: https://foundationdb.com/white-papers/anti-features (long-running transactions)
        long t1 = System.nanoTime();
        db.run(new Function<Transaction, Void>() {
            public Void apply(final Transaction tr) {
                parser.parse(new File("assets/100_gs_e.pl"), new GraphParser.Listener<Vertex>() {
                            @Override
                            public void onItem(Vertex item) {
                                tr.set(Tuple.from("v",item.getSensor(), item.getId()).pack(), Tuple.from("").pack());
                            }
                        }, new GraphParser.Listener<Edge>() {
                            @Override
                            public void onItem(Edge item) {
                                tr.set(Tuple.from("e",
                                        item.getStart().getSensor(), item.getStart().getId(),
                                        item.getEnd().getSensor(), item.getEnd().getId()
                                ).pack(), Tuple.from("").pack());
                            }
                        });

                return null;
            }
        });
        long t2 = System.nanoTime();

        // read vertices only
        AsyncIterable<KeyValue> result = db.run(new Function<Transaction, AsyncIterable<KeyValue>>() {
            public AsyncIterable<KeyValue> apply(Transaction tr) {
                AsyncIterable<KeyValue> result = tr.getRange(Tuple.from("v", "a", 0).pack(), Tuple.from("v", "x", Integer.MAX_VALUE).pack());
                return result;
            }
        });
        long t3 = System.nanoTime();

        for(KeyValue kv : result) {
            Tuple keyTuple = Tuple.fromBytes(kv.getKey());
            Tuple valueTuple = Tuple.fromBytes(kv.getValue());
            System.out.println("k: " + keyTuple.getString(1) + keyTuple.getLong(2) + "  v: " + valueTuple.getString(0));
        }

        System.out.println("Parse + write time: " + (t2-t1) / 1e9);
        System.out.println("Read time: " + (t3-t2) / 1e9);
    }
}
