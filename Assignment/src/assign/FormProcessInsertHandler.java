package assign;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class FormProcessInsertHandler implements HttpHandler {

    private HomeApplianceDAO homeDAO;
	private AdminProduct adminproduct;

    public FormProcessInsertHandler(AdminProduct adminproduct, HomeApplianceDAO homeDAO) {
    	this.adminproduct = adminproduct;
    	this.homeDAO = homeDAO;
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        System.out.println("In FormProcessHandler");

       
        BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        String request = sb.toString();
        System.out.println("Request: " + request);

        
        HashMap<String, String> map = Util.requestStringToMap(request);
        try {

       // int id = Integer.parseInt(map.get("id"));
        String sku = map.get("sku");
        String description = map.get("description");
        String category = map.get("category");
        int price = Integer.parseInt(map.get("price"));

        
        HomeAppliance newProduct = new HomeAppliance(sku, description, category, price);

        
            
            homeDAO.addProduct(newProduct);
            System.out.println("Product successfully added");
            he.getResponseHeaders().set("Location", "/adminpro");
            he.sendResponseHeaders(303, 0); 
        
        }catch(SQLException e){
            e.printStackTrace();
            he.sendResponseHeaders(500, 0);
        }
        }
    }


