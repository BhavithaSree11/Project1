package assign;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class EditProductHandler implements HttpHandler {

	private HomeApplianceDAO homeDAO;
	
	public EditProductHandler (HomeApplianceDAO homeDAO) {
		this.homeDAO = homeDAO;
	}

	public void handle(HttpExchange he) throws IOException {

		he.sendResponseHeaders(200, 0);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));

		String query = he.getRequestURI().getQuery();

		HashMap<String, String> map = Util.requestStringToMap(query);

		int id = Integer.parseInt(map.get("id"));
		HomeAppliance c=homeDAO.findProduct(id);
		if(c!=null) {
			 String formHtml = "<html>" +
			            "<head>" +
			            "<title>Edit Form</title>" +
			            "</head>" +
			            "<body>" +
			            "<h2>Edit Product</h2>" +
			            "<form method='Post' action='/formaction'>" +
			            c.formList() +  
			            "<input type='hidden' name='id' value='" + c.getId() + "'>" +
			            "<input type='submit' value='Submit'>" +
			            "</form>" +
			            "<a href='/adminpro'>Cancel</a>" +
			            "</body>" +
			            "</html>";
			    
			    out.write(formHtml);
		}else {
			 out.write("<html><body><h2>Product Not Found</h2></body></html>");
		}
		out.close();

	}

}