import com.mongodb.async.client.MongoCollection;
import com.mongodb.async.client.MongoDatabase;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

public class MongoSend {

    public static void main(String[] args) {

        JsonObject mongoconfig = new JsonObject()
                .put("connection_string", "mongodb://localhost:27017")
                .put("db_name", "Books");

        Vertx vertx=Vertx.vertx();

 /*
 MongoCollection collection;
 collection = db.getCollection(Books);
 MongoDatabase database = mongo.getDatabase("dbName");
 collection.insertOne(Document.parse(document.encode())); */

        MongoClient mongo=MongoClient.createShared(vertx,mongoconfig);

              JsonObject document = new JsonObject()
                    .put("Rollno", "24")
                    .put("name", "ruchi")
                    .put("Subject", "Maths");
                     wait(30000);
            mongo.save("data", document, res -> {
                if (res.succeeded()) {
                    String Rollno = res.result();
                    System.out.println("saved Book with rollno." +Rollno);
                } else {
                    res.cause().printStackTrace();
                }

            });

    JsonObject insert = new JsonObject()
                .put("Year", "1999")
                .put("Rollno","24");
        wait(30000);
        mongo.insert("data", insert, res -> {
            if (res.succeeded()) {

                System.out.println("Inserted year");
            } else {
                res.cause().printStackTrace();
            }
        });

        JsonObject query = new JsonObject()
                .put("Rollno", "24");
        JsonObject up = new JsonObject().put("$set", new JsonObject()
                .put("author", "neha"));
        wait(30000);
        mongo.updateCollection("data", query, up, res -> {
            if (res.succeeded()) {
                System.out.println("data updated !");
            } else {
                res.cause().printStackTrace();
            }
        });

        JsonObject remove = new JsonObject()
                .put("Rollno" ,"24")
                .put("author", "neha");
        wait(30000);
        mongo.removeDocuments("data", remove, res -> {
            if (res.succeeded()) {
                System.out.println("Never much liked neha!");
            } else {
                res.cause().printStackTrace();
            }
        });

    }
    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

}
