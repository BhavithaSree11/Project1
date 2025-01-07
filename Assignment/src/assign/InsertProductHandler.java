package assign;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class InsertProductHandler implements HttpHandler {
		

		public void handle(HttpExchange he) throws IOException {

			he.sendResponseHeaders(200, 0);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));

			out.write("<html>" + "<head> <title>Insert Product Form</title> </head>" + "<body>"
					+ "<form method=\"Post\" action=\"/forminsert\">" + 
					
					"<label>SKU</label><br /\\> " + "<input name=\"sku\" value=\"\"> <br /\\> "
					+ "<label>Description</label><br /\\> " + "<input name=\"description\" value=\"\">  <br /\\> "
					+ "<label>Category</label><br /\\> " + "<input name=\"category\" value=\"\">  <br /\\> "
					+ "<label>Price</label><br /\\> " + "<input name=\"price\" type=\"number\" value=>"
					+ "<input type=\"submit\" value=\"Submit\">  " + "</form>" + "<a href=\"/\"> Cancel </a>" + "</body>"
					+ "</html>");

			out.close();

		}

	}

