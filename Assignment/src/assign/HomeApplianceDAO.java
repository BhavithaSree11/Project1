package assign;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class HomeApplianceDAO {
	private Connection connect() throws SQLException {

	    String dbURL = "jdbc:sqlite:C:/Users/Bhavitha Sree/Desktop/OOps Assignment/appliance.sqlite";
	    return DriverManager.getConnection(dbURL);

	  }

	
	 //Function to retrieve list of all the products 
	 public List<HomeAppliance>findAllProducts(){
		 List<HomeAppliance>products = new ArrayList<>();
		
		 
		 try (Connection dbConnection = connect();
			        Statement st = dbConnection.createStatement();){
			 String query = "SELECT * FROM products";    
			 ResultSet rs = st.executeQuery(query);
			 
			 while(rs.next()) {
						 int id = rs.getInt("id");
						String sku = rs.getString("sku");
						String desc= rs.getString("description");
						String cat= rs.getString("category");
						 int pr =rs.getInt("price");
						
						  
						 products.add(new HomeAppliance(id, sku, desc, cat, pr));
			 }
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
		 return products;
	 }
	 
	 public List<HomeAppliance> findProductsByCategory(String category) {
		    List<HomeAppliance> products = new ArrayList<>();
		    String query = "SELECT * FROM products WHERE category = ?";
		    try (Connection dbConnection = connect();
		    		PreparedStatement pst = dbConnection.prepareStatement(query)) {
		        pst.setString(1, category);
		        ResultSet rs = pst.executeQuery();
		        while (rs.next()) {
		            products.add(new HomeAppliance(
		            		rs.getInt("id"),
		                rs.getString("category"),
		                rs.getString("sku"),
		                rs.getString("description"),
		                
		                rs.getInt("price")
		            ));
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return products;
		}
	 
	 //Find a product by it's ID
	 public HomeAppliance findProduct(int id){
		 String query = "SELECT * FROM products WHERE id = ?";
		 try(Connection dbConnection = connect();
				 PreparedStatement pst = dbConnection.prepareStatement(query)){
			 pst.setInt(1,  id);
			 ResultSet rs = pst.executeQuery();
				 if(rs.next()) {			  
					 return new HomeAppliance(
							 rs.getInt("id"),
							 rs.getString("sku"),
							 rs.getString("description"),
							 rs.getString("category"),
							 rs.getInt("price")
							 
							 );
				 }
			 
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
		return null;
	 }
	 
	 //Function to delete a product
	 public void deleteProduct(int id){
		 String query = "DELETE FROM products WHERE id = ?";
		 try(Connection dbConnection = connect();
				 PreparedStatement pst = dbConnection.prepareStatement(query)){
			 pst.setInt(1,  id);
			 pst.executeUpdate();
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
	 }
	 //Function to update a product details
	 public boolean updateProduct(int id, HomeAppliance appliance) throws SQLException{
		 String query = "UPDATE products SET sku = ?, description = ?, category = ?, price = ?WHERE id = ?";
		 try (Connection dbConnection = connect();
				 PreparedStatement pst = dbConnection.prepareStatement(query)){
			 pst.setString(1, appliance.getSku());
			 pst.setString(2, appliance.getDescription());
			 pst.setString(3, appliance.getCategory());
			 pst.setInt(4, appliance.getPrice());
			
			 pst.setInt(5, id);
			
			int rowsChanged =pst.executeUpdate();
			return rowsChanged >0;
		 }catch(SQLException e) {
			 System.err.println("Error while updating product: " + e.getMessage());
			 throw e;
		 }
	 }
	 
	 
	 //Function to add a new product
	 public void addProduct(HomeAppliance appliance){
		 String query = "INSERT INTO products(sku, description, category, price) VALUES(?,?,?,?)";
		 try(Connection dbConnection = connect();
				 PreparedStatement pst = dbConnection.prepareStatement(query)){
			 pst.setString(1,  appliance.getSku());
			 pst.setString(2,  appliance.getDescription());
			 pst.setString(3,  appliance.getCategory());
			 pst.setInt(4,  appliance.getPrice());
			
			 pst.executeUpdate();
			 
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
	 }




	





}
