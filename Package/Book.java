
package Package;

public class Book {
    String isbn;
    String title;
    String author;
    boolean borrowed = false;
    int borrowedByUserId = -1;
    
    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }
    
    String getIsbn() {
        return isbn;
    }
    
    boolean isBorrowed() {
        return borrowed;
    }
    
    void borrow(int userID) {
        borrowed = true;
        borrowedByUserId = userID;
    }
    
    void returnBook() {
        borrowed = false;
        borrowedByUserId = -1;
    }
    
    void displayBook() {
        System.out.println("ISBN: " + isbn);
        System.out.println("Book title: " + title);
        System.out.println("Book author: " + author);
    }
}
