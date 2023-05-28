import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class dbFacade {
    private MongoClient client;
    private MongoDatabase db;
    private Scanner scan;
    private Person person;
    private final String COLLECTION_NAME = "Persons";
    private final MongoCollection<Document> collection;

    public dbFacade() {
        // Create a new MongoDB connection
        this.client = MongoClients.create("mongodb://localhost:27017");
        // Get a reference to the "mydb" database
        this.db = client.getDatabase("myDB");

        // Get a reference to the "users" collection
        this.collection = db.getCollection(COLLECTION_NAME);
        this.scan = new Scanner(System.in);
    }



    // Create operation
    public void createPerson(Person person) {  // Takes in a person object that is either employee or costumer
        Document document = new Document("name", person.getName()) // Creates a new document with the person object data
                .append("age", person.getAge())
                .append("address", person.getAddress())
                .append("type", person.getType());

        if(collection.find(document).first() != null) { // Checks if the document already exists in the collection, if yes then stops the method createPerson
            System.out.println("Person already exists");
            return;
        }

        // If the person you want to add does not exist in the collection then it will check if the customerID or employeeID already exists
        int customerID = 0;
        int employeeID = 0;

         if(person instanceof Customer){ // If the person is a customer then it will add the customerID to the document
             Customer customer = (Customer) person;
             document.append("customerID", customer.getCustomerID());
             customerID = customer.getCustomerID();
         }
         else if(person instanceof Employee){ // If the person is an employee then it will add the employeeID to the document
             Employee employee = (Employee) person;
             document.append("employeeID", employee.getEmployeeID());
             employeeID = employee.getEmployeeID();
         }

         if (customerID != 0 && collection.find(Filters.eq("customerID", customerID)).first() != null) { // checks if the customerID already exist
             System.out.println("Person with this customer ID already exists, please change ID");
         }
         else if (employeeID != 0 && collection.find(Filters.eq("employeeID", employeeID)).first() != null) { // checks if the employeeID already exists
             System.out.println("Person with this employee ID already exists, please change ID");
         }

         else{
            collection.insertOne(document); // Insert the document into the collection
        }
    }

    // Find _id operation
    public String printPersonIDFromDB(Person person){
        Document document = new Document("name", person.getName()) // Creates a new document with the person object data
                .append("age", person.getAge())
                .append("address", person.getAddress())
                .append("type", person.getType());

        String personID = collection.find(document).first().getObjectId("_id").toString();

       // System.out.println(personID);
        return personID;

    }

    // Read operation
    public Person readPerson(String id){
        Document query = new Document("_id", new ObjectId(id)); // Creates a query to find the document with the same id as the person object
        Document result = collection.find(query).first();
        if (result == null) {
            System.out.println("Database is empty");
            return null;
        }
        String name = result.getString("name");
        int age = result.getInteger("age");
        String address = result.getString("address");
        String type = result.getString("type");

        if (type.equals("Customer")) { // If the person object is a costumer, add the costumerID to the document
            int costumerID = result.getInteger("customerID");
            return new Customer(name, age, address, "Customer", costumerID);
        } else if (type.equals("Employee")) { // If the person object is an employee, add the employeeID to the document
            int employeeID = result.getInteger("employeeID");
            return new Employee(name, age, address, "Employee", employeeID);
        }
        else {
            System.out.println("No matching type found");
            return null;
        }
    }

    // Update operation
    public void updatePerson(Person person) {

        Document filter = new Document("name", person.getName())
                .append("age", person.getAge())
                .append("address", person.getAddress())
                .append("type", person.getType());

        if (collection.find(filter).first() == null) {
            System.out.println("Person does not exist");
            return;
        }

        if ((person.getType().equals("Customer"))) {
            filter.append("customerID", ((Customer) person).getCustomerID());

        } else if (person.getType().equals("Employee")) {
            filter.append("employeeID", ((Employee) person).getEmployeeID());
        }

        System.out.println("What do u want to update?");
        System.out.println("1. Name");
        System.out.println("2. Age");
        System.out.println("3. Address");
        System.out.println("4. Switch type");
        System.out.println("5. change instance ID");

        Scanner scan = new Scanner(System.in);
        Document update;

        switch (scan.nextInt()) {
            case 1:
                System.out.println("Enter new name");
                scan.nextLine();
                String newName = scan.nextLine();
                update = new Document("$set", new Document("name", newName));
                collection.updateOne(filter, update);
                System.out.println("Name updated from " + person.getName() + " to " + newName);
                break;

            case 2:
                System.out.println("Enter new age");
                int newAge = scan.nextInt();
                update = new Document("$set", new Document("age", newAge));
                collection.updateOne(filter, update);
                System.out.println("Age updated from " + person.getAge() + " to " + newAge);
                break;

            case 3:
                System.out.println("Enter new address");
                scan.nextLine();
                String newAddress = scan.nextLine();
                update = new Document("$set", new Document("address", newAddress));
                collection.updateOne(filter, update);
                System.out.println("Address updated from " + person.getAddress() + " to " + newAddress);
                break;

            case 4:
                Document uniqueFilter = new Document("name", person.getName())
                        .append("age", person.getAge())
                        .append("address", person.getAddress());

                System.out.println("Before: " + person.getType());
                if (person instanceof Employee) { // If the person object is an employee, change the type to customer
                    int value = ((Employee) person).getEmployeeID();
                    System.out.println(value);
                    uniqueFilter.append("type", "Customer");
                    uniqueFilter.append("customerID", value);
                    collection.replaceOne(filter, uniqueFilter);
                    System.out.println(uniqueFilter.toString());
                    person = new Customer(person.getName(), person.getAge(), person.getAddress(), "Customer", value);
                    System.out.println("After: " + person.getType());
                }
                else if (person instanceof Customer) { // If the person object is a customer, change the type to employee
                    int value = ((Customer) person).getCustomerID();
                    System.out.println(value);
                    uniqueFilter.append("type", "Employee");
                    uniqueFilter.append("employeeID", value);
                    collection.replaceOne(filter, uniqueFilter);
                    System.out.println(uniqueFilter.toString());
                    person = new Employee(person.getName(), person.getAge(), person.getAddress(), "Employee", value);
                    System.out.println("After: " + person.getType());
                }
                break;

            case 5:
                System.out.print("Enter new ID: ");
                int changeID = scan.nextInt(); // Takes the new ID from the user

                if (person.getType().equals("Employee")) { // If the person object is an employee
                    // Check if the new ID already exists in the database
                    if (collection.find(Filters.eq("employeeID", changeID)).first() != null) {
                        System.out.println("ID already exists. Please enter a different ID.");
                    } else { // Add the employeeID to the document
                        // Update the ID
                        update = new Document("$set", new Document("employeeID", changeID));
                        collection.updateOne(filter, update);
                        System.out.println("Employee ID updated to: " + changeID);
                    }
                }
                else if (person.getType().equals("Customer")) { // If the person object is a customer
                    // Check if the new ID already exists in the database
                    if (collection.find(Filters.eq("customerID", changeID)).first() != null) {
                        System.out.println("ID already exists. Please enter a different ID.");
                    } else { // Add the customerID to the document
                        // Update the ID
                        update = new Document("$set", new Document("customerID", changeID));
                        collection.updateOne(filter, update);
                        System.out.println("Customer ID updated to: " + changeID);
                    }
                }
                break;
        }
        listPersons(); // Updates the list of persons
    }

    // Delete operation
    public void deletePerson(String id) {
        Document query = new Document("_id", new ObjectId(id)); // Creates a query to find the document with the same id as the person object
        Document result = collection.find(query).first();
        collection.deleteOne(result); // Deletes the document
        System.out.println("Person with the id: " + id + " was deleted");
    }


    public List<Person> listPersons() { // Lists all the persons in the database
        List<Person> persons = new ArrayList<>(); // Creates a list of persons
        for (Document doc : collection.find()) { // Check each doc inside the collection
            String name = doc.getString("name");
            int age = doc.getInteger("age");
            String address = doc.getString("address");
            String type = doc.getString("type");

            if (type.equals("Customer")) { // If the type is Costumer, add a new Costumer to the list
                persons.add(new Customer(name, age, address, type, doc.getInteger("customerID")));

            } else if (type.equals("Employee")) { // If the type is Employee, add a new Employee to the list
                persons.add(new Employee(name, age, address, type, doc.getInteger("employeeID")));

            }
        }
        return persons;
    }
}




    /*     public void updatePerson (Person person){ // Takes in a person object that is either employee or costumer
        Document query = new Document("_id", new ObjectId(); // Creates a query to find the document with the same id as the person object
        Document result = collection.find(query).first();
        if (result == null) {
            return null;
        }
        return new User(
                result.getString("name"),
                result.getString("email"),
                result.getInteger("age")
        );
    }


    public void deleteUserByEmail(String id) {
        Document query = new Document("_id", id); //
        collection.deleteOne(query);
    }
   */

