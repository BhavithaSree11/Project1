package assign;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	private Connection connect() throws SQLException {

	    String dbURL = "jdbc:sqlite:C:/Users/Bhavitha Sree/Desktop/OOps Assignment/appliance.sqlite";
	    return DriverManager.getConnection(dbURL);

	  }
	public boolean userExists(String un, String ps) {
		 String query = "SELECT * FROM users WHERE username = ? AND password = ?";
		 try(Connection dbConnection = connect();
				 PreparedStatement pst = dbConnection.prepareStatement(query)){
			 pst.setString(1, un);
	            pst.setString(2, ps);

	            ResultSet rs = pst.executeQuery();
	            return rs.next();
		 }catch(Exception e) {
			 e.printStackTrace();
			 return false;
		 }
	}

}
