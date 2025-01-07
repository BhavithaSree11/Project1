package assign;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ProductHandler implements HttpHandler{
	public void handle(HttpExchange he) throws IOException {
		 he.sendResponseHeaders(200, 0);
		HomeApplianceDAO ob = new HomeApplianceDAO();
		List<HomeAppliance> prod = ob.findAllProducts();
		 StringBuilder rs= new StringBuilder();
		 rs.append("""
	        	  <html>
               <head>
               <style>
               table {
                   width: 100%;
                   border-collapse: collapse;
               }
               table, th, td {
                   border: 1px solid black;
               }
               th{
                padding: 10px;
                 text-align: center;
                 background-color: #f2f2f2;
                 color: #333;
	              }
               td {
                   padding: 10px;
                   text-align: center;
               }
               tr:hover {
                background-color: #e6f7ff;
                 }
               button {
                   background-color: white;
                   padding: 10px;
                   border: 1px solid black;
                   border-radius: 5px;
               }
               button:hover {
                   background-color: #ADD8E6;
               }
               .action-buttons {
                   margin-top: 20px;
                   display: flex;
                   justify-content: flex-end;
               }
               </style>
               </head>
               <body>
               <h1>List of Products Available</h1>
               <table>
               <tr>
                   <th>ID</th>
                    <th>SKU</th>
                    <th>Description</th>
                    <th>Category</th>
                    <th>Price</th>
               </tr>
               """);

	        for (HomeAppliance product : prod) {
	            rs.append("<tr>")
	                    .append("<td>").append(product.getId()).append("</td>")
	                    .append("<td>").append(product.getSku()).append("</td>")
	                    .append("<td>").append(product.getDescription()).append("</td>")
	                    .append("<td>").append(product.getCategory()).append("</td>")
	                    .append("<td>").append(product.getPrice()).append("</td>")
	                    .append("</tr>");
	        }
	        rs.append("</table>")
            .append("<a href=\"/menu\">Back to Menu</a>")
            .append("</body>")
            .append("</html>"); 
	        OutputStream os = he.getResponseBody();
	        os.write(rs.toString().getBytes());
	        os.close();
		
	}

}
