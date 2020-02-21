package ad;

import com.mongodb.MongoBulkWriteException;
import com.mongodb.MongoClient;
import com.mongodb.MongoCommandException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.Document;

public class Main1 {

    static MongoClient mongo;
    static MongoDatabase database;
    static MongoCollection<Document> pedidos;

    public static void main(String[] args) {
        init();
        showAllContent();
        ej1();
        showAllContent();
        ej2();
        queryOne("p3");
        ej3();
        queryOne("p2");
        ej4();
        ej5();
        ej6();


    }


    private static void ej1() {
        System.out.println("#Ej1");
        pedidos.insertOne(createDocument("p4", "c1", "pro3", 5, "02/02/2019"));
    }

    private static void ej2() {
        System.out.println("#Ej2");
        pedidos.updateOne(Filters.eq("_id", "p3"), Updates.set("codpro", "pro4"));
    }

    private static void ej3() {
        System.out.println("#Ej3");
        incrementCantidadBy("p2", 7);
    }

    private static void ej4() {
        System.out.println("#Ej4");
        queryOne("p3");
    }

    private static void ej5() {
        System.out.println("#Ej5");
        Document query = queryOne("p1");
        queryFields(query, "codcli", "codpro", "cantidade");
    }

    private static void ej6() {
        System.out.println("#Ej6");
        Iterator iterator = findByCantidadeGT(2).iterator();
        while (iterator.hasNext()) {
            queryFields((Document) iterator.next(), "codcli", "codpro");
        }


    }

    private static void init() {
        mongo    = new MongoClient("10.0.9.119", 27017);
        database = mongo.getDatabase("tenda");
        pedidos  = database.getCollection("pedidos");
        pedidos.drop();
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
    }

    private static void showAllContent() {
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

    static Document queryOne(String id) {
        Document document = pedidos.find(Filters.eq("_id", id)).first();
        System.out.println(document);
        return document;
    }

    static void incrementCantidadBy(String id, int incremento) {
        pedidos.updateOne(Filters.eq("_id", "p2"), Updates.inc("cantidade", incremento));
    }

    static void queryFields(Document query, String... fields) {
        for (String field : fields) {
            System.out.println(field + ": " + query.get(field));
        }
    }

    static FindIterable<Document> findByCantidadeGT(int cantidade) {
        return pedidos.find(Filters.gt("cantidade", cantidade));
    }
}
