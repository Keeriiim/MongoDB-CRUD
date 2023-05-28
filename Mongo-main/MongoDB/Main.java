public class Main {

    public static void main(String[] args) {
        
       dbFacade db = new dbFacade();

       Person p1 = new Employee("Jerry", 26, "Street 1", "Employee", 1);
       Person p2 = new Customer("Jane", 30, "Street 2", "Customer", 1);
       Person p3 = new Customer("John", 25, "Street 3", "Customer",2);


        db.createPerson(p1);
        db.createPerson(p2);
        db.createPerson(p3);


        // Lists all person / one person from the database
        System.out.println(db.listPersons());
        System.out.println();

        // Gets the _id of a person
        String idOfPerson = db.printPersonIDFromDB(db.listPersons().get(2));
        System.out.println("Who is "+ idOfPerson + " ? : " + db.readPerson(idOfPerson));

        // Update a person in the database
        db.updatePerson(db.readPerson(idOfPerson));
        System.out.println(db.listPersons().get(2));
        System.out.println();




        // Deletes a person from the database by _id
       // db.deletePerson(idOfPerson);
        // System.out.println(db.listPersons());




    }
}
