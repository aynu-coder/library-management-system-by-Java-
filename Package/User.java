
package Package;

public class User extends Person implements FinePolicy{
    public User(int id, String name, String phone) {
        super(id, name, phone);
    }
    
     @Override
     void displayDetails() {
         System.out.println("User id: " + getId());
         System.out.println("Name: " + name);
         System.out.println("Phone Number: " + phone);
     }
    
     @Override
     public double calculateFine(int lateDays) {
        return (lateDays * FINE_PER_DAY);
    }
}
