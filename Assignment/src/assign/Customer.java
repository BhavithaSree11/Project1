package assign;

public class Customer {
	
	    private int customerID;
	    private String businessName;
	    private Address address;
	    private String telephoneNumber;

	    public Customer(int customerID, String businessName, Address address, String telephoneNumber) {
	    	this.customerID = customerID;
	        this.businessName = businessName;
	        this.address = address;
	        this.telephoneNumber = telephoneNumber;
	    }

		public int getCustomerID() {
			return customerID;
		}

		public void setCustomerID(int customerID) {
			this.customerID = customerID;
		}

		public String getBusinessName() {
			return businessName;
		}

		public void setBusinessName(String businessName) {
			this.businessName = businessName;
		}

		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		public String getTelephoneNumber() {
			return telephoneNumber;
		}

		public void setTelephoneNumber(String telephoneNumber) {
			this.telephoneNumber = telephoneNumber;
		}
		@Override
		public String toString() {
	        return "CustomerID: " + customerID + ", BusinessName: " + businessName + 
	               ", Address: " + address + ", Telephone: " + telephoneNumber;
	    }

		public String formListCustomers() {
			
			return 
					 "<label>Customer ID:</label><br /> " +
		                "<input name='customerID' value='" + customerID + "' readonly> <br /> " +
		                "<label>Business Name:</label><br /> " +
		                "<input name='businessName' value='" + businessName + "'> <br /> " +
		                "<label>Address Line 1:</label><br /> " +
		                "<input name='addressLine0' value='" + address.getAddressLine0() + "'> <br /> " +
		                "<label>Address Line 2:</label><br /> " +
		                "<input name='addressLine1' value='" + address.getAddressLine1() + "'> <br /> " +
		                "<label>City:</label><br /> " +
		                "<input name='addressLine2' value='" + address.getAddressLine2() + "'> <br /> " +
		                "<label>Country:</label><br /> " +
		                "<input name='country' value='" + address.getCountry() + "'> <br /> " +
		                "<label>Post Code:</label><br /> " +
		                "<input name='postCode' value='" + address.getPostCode() + "'> <br /> " +
		                "<label>Telephone:</label><br /> " +
		                "<input name='telephoneNumber' value='" + telephoneNumber + "'> <br /> ";
		}
		public String toHTMLString() {
			// return "";
			return "<tr><td>" + customerID + "</td><td>" + businessName + "</td><td>" + address
					+ "</td><td>"+ telephoneNumber +"</td><td><a href=\"/edit?id=" + customerID + "\">Edit</a></td></tr>";
		}
		
		
		}
		

