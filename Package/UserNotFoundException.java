
package Package;

public class UserNotFoundException extends Exception{
    UserNotFoundException(int id) {
        super("Invalid id: " +id);
    }
}
