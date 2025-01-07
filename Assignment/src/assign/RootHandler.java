package assign;
import java.io.OutputStreamWriter;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedWriter;
import java.io.IOException;

public class RootHandler implements HttpHandler {
    public void handle(HttpExchange he) throws IOException {
        String response = "Welcome to OOP";
        he.sendResponseHeaders(200, 0);
        
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
        out.write(
            "<html>" +
            "<head><title>My new homeappliance web page</title></head>" +
            "<body>" +
            "<h1>Hello, Welcome to the HomeAppliance Store Website!</h1>" +
            "<li><a href=\"/products\">View Products List</a></li>"+
            "<li><a href=\"/login\">Admin Login</a></li>"+
            "</body>" +
            "</html>"
        );
        out.close();
    }

}


