public class Customer extends Person {
    private int customerID;

    public Customer(String name, int age, String address, String type, int customerID){
        super(name, age, address, "Customer");
        this.customerID = customerID;

    }

    @Override
    public String toString() {
        return super.toString() + ", costumerID = " + customerID;
    }

    /**
     * @param customerID the customerID to set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getCustomerID(){
        return customerID;
    }

    
    
}
