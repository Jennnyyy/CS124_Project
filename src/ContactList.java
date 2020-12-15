import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ContactList {
    private static int hashTableSize = 3; // initial size of table
    private static ContactNode[] hashTable; // hash table
    private static int numContacts;// contact counter
    private static int numContactNodes;

    private class ContactNode<String, Contact> {
        public String key;
        public Contact contact;
        public ContactNode<String, Contact> next;

        public ContactNode(String key, Contact contact) {
            this.key = key;
            this.contact = contact;
        }

        @Override
        public java.lang.String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(key).append("'s Info:\n").append(contact.toString());
            return sb.toString();
        }
    }

    public ContactList() {
        hashTable = new ContactNode[hashTableSize];
        numContacts = 0;
        numContactNodes = 0;
        for (int i = 0; i < hashTable.length; i++)
            hashTable[i] = null;
    }

    private int hashValue(String s) {
        int value = Math.abs(s.hashCode());
        int index = value % hashTable.length;
        return index;
    }

    public void checkHashTable() {
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] == null)
                System.out.println("Empty, as it should be");
            else
                System.out.println("Items found");
        }
    }

    public void printSpecificPosition(int index) {
        ContactNode<String, Contact> current = hashTable[index];
        while (current != null) {
            System.out.println(current.toString());
            current = current.next;
        }
    }

    // run time: O(1)
    public boolean insert(String name, String number) // no duplicates
    {
        // update numContacts
        numContacts++;
        int index;
        String str;
        // create a new contact, using both name and number as key
        Contact contact = new Contact(name, number);
        for (int i = 0; i < 2; i++) {
            if (i == 0)
                str = name;
            else
                str = number;
            // figure out where this cNode belongs in the hashTable
            index = hashValue(str);
            // create a new cNode
            ContactNode<String, Contact> cNode = new ContactNode<>(str, contact);
            // place into hashtable if empty or place at front of existing linked list
            if (hashTable[index] == null)
                hashTable[index] = cNode;
            else {
                // set the next of the new cNode to the next of the placeHolder
                cNode.next = hashTable[index];
                // place this new node to be the next node after placeHolder
                hashTable[index] = cNode;
            }
            // keep track of the total number of nodes
            numContactNodes++;
        }

        if ((double) numContactNodes / hashTable.length >= 0.7)
            rehash();

        return true;
    }

    private void rehash() {
        // helper objects
        ContactNode[] temp = hashTable;
        ContactNode<String, Contact> current;
        HashMap<String, Contact> noRepeats = new HashMap<>();
        // reset global variables
        hashTableSize = nextPrime(hashTableSize);
        hashTable = new ContactNode[hashTableSize];
        numContacts = 0;
        numContactNodes = 0;
        // reinitialize hashTable
        for (int i = 0; i < hashTable.length; i++)
            hashTable[i] = null;
        // refill hashTable
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != null) {
                current = temp[i];
                while (current != null) {
                    if (!noRepeats.containsValue(current.contact))
                        insert(current.contact.getName(), current.contact.getNumber());
                    noRepeats.put(current.key, current.contact);
                    current = current.next;
                }
            }
        }
    }

    public int nextPrime(int oldIndex) {
        BigInteger b = new BigInteger(String.valueOf(oldIndex * 2));
        int newIndex = Integer.parseInt(b.nextProbablePrime().toString());
        return newIndex;
    }

 // run time: O(1)
 	public boolean delete(String nameORnumber) {

 		String str = nameORnumber;
 		
 		ContactNode<String, Contact> current;
 		
 		//prev doesn't exist yet so its null
 		ContactNode<String, Contact> prev = null;

 		// calling the hashvalue(str) to get a possible value
 		int possibleIndex = hashValue(str);

 		// nothing in array empty
 		if (hashTable[possibleIndex] == null)// for possible linked list that we wont be able to look inside of until
 		{
 			return false;
 		} 
 		else {
 			
 			current = hashTable[possibleIndex];
 			
 			// look into hashtable if the value is any of the keys
 			
 			while (current != null) 
 			{
 				if (current.key.equals(str)) 
 				{	
 					
 					
 					//we don't want to loose the rest of the chain
 					if( prev == null )
 					{
 						hashTable[possibleIndex] = current.next;
 					}
 					else
 					{
 						
 						//if it is the third one A B C and you decide you need to delete B
 						// You need to set prev which is A to C because current is B. 
 						//So B is gone. and the chain is still fixed to the right position.
 						prev.next = current.next;
 							
 					}
 					
 					//decrease numContacts in the LinkedList
 					numContacts--;
 					numContactNodes--;
 					
 					//look for the other contactNode with same object
 					
 					if (isNumeric(str) == true)
 					{
 						//i would need to delete the string name with object next 
 						delete(current.contact.getName());
 						
 					}
 					else
 					{
 						delete(current.contact.getNumber());
 					}
 					
 					return true;
 					
 					
 				}	
 				//to look for other contact Node should come first
				prev = current;
			
 				current = current.next;
 				
 			}//endofwhile

 		}
 		

 		return false;
 	}

 	// run time: O(1)
 	public String find(String nameORnumber) {

 		String str = nameORnumber;
 		
 		ContactNode<String, Contact> current;
 		ContactNode<String, Contact> prev;
 		
 		// look for possible index?
 		int possibleIndex = hashValue(nameORnumber);
 		
 		
 		// Isn't hashTable[possibleIndex].key an object not a string so how would this
 		
 		// nothing in array empty
 		if (hashTable[possibleIndex] == null)// for possible linked list that we wont be able to look inside of until
 		{
 			
 			return  str + " is not in contact list";

 		} 
 		else {

 			current = hashTable[possibleIndex];

 			// look into hashtable if the value is any of the keys

 			while (current != null) 
 			{
 				if (current.key.equals(str)) 
 				{

 					return str + " is in contact list";

 				}
 				
 				
 				current = current.next;
 				
 			}//endofwhileloop

 		}

 		return "Contact not found";
 	}

    // run time: O(1)
    public int size() {
        return hashTableSize;
    }

    // run time: O(1)
    public int numContactNodes() {
        return numContactNodes;
    }

    // run time: O(1)
    public int numContacts() {
        return numContacts;
    }

    // run time: O(N log N)
    public void printAllContacts() {
        HashMap<String, Contact> contacts = new HashMap<>();
        ContactNode<String, Contact> current;
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                current = hashTable[i];
                while (current != null) {
                    if (!isNumeric(current.key))
                        contacts.put(current.key, current.contact);
                    current = current.next;
                }
            }
        }
        ArrayList<String> sortedContacts = new ArrayList<>(contacts.keySet());
        Collections.sort(sortedContacts);
        for (String s : sortedContacts)
            System.out.println(contacts.get(s).toString());
    }

    // run time: O(N log N)
    public void searchAllContacts(String target) {
        HashMap<String, Contact> contacts = new HashMap<>();
        ContactNode<String, Contact> current;
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                current = hashTable[i];
                while (current != null) {
                    if (current.key.toLowerCase().contains(target.toLowerCase()))
                        contacts.put(current.key, current.contact);
                    current = current.next;
                }
            }
        }
        ArrayList<String> sortedContacts = new ArrayList<>(contacts.keySet());
        Collections.sort(sortedContacts);
        for (String s : sortedContacts)
            System.out.println(contacts.get(s).toString());
    }

    private static boolean isNumeric(String strNum) {
        try {
            long number = Long.parseLong(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		System.out.println(" This part 2 ");
		//ContactList 
		
		ContactList list1 = new ContactList();
		
		list1.insert("June","510-134-045");
		list1.insert("Lily","510-336-911");
		list1.insert("Leo","510-921-831");
		list1.insert("Andrew","512-114-415");
		list1.insert("Benjamin","520-994-085");
		list1.insert("Jacob","750-131-041");
		list1.insert("Lucas","750-211-911");
		list1.insert("Bryan","402-111-931");
		list1.insert("Andy","402-994-121");
		list1.insert("Barbara","520-994-085");
		
		System.out.println(" ");

		System.out.println("This is the number of Contact Nodes: ");
		list1.numContactNodes();
		
		System.out.println(" ");
		
		System.out.println("Number of Contacts: ");
		list1.numContacts();
		
		System.out.println(" ");
		
		System.out.println("Delete 510-921-831");
		list1.delete("510-921-831");
		
		
		System.out.println(" ");

		
		System.out.println("Number of Contact Nodes: ");
		list1.numContactNodes();
		
		
		System.out.println(" ");

		
		System.out.println("Number of Contacts: ");
		list1.numContacts();
		System.out.println(" ");
		
		
		
		System.out.println("Delete 510-921-831");
		list1.delete("510-921-831");
		
		
		
		System.out.println("Number of Contact Nodes: ");
		list1.numContactNodes();
		
		
		System.out.println(" ");

		
		System.out.println("Number of Contacts: ");
		list1.numContacts();
		
		/////////////////////////////////////////Find Method
		
		System.out.println(" ");
		System.out.println("Find..... ");

		
		System.out.println("Find George: ");
		list1.find("Benjamin");
		

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
		
		list1.delete("Benjamin");
		list1.delete("June");
		list1.delete("Lily");
		
		///////////////////Delete a contact that has already been deleted: 
		System.out.println(" We've deleted Lily but this is a double check: ");

		list1.delete("Lily");
		
		///////////////////
		System.out.println("Deleting...... ");

		list1.delete("Jacob");
		
		System.out.println(" ");

		list1.delete("520-994-085");
		System.out.println(" ");

		list1.delete("402-111-931");
		System.out.println(" ");

		list1.delete("402-994-121");
		

		

}
}

