package ad;

import com.mongodb.MongoBulkWriteException;
import com.mongodb.MongoClient;
import com.mongodb.MongoCommandException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.Document;

public class Main1 {

    static MongoClient mongo;
    static MongoDatabase database;
    static MongoCollection<Document> pedidos;

    public static void main(String[] args) {
        mongo    = new MongoClient("10.0.9.27", 27017);
        database = mongo.getDatabase("tenda");
        pedidos  = database.getCollection("pedidos");

        showContent(pedidos);
    }

    private static void init() {

        try {
            database.createCollection("pedidos");
        } catch (MongoCommandException mce) {
            mce.printStackTrace();
        }

        try {
            initInsert(pedidos);
        } catch (MongoBulkWriteException mbwe) {
            mbwe.printStackTrace();
        }
        showContent(pedidos);
    }

    private static void showContent(MongoCollection<Document> pedidos) {
        // Getting the iterable object
        FindIterable<Document> iterDoc = pedidos.find();
        int i = 1;

        // Getting the iterator
        Iterator it = iterDoc.iterator();

        while (it.hasNext()) {
//            System.out.println( ((Document)it.next()).get("cantidade"));
            System.out.println(it.next());
            i++;
        }
    }

    private static void initInsert(MongoCollection<Document> pedidos) {
        List<Document> seedData = new ArrayList<>();
        seedData.add(createDocument("p1", "c1", "pro1", 2, "02/02/2011"));
        seedData.add(createDocument("p2", "c2", "pro2", 3, "03/03/2011"));
        seedData.add(createDocument("p3", "c1", "pro2", 4, "04/04/2011"));

        pedidos.insertMany(seedData);
    }

    static Document createDocument(String id, String codcli, String codpro, int cantidade, String data) {
        return new Document("_id", id)
            .append("codcli", codcli)
            .append("codpro", codpro)
            .append("cantidade", cantidade)
            .append("data", data);
    }
}
