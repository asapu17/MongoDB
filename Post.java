import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class Post
{
    public static void main(String args[])
    {
        Vertx vertx= Vertx.vertx();
        HttpServer httpServer=vertx.createHttpServer();

        Router router= Router.router(vertx);
        Route one=router
                .post("/ruchi")
                .handler(routingContext -> {
                    System.out.println("i'm done");
                    HttpServerResponse response=routingContext.response();
                    response.setChunked(true);
                    response.write("hi i'm database");
                    response.end();
                });
        Route two=router
                .get("/ruchi")
                .handler(routingContext -> {
                    System.out.println("i'm done from get");
                    HttpServerResponse response=routingContext.response();
                    response.setChunked(true);
                    response.write("displaying data");
                    response.end();
                });

        httpServer.requestHandler(router::accept).listen(8080);
    }
}
