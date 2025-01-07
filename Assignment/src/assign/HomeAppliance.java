
//student id = 24853168
package assign;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HomeAppliance {
         private int id;
		 private String sku; 
		 private String description;
		 private String category;
		 private int price;
		 
	// Constructor for adding new appliance
		 
public HomeAppliance(String sku, String description, String category, int price) {
		        this.sku = sku;
		        this.description = description;
		        this.category = category;
		        this.price = price;
		       
		    }
//constructor for retrieving appliances
public HomeAppliance(int id, String sku, String description, String category, int price) {
    this.id = id;
    this.sku = sku;
    this.description = description;
    this.category = category;
    this.price = price;
    
}


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getSku() {
	return sku;
}
public void setSku(String sku) {
	this.sku = sku;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}

public String formList() {
	return 
			"<label>ID</label><br /\\> " + "<input name=\"id\" value=\"" + id + "\"> <br /\\> "
			+"<label>SKU</label><br /\\> " + "<input name=\"sku\" value=\"" + sku + "\"> <br /\\> "
			+ "<label>Description</label><br /\\> " + "<input name=\"description\" value=\"" + description + "\">  <br /\\> "
			+ "<label>Category</label><br /\\> " + "<input name=\"category\" value=\"" + category + "\">  <br /\\> "
			+ "<label>Price</label><br /\\> " + "<input name=\"price\" type=\"number\" value=" + price + ">";
}

public String toHTMLString() {
	// return "";
	return "<tr><td>" + sku + "</td><td>" + description + "</td><td>" + category
			+ "</td><td>"+ price +"</td><td><a href=\"/edit?id=" + id + "\">Edit</a></td></tr>";
}
@Override
public String toString() {
    return "ID: " + id + ", SKU: " + sku + ", Description: " + description +
           ", Category: " + category + ", Price: £" + price;
}

public void assignParameters(PreparedStatement statement) throws SQLException {

	statement.setInt(1, id);
	statement.setString(2, sku);
	statement.setString(3, description);
	statement.setString(4, category);
	statement.setInt(5, price);

}

}