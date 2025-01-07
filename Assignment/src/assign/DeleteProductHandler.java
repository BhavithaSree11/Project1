package assign;

import java.io.IOException;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class DeleteProductHandler implements HttpHandler {
	private AdminProduct adminproduct;
	private HomeApplianceDAO homeDAO;
	
	
	public DeleteProductHandler(AdminProduct adminproduct,HomeApplianceDAO homeDAO ) {
		this.adminproduct = adminproduct;
		this.homeDAO = homeDAO;
	}


	public void handle(HttpExchange he)throws IOException{
		
		String query = he.getRequestURI().getQuery();
		Map<String, String> map = Util.requestStringToMap(query);

		int id = Integer.parseInt(map.get("id"));
		homeDAO.deleteProduct(id);
		adminproduct.handle(he);
	}

}
