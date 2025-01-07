package assign;
import java.util.Scanner;

public class HomeApplianceController {
	public static void main(String[]args) {
		
			Scanner sc = new Scanner(System.in);
			HomeApplianceDAO obj = new HomeApplianceDAO();
			int choice=0;
			
			do {
				System.out.println("--------MENU------------");
				System.out.println("\nHome Appliance Store");
				System.out.println("Choose from these options");
                System.out.println("1. List all products");
                System.out.println("2. Find a product by ID");
                System.out.println("3. Delete a product");
                System.out.println("4. Update a product");
                System.out.println("5. Add a product");
                System.out.println("6. Exit");
                System.out.println();
                
                System.out.print("Enter your Selection: ");
                
                try {
                	 choice = sc.nextInt();
                     sc.nextLine();
                     
                switch(choice) {
                case 1:
                	for(HomeAppliance appliance : obj.findAllProducts()) {
                		System.out.println(appliance);
                	}
                	break;
                	
                case 2:
                	System.out.print("Enter product ID to search: ");
                	int searchID = Integer.parseInt(sc.nextLine());
                	HomeAppliance ha = obj.findProduct(searchID);
                	System.out.println(ha);
                	break;
                	
                case 3:
                	 System.out.print("Enter product ID to delete: ");
                     int idDelete = Integer.parseInt(sc.nextLine());
                     obj.deleteProduct(idDelete);
                     System.out.println("Product deleted.");
                     break;
                     
                case 4:
                	System.out.print("Enter a product ID to update: ");
                	int idUpdate = Integer.parseInt(sc.nextLine());
                	System.out.print("Enter New SKU: ");
                    String sku = sc.nextLine();
                    System.out.print("Enter New Description: ");
                    String desc = sc.nextLine();
                    System.out.print("Enter New Category: ");
                    String cat = sc.nextLine();
                    System.out.print("Enter New Price: ");
                    int price = Integer.parseInt(sc.nextLine());
                    
                    obj.updateProduct(idUpdate, new HomeAppliance(sku, desc, cat, price));
                    System.out.println("Product updated.");
                    break;
                    
                case 5:
                	System.out.print("Enter SKU: ");
                    String skua = sc.nextLine();
                    System.out.print("Enter Description: ");
                    String desca= sc.nextLine();
                    System.out.print("Enter Category: ");
                    String cata = sc.nextLine();
                    System.out.print("Enter Price: ");
                    int pricea = Integer.parseInt(sc.nextLine());
                   
                    obj.addProduct(new HomeAppliance(skua, desca, cata, pricea));
                    System.out.println("Product added.");
                    break;
                    
                case 6:
                	System.out.println("Exiting.....");
                    
                default:
                	System.out.println("Selection is invalid, please enter again");
                    	}
                }catch(Exception e) {
                	e.printStackTrace();
                }
                
			}while (choice!=6);
				sc.close();
		}
	}


