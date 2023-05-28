public class Employee extends Person{
    private int employeeID;

    public Employee(String name, int age, String address, String type, int employeeID){
        super(name, age, address, "Employee");
        this.employeeID = employeeID;
    }

    /**
     * @return the employeeID
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * @param employeeID the employeeID to set
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    @Override
    public String toString() {
        return super.toString() + ", employeeID =" + employeeID;
    }

    
    
}
