package assign;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class FormprocessCustomerHandler implements HttpHandler {
	private CustomerDAO cuDAO ;
	private CustomerHandler customerhandler;
	public FormprocessCustomerHandler (CustomerHandler customerhandler, CustomerDAO cuDAO) {
		this.customerhandler = customerhandler;
		this.cuDAO = cuDAO;
	}

	public void handle(HttpExchange he) throws IOException {

		System.out.println("In FormProcessCustomerHandler");

		BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));

		String line;
		StringBuilder sb = new StringBuilder();

		while ((line = in.readLine()) != null) {
			sb.append(line);
		}

		String request = sb.toString();
		System.out.println("Request receieved:" +request);
		HashMap<String, String> map = Util.requestStringToMap(request);
	        int customerID = Integer.parseInt(map.get("customerID"));
	        String businessName = map.get("businessName");
	        
	        Address address = new Address(
	            map.get("addressLine0"),
	            map.get("addressLine1"),
	            map.get("addressLine2"),
	            map.get("country"),
	            map.get("postCode")
	        );
	        
	        String telephoneNumber = map.get("telephoneNumber");
	        Customer c = new Customer(customerID, businessName, address, telephoneNumber);

	        try {
	            boolean isUpdated =cuDAO.updateCustomer(c);
	            if (isUpdated) {
	                he.getResponseHeaders().set("Location", "/customers");
	                he.sendResponseHeaders(302, 0);
	            } else {
	                String response = "<html><body><h2>Update Failed</h2></body></html>";
	                he.sendResponseHeaders(400, response.length());
	                he.getResponseBody().write(response.getBytes());
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            String errorResponse = "<html><body><h2>Customer details are not updated</h2></body></html>";
	            he.sendResponseHeaders(500, errorResponse.length());
	            he.getResponseBody().write(errorResponse.getBytes());
	        } finally {
	            he.getResponseBody().close();
	        }
	    }
	}
