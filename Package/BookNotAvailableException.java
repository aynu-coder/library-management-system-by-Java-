
package Package;

public class BookNotAvailableException extends Exception{
    BookNotAvailableException(String isbn) {
        super("Invalid ISBN: " +isbn);
    }
}
