package assign;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;

public class CustomerDAO {
	private Connection connect() throws SQLException {

	    String dbURL = "jdbc:sqlite:C:/Users/Bhavitha Sree/Desktop/OOps Assignment/appliance.sqlite";
	    return DriverManager.getConnection(dbURL);

	  }
	
	 public List<Customer> ListCustomers() {
	        List<Customer> customerDetails = new ArrayList<>();
	       
	        try (Connection dbConnection = connect();
			        Statement st = dbConnection.createStatement();){
	        	 String query = "SELECT * FROM customer";
	        	 ResultSet rs = st.executeQuery(query);
	        	  while (rs.next()) {
	                Address ob = new Address(
	                        rs.getString("addressLine0"),
	                        rs.getString("addressLine1"),
	                        rs.getString("addressLine2"),
	                        rs.getString("country"),
	                        rs.getString("postCode")
	                );
	                customerDetails.add(new Customer(
	                		rs.getInt("customerID"),
	                        rs.getString("businessName"),
	                        ob,
	                        rs.getString("telephoneNumber")
	                ));
	            }
	        }catch(SQLException e) {
				 e.printStackTrace();
			 }
	        return customerDetails;
	    }
	 
	 public void addCustomer(Customer cust) {
	 String query = "INSERT INTO customer(customerID, businessName, addressLine0, addressLine1, addressLine2, country, postCode, telephoneNumber) VALUES(?,?,?,?,?,?,?,?)";
	 try(Connection dbConnection = connect();
			 PreparedStatement pst = dbConnection.prepareStatement(query)){
		 pst.setInt(1,  cust.getCustomerID());
		 pst.setString(2, cust.getBusinessName());
		 pst.setString(3, cust.getAddress().getAddressLine0());
		 pst.setString(4, cust.getAddress().getAddressLine1());
		 pst.setString(5, cust.getAddress().getAddressLine2());
		 pst.setString(6, cust.getAddress().getCountry());
		 pst.setString(7, cust.getAddress().getPostCode());
		 pst.setString(8,  cust.getTelephoneNumber());
		 pst.executeUpdate();
		 
		 }catch(SQLException e) {
		 e.printStackTrace();
	 }	
}
	 
	 public Customer findCustomer(int customerID) {
		String query = "SELECT * FROM customer WHERE customerID = ?";
		try(Connection dbConnection = connect();
				 PreparedStatement pst = dbConnection.prepareStatement(query)){
			pst.setInt(1, customerID);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				Address ob = new Address(
                        rs.getString("addressLine0"),
                        rs.getString("addressLine1"),
                        rs.getString("addressLine2"),
                        rs.getString("country"),
                        rs.getString("postCode")
                );
                Customer obj = new Customer(
                		rs.getInt("customerID"),
                        rs.getString("businessName"),
                        ob,
                        rs.getString("telephoneNumber")
                );
                obj.setCustomerID(rs.getInt("customerId"));
                return obj;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		 return null;	 
	 }
	 
	 public boolean updateCustomer(Customer cust) throws SQLException {
	        String query = "UPDATE customer SET businessName = ?, addressLine0=?, addressLine1=?, addressLine2=?, country=?, postCode=?, telephoneNumber=? WHERE customerID = ?";
	        try (Connection dbConnection = connect();
	             PreparedStatement pst = dbConnection.prepareStatement(query)) {
	            pst.setString(1, cust.getBusinessName());
	            pst.setString(2, cust.getAddress().getAddressLine0());
	            pst.setString(3, cust.getAddress().getAddressLine1());
	            pst.setString(4, cust.getAddress().getAddressLine2());
	            pst.setString(5, cust.getAddress().getCountry());
	            pst.setString(6, cust.getAddress().getPostCode());
	            pst.setString(7, cust.getTelephoneNumber());
	            pst.setInt(8, cust.getCustomerID());
	            int affectedRows = pst.executeUpdate();
	            return affectedRows > 0;
	        }
	    }
	

	 public void deleteCustomer(int customerID) {
		 String query = "DELETE FROM customer WHERE customerID = ?";
		 try (Connection dbConnection = connect();
				 PreparedStatement pst = dbConnection.prepareStatement(query)){
			pst.setInt(1, customerID);
			pst.executeUpdate();
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
	 }
	  
}
