
public class Main {

	
	
	 public static void main(String[] args) {
			// TODO Auto-generated method stub

			 System.out.println(" This part 3 ");
				//ContactList 
				
				ContactList list1 = new ContactList();
				
				list1.insert("June","5101340451");
				list1.insert("Lily","5103306911");
				list1.insert("Leo","51092183195");
				list1.insert("Andrew","512114415");
				list1.insert("Benjamin","520994085");
				list1.insert("Jacob","750131041");
				list1.insert("Lucas","750211911");
				list1.insert("Bryan","402111931");
				list1.insert("Andy","402994121");
				list1.insert("Barbara","520694099");
				
				System.out.println(" ");

				System.out.println("This is the number of Contact Nodes: ");
				System.out.println(list1.numContactNodes());
				
				System.out.println(" ");
				
				System.out.println("Number of Contacts: ");
				System.out.println(list1.numContacts());
				System.out.println(" ");

				
				////////////////Deleting contact 
				System.out.println("Deleting... ");

				System.out.print("(510)330-6911...");
				System.out.println(list1.delete("5103306911"));
				
				
				
				
				System.out.println(" ");

				///////////////Update on Contact Nodes
				
				System.out.println("Number of Contact Nodes: ");
				System.out.println(list1.numContactNodes());
				
				
				System.out.println(" ");

				
				System.out.println("Number of Contacts: ");
				System.out.println(list1.numContacts());
				System.out.println(" ");
				
				
				System.out.println("Deleting... ");

				System.out.print("510-134-0451...");
				System.out.println(list1.delete("5101340451"));
				
				System.out.println(" ");

				
				System.out.println("Number of Contact Nodes: ");
				System.out.println(list1.numContactNodes());
				
				
				System.out.println(" ");

				
				System.out.println("Number of Contacts: ");
				System.out.println(list1.numContacts());
				
				/////////////////////////////////////////Find Method
				
				System.out.println(" ");
				System.out.println("Find Method..... ");
				System.out.println(" ");

				
				System.out.println("Find George: ");
				System.out.println(list1.find("George"));
				
				
				System.out.println(" ");
				
				System.out.println("Find 750131041: ");
				System.out.println(list1.find("750131041"));
				
				System.out.println(" ");

				
				//////////////////////////////////////////////////
				
				System.out.println("Search All Contacts Method: ");
				
				System.out.println(" ");
				System.out.println("Searching for 402");
				
				list1.searchAllContacts("402");
				
				
				
				System.out.println(" ");
				System.out.println("Searching for all contacts thats start with 'L' :");
				
				list1.searchAllContacts("L");
				
				
				//////////////////////////////////////////////
				
				System.out.println(" ");
				System.out.println("Deleting..... ");
				
				System.out.print("Benjamin...");

				System.out.print(list1.delete("Benjamin"));
				System.out.println(" ");

			
				System.out.print("June... ");

				System.out.print(list1.delete("June"));

				System.out.println(" ");

				
				System.out.print("Lily... ");

				System.out.print(list1.delete("Lily"));
				
				System.out.println(" ");
				System.out.println(" ");


				///////////////////Delete a contact that has already been deleted: 
				System.out.println("We've deleted Lily but this is a double check: ");
				System.out.println(" ");

				System.out.println("Deleting...... ");

				System.out.print("Lily... ");

				System.out.println(list1.delete("Lily"));
				
				System.out.println(" ");

				System.out.println(" ");

				///////////////////
				
				System.out.println("Deleting...... ");
				System.out.println(" ");

				System.out.print("Jacob... ");

				System.out.println(list1.delete("Jacob"));
				

				System.out.print("520994085... ");

				System.out.println(list1.delete("520994085"));

				System.out.print("402111931...");

				System.out.println(" ");
				
				

				System.out.print("402994121...");

				System.out.print(list1.delete("402994121"));

				System.out.println(" ");
				System.out.println(" ");

				System.out.println("Print all contacts ");
				list1.printAllContacts();
				


			

			

	}

}
