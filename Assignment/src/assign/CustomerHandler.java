package assign;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

import java.util.List;


public class CustomerHandler  implements HttpHandler{
	public void handle(HttpExchange he) throws IOException {
		 he.sendResponseHeaders(200, 0);
		 CustomerDAO dao = new CustomerDAO();
		 List<Customer> custo = dao.ListCustomers();
			StringBuilder sb = new StringBuilder();
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
	                 
	                }td {
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
	                <h1>Customer Management</h1>
	                <table>
	                <tr>
	                    <th>CustomerID</th>
	                    <th>BusinessName</th>
	                    <th>Address</th>
	                    <th>PhoneNumber</th>
	                    <th>Actions</th>
	                </tr>
	                """);

	        for (Customer customer : custo) {
	            sb.append("<tr>")
	                    .append("<td>").append(customer.getCustomerID()).append("</td>")
	                    .append("<td>").append(customer.getBusinessName()).append("</td>")
	                    .append("<td>").append(customer.getAddress()).append("</td>")
	                    .append("<td>").append(customer.getTelephoneNumber()).append("</td>")
	                    .append("<td>")
	                    .append("<a href='/customeredit?id=").append(customer.getCustomerID()).append("'>Edit</a> | ")
	                    .append("<a href='/deletecustomer?id=").append(customer.getCustomerID()).append("' onclick='return confirm(\"Are you sure?\")'>Delete</a>")
	                    
	                    
	                    .append("</tr>");
	        }
	        sb.append("""
	        		</table>
	        		<div class="action-buttons">
            <button onclick="window.location.href='/addcustomer'">Add Customer</button>
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
