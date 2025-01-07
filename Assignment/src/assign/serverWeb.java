package assign;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;

public class serverWeb {
	static final private int PORT = 8110;
	void startServer(HttpServer server, RootHandler rh, LoginHandler lh, DeleteProductHandler dph, ProductHandler ph, CustomerHandler ch, AdminProduct ap, EditProductHandler eph,
			FormProcessHandler fph, InsertProductHandler iph, FormProcessInsertHandler fpih, DeleteCustomerHandler dch, EditCustomerHandler ech, FormprocessCustomerHandler fpch) {
		server.createContext("/menu", rh);
		server.createContext("/login", lh);
		server.createContext("/deleteproduct", dph);
		server.createContext("/products", ph);
		server.createContext("/customers", ch);
		server.createContext("/adminpro", ap);
		server.createContext("/editproduct", eph);
		server.createContext("/formaction", fph);
		server.createContext("/addproduct", iph);
		server.createContext("/forminsert", fpih);
		server.createContext("/deletecustomer", dch);
		server.createContext("/customeredit", ech);
		server.createContext("/customerform", fpch);
		server.setExecutor(null);
		server.start();
		System.out.println("The server is listening on port " + PORT);
	}
	
	public static void main(String[] args) throws IOException {
	HttpServer server = HttpServer.create(new InetSocketAddress(PORT),0);
	AdminProduct ap = new AdminProduct();
	HomeApplianceDAO homeDAO = new HomeApplianceDAO();
	CustomerDAO cuDAO = new CustomerDAO();
	RootHandler rh = new RootHandler();
	LoginHandler lh = new LoginHandler();
	ProductHandler ph = new ProductHandler();
	EditProductHandler eph = new EditProductHandler(homeDAO);
	FormProcessHandler fph = new FormProcessHandler(ap, homeDAO);
	InsertProductHandler iph = new InsertProductHandler();
	DeleteProductHandler dph = new DeleteProductHandler(ap, homeDAO);
	FormProcessInsertHandler fpih = new FormProcessInsertHandler(ap,homeDAO);
	
	
	CustomerHandler ch = new CustomerHandler();
	DeleteCustomerHandler dch = new DeleteCustomerHandler(ch, cuDAO);
	EditCustomerHandler ech = new EditCustomerHandler(cuDAO);
	FormprocessCustomerHandler fpch = new FormprocessCustomerHandler(ch, cuDAO);
	
	
	serverWeb sw = new serverWeb();
	sw.startServer(server, rh, lh, dph, ph, ch, ap, eph, fph, iph,fpih, dch, ech, fpch);
	
	
	
	

}
}
