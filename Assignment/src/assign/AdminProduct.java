package assign;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class AdminProduct implements HttpHandler{
	public void handle(HttpExchange he) throws IOException {
		 he.sendResponseHeaders(200, 0);
		 
		 String qu = he.getRequestURI().getQuery();
		 String filter = null;
	        String search = null;
	        if (qu!= null) {
	            for (String param : qu.split("&")) {
	                String[] pair = param.split("=");
	                if (pair[0].equals("category")) {
	                    filter = pair[1];
	                } else if (pair[0].equals("search")) {
	                    search = pair[1];
	                }
	            }
	        }
	        
		HomeApplianceDAO obj = new HomeApplianceDAO();
		List<HomeAppliance> prod = obj.findAllProducts();
		if (filter != null && !filter.isEmpty()) {
            prod = (obj). findProductsByCategory(filter);
        } else {
            prod = obj.findAllProducts(); 
        }
		
		
		 StringBuilder sb= new StringBuilder();
	        sb.append("""
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
                <h1>Admin Product Management</h1>
                
                 <!-- Filter and Search Form -->
                <form method="GET" action="/adminpro">
                    <label for="category">Filter by Category:</label>
                    <select name="category" id="category">
                        <option value="">All</option>
                        <option value="Electronics">Electronics</option>
                        <option value="Kitchen">Kitchen</option>
                        <option value="Entertainment">Entertainment</option>
                    </select>
                    <label for="search">Search:</label>
                    <input type="text" name="search" id="search" placeholder="Search products...">
                    <button type="submit">Apply</button>
                </form>
                
                <table>
                <tr>
                    <th>ID</th>
                    <th>SKU</th>
                    <th>Description</th>
                    <th>Category</th>
                    <th>Price</th>
                    <th>Actions</th>
                </tr>
                """);

        for (HomeAppliance product : prod) {
            sb.append("<tr>")
                    .append("<td>").append(product.getId()).append("</td>")
                    .append("<td>").append(product.getSku()).append("</td>")
                    .append("<td>").append(product.getDescription()).append("</td>")
                    .append("<td>").append(product.getCategory()).append("</td>")
                    .append("<td>").append(product.getPrice()).append("</td>")
                    .append("<td>")
                    .append("<a href='/editproduct?id=").append(product.getId()).append("'>Edit</a> | ")
                    .append("<a href='/deleteproduct?id=").append(product.getId()).append("' onclick='return confirm(\"Are you sure?\")'>Delete</a>")
                    
                    .append("</tr>");
        }

        sb.append("""
                </table>
                <div class="action-buttons">
                <button onclick="window.location.href='/addproduct'">Insert Product</button>
                    <button onclick="window.location.href='/customers'">Manage Customers</button>
                </div>
                <a href="/menu">Back to Menu</a>
                </body>
                </html>
	        		
	        		""");
	        
	        OutputStream os = he.getResponseBody();
	        os.write(sb.toString().getBytes());
	        os.close();
		
	}

}
