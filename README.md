# MongoDB-CRUD

## The Program was designed to produce a CRUD operation with MongoDB as a local database.

### public void createPerson(Person person) {}
Takes in a person object that is either employee or costumer and adds it to the database

### public String printPersonIDFromDB(Person person) {}
Takes in a person object and returns the _id of the document with the same data as the person object

### public Person readPerson(String id) {}
Takes in a _id and returns the person object with the same _id

### public void updatePerson(Person person){}
Takes in a person object and updates the document by name, age, address, type, customerID or employeeID

### public void deletePerson(String id) {}
Takes in an id and deletes the document with the same id


### public List<Person> listPersons() {}
Updates the list of persons and returns it
