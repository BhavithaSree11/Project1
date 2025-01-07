package assign;

import java.util.Scanner;


public class CustomerController {
	public static void main(String[]args) {
		
		Scanner cu = new Scanner(System.in);
		CustomerDAO cd = new CustomerDAO();
		int ch =0;
		do {
			System.out.println("--------CUSTOMERS MENU------------");
			System.out.println("\nHome Appliance Store");
			System.out.println("Choose from these options");
            System.out.println("1. List all customers");
            System.out.println("2. Add the Customer");
            System.out.println("3. Find a Customer by ID");
            System.out.println("4. Update the Customer Details");
            System.out.println("5. Delete a customer record");
            System.out.println("6. Exit");
            System.out.println();
            System.out.print("Enter your Option from Menu: ");
            
            try {
            	 ch = cu.nextInt();
                 cu.nextLine();
                 
            switch(ch) {
            case 1:
            	for(Customer cust : cd.ListCustomers()) {
            		System.out.println(cust);
            	}
            	break;
            case 2:
            	System.out.print("Enter customerID:");
            	int cID = cu.nextInt();
            	System.out.print("Enter business name:");
                String ba = cu.nextLine();
                System.out.print("Enter address line 0:");
                String ad0 = cu.nextLine();
                System.out.print("Enter address line 1:");
                String ad1 = cu.nextLine();
                System.out.print("Enter address line 2:");
                String ad2 = cu.nextLine();
                System.out.print("Enter country:");
                String coun = cu.nextLine();
                System.out.print("Enter postcode:");
                String pst = cu.nextLine();
                System.out.print("Enter telephone number:");
                String tn = cu.nextLine();
                Address address = new Address(ad0, ad1, ad2, coun, pst);
                Customer newCustomer = new Customer(cID, ba, address, tn);
                cd.addCustomer(newCustomer);
                System.out.println("Customer added successfully.");
                break;
                
            case 3:
            	System.out.print("Enter Customer ID to search: ");
            	int custID = Integer.parseInt(cu.nextLine());
            	Customer obj = cd.findCustomer(custID);
            	System.out.println(obj);
            	break;
            	
            case 4:
            	 System.out.print("Enter customer ID to update:");
                 int cuID = Integer.parseInt(cu.nextLine());
                 try {
                	 Customer customer = cd.findCustomer(cuID);
                	 if(customer!=null) {
                		 System.out.print("Enter New Bussiness Name: ");
                         customer.setBusinessName(cu.nextLine());
                         System.out.print("Enter New AddressLine0: ");
                         customer.getAddress().setAddressLine0(cu.nextLine());
                         System.out.print("Enter New AddressLine1: ");
                         customer.getAddress().setAddressLine1(cu.nextLine());
                         System.out.print("Enter New AddressLine2: ");
                         customer.getAddress().setAddressLine2(cu.nextLine());
                         System.out.print("Enter New Country Name: ");
                         customer.getAddress().setCountry(cu.nextLine());
                         System.out.print("Enter New Post Code: ");
                         customer.getAddress().setPostCode(cu.nextLine());
                         System.out.print("Enter New Telephone Number: ");
                         customer.setTelephoneNumber(cu.nextLine());
                         cd.updateCustomer(customer);
                         System.out.println("Customer updated successfully"); 
                	 }
                	 else {
                		 System.out.println("Customer not found");
                	 }
                 }catch(Exception e) {
                	 e.printStackTrace();
                 }
                 break;
                 
            case 5:
            	System.out.print("Enter Customer ID to delete: ");
                int idDelete = Integer.parseInt(cu.nextLine());
                cd.deleteCustomer(idDelete);
                System.out.println("Customer deleted.");
                break;
                
            case 6:
            	System.out.println("Exiting.....");
                
            default:
            	System.out.println("Selection is invalid, please enter again");
            	
            }
            }catch(Exception e) {
            	e.printStackTrace();
            }
		
	}while (ch!=6);
		cu.close();
		}
}

