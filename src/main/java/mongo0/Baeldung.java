package mongo0;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class Baeldung {

    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        Mongo mongo = new Mongo("localhost", 27017);
        DB database = mongoClient.getDB("myMongoDb");

        mongoClient = new MongoClient();
        database    = mongoClient.getDB("myMongoDb");
//        boolean auth = database.authenticate("username", "pwd".toCharArray());

        mongoClient.getDatabaseNames().forEach(System.out::println);

        database.createCollection("customers", null);

        database.getCollectionNames().forEach(System.out::println);

        DBCollection collection = database.getCollection("customers");
        BasicDBObject document = new BasicDBObject();
        document.put("name", "Shubham");
        document.put("company", "Baeldung");
        collection.insert(document);

        BasicDBObject query = new BasicDBObject();
        query.put("name", "Shubham");

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("name", "John");

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);

        collection.update(query, updateObject);

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", "John");
        DBCursor cursor = collection.find(searchQuery);

        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }

        searchQuery = new BasicDBObject();
        searchQuery.put("name", "John");

        collection.remove(searchQuery);
    }
}
