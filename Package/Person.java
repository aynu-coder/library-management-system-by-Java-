
package Package;

public abstract class Person {
    int id;
    String name;
    String phone;
    
    public Person(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
    
    int getId() {
        return id;
    }
    
    abstract void displayDetails();
}
