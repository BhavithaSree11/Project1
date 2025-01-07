package assign;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class EditCustomerHandler implements HttpHandler {
	
private CustomerDAO cuDAO;
		
		public EditCustomerHandler (CustomerDAO cuDAO) {
			this.cuDAO = cuDAO;
		}

		public void handle(HttpExchange he) throws IOException {

			he.sendResponseHeaders(200, 0);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));

			String query = he.getRequestURI().getQuery();

			HashMap<String, String> map = Util.requestStringToMap(query);

			int customerID= Integer.parseInt(map.get("customerID"));
			Customer cu=cuDAO.findCustomer(customerID);
			if(cu!=null) {
				 String formHtml = "<html>" +
				            "<head>" +
				            "<title>Edit Form for Customer Details</title>" +
				            "</head>" +
				            "<body>" +
				            "<h2>Edit Customer</h2>" +
				            "<form method='Post' action='/editcustomerform'>" +
				            cu.formListCustomers() +  
				            "<input type='hidden' name='customerID' value='" + cu.getCustomerID() + "'>" +
				            "<input type='submit' value='Submit'>" +
				            "</form>" +
				            "<a href='/customers'>Cancel</a>" +
				            "</body>" +
				            "</html>";
				    
				    out.write(formHtml);
			}else {
				 out.write("<html><body><h2>Customer Not Found</h2></body></html>");
			}
			out.close();

		}

	}


