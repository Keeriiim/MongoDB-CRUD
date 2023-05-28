public class Person{
    private String type;
    private String name;
    private int age;
    private String address;

public Person(String name, int age, String address, String type){
    this.name = name;
    this.age = age;
    this.address = address;
    this.type = type;

}

/**
 * @return the type
 */
public String getType() {
    return type;
}

/**
 * @param type the type to set
 */
public void setType(String type) {
    this.type = type;
}

/**
 * @return the name
 */
public String getName() {
    return name;
}

/**
 * @param name the name to set
 */
public void setName(String name) {
    this.name = name;
}

/**
 * @return the age
 */
public int getAge() {
    return age;
}

/**
 * @param age the age to set
 */
public void setAge(int age) {
    this.age = age;
}

/**
 * @return the address
 */
public String getAddress() {
    return address;
}

/**
 * @param address the address to set
 */
public void setAddress(String address) {
    this.address = address;
}

@Override
public String toString() {
    return "Person type = " + type + ", name = " + name + ", age = " + age + ", address = " + address;
}
    

    



}