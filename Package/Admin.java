
package Package;

public class Admin {
    static final String PASSWORD = "1234";
    
    boolean authenticate(String userinput) {
        return (PASSWORD.equals(userinput));
    }
}
