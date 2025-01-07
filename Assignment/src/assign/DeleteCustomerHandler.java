package assign;

import java.io.IOException;
import java.util.Map;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public class DeleteCustomerHandler implements HttpHandler{
	private CustomerHandler custhandler;
	private CustomerDAO custDAO;
	
	
	public DeleteCustomerHandler( CustomerHandler custhandler, CustomerDAO custDAO) {
		this.custhandler = custhandler;
		this.custDAO = custDAO;
	}


	public void handle(HttpExchange he)throws IOException{
		
		String query = he.getRequestURI().getQuery();
		Map<String, String> map = Util.requestStringToMap(query);

		int id = Integer.parseInt(map.get("id"));
		custDAO.deleteCustomer(id);
		custhandler.handle(he);
	}

}
