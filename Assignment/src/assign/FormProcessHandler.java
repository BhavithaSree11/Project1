package assign;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class FormProcessHandler implements HttpHandler {

	
	private HomeApplianceDAO homeDAO;
	private AdminProduct adminproduct;

	public FormProcessHandler(AdminProduct adminproduct,HomeApplianceDAO homeDAO) {
		this.adminproduct = adminproduct;
		this.homeDAO = homeDAO;
	}

	public void handle(HttpExchange he) throws IOException {

		System.out.println("In FormProcessHandler");

		BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));

		String line;
		StringBuilder sb = new StringBuilder();

		while ((line = in.readLine()) != null) {
			sb.append(line);
		}

		String request = sb.toString();
		System.out.println("Request receieved:" +request);
		HashMap<String, String> map = Util.requestStringToMap(request);
		
		int id = Integer.parseInt(map.get("id"));
		String sku = map.get("sku");
		String description = map.get("description");
		String category = map.get("category");
		int price = Integer.parseInt(map.get("price"));
		HomeAppliance prod = new HomeAppliance(sku, description, category, price);

		try {
			boolean isUpdated = homeDAO.updateProduct(id, prod);
			if(isUpdated) {
				System.out.println("Product updated successfully!");
				he.getResponseHeaders().set("Location", "/adminpro");
			    he.sendResponseHeaders(302, 0);
	        } else {
	        	 System.out.println("Product update failed!");
	             String errorResponse = "<html><body><h2>Update Failed</h2><p>Could not update the product.</p></body></html>";
	             he.sendResponseHeaders(400, errorResponse.length());
	             he.getResponseBody().write(errorResponse.getBytes());
	        }
			
		}catch(SQLException e) {
			 e.printStackTrace();
		        String errorResponse = "<html><body><h2>Error updating product</h2><pre>" + e.getMessage() + "</pre></body></html>";
		        he.sendResponseHeaders(500, errorResponse.length());
		        he.getResponseBody().write(errorResponse.getBytes());
		}
		finally {
			he.getResponseBody().close();
		}

		

	}

}
