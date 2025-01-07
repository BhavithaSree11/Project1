package assign;

public class Address {
	private String addressLine0;
    private String addressLine1;
    private String addressLine2;
    private String country;
    private String postCode;

    public Address(String addressLine0, String addressLine1, String addressLine2, String country, String postCode) {
        this.addressLine0 = addressLine0;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.country = country;
        this.postCode = postCode;
    }

	public String getAddressLine0() {
		return addressLine0;
	}

	public void setAddressLine0(String addressLine0) {
		this.addressLine0 = addressLine0;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	@Override
	public String toString() {
        return "AddressLine0: " + addressLine0 + ", AddressLine1: " +addressLine1 + 
               ", AddressLine2: " + addressLine2 + ", Country: " + country + ", PostCode: "+ postCode;
    }
	
}
