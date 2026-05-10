
package Package;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    List<Book> books = new ArrayList<>();
    List<User> users = new ArrayList<>();
    
    void addBook(Book book) {
        books.add(book);
    }
    
    int showAvailableBooks() {
        int count = 0;
            for(Book b : books) {
              if(!b.isBorrowed()) {
              b.displayBook();
              System.out.println("");
              count++;
              }
            }
            
        if(count == 0) {
            System.out.println("There is no any available book.");
        }
        return count;
    }
    
    void showBorrowedBooks() {
        int count = 0;
        for(Book b : books) {
            if(b.isBorrowed()) {
               b.displayBook();
                System.out.println("Borrowed by user with id: "+b.borrowedByUserId);
               System.out.println("");
               count++;
            }
        }
        if(count == 0) {
            System.out.println("There is no any borrowed book.");
        }
    }
    
    void showAllUsers() {
        int count = 0;
        for(User u : users) {
             u.displayDetails();
             System.out.println("");
             count++;
        }
        
        if(count == 0) {
            System.out.println("There are no registered users.");
        }
    }
    
    void registerUser(User user) {
        users.add(user);
    }
    
    void borrowBook(int userId, String isbn) throws UserNotFoundException, BookNotAvailableException {
        int countUser = 0;
        int countBook = 0;
        
         for(User u : users) {
            if(u.getId() == userId) {
                for(Book b : books) {
                 if(b.getIsbn().equals(isbn)) {
                     b.borrow(userId);
                     System.out.println("Book with ISBN: "+isbn+" is successfully borrowed");
                     countBook++;
                  }
                }
                if(countBook == 0) {
                    throw new BookNotAvailableException(isbn);//when the ISBN that the user entered is not on the list
                }
                countUser++;
            }
        }
        if(countUser == 0) {
            throw new UserNotFoundException(userId);
          }
        
    }
   
    void returnBook(int userId, String isbn, int lateDays) throws UserNotFoundException, BookNotAvailableException {
        int countUser = 0;
        int countBook = 0;
        
        for(User u : users) {
            if(u.getId() == userId) {
                for(Book b : books) {
                  if(b.borrowed == true && b.borrowedByUserId == userId) {
                    if(b.getIsbn().equals(isbn)) {
                        countBook++;
                        b.returnBook();
                        System.out.println("Book returned successfully");
                        System.out.println("Total fine: "+ u.calculateFine(lateDays) +" Birr");
                        break;
                    }
                  }
               }
                if(countBook == 0) {
                    throw new BookNotAvailableException(isbn);
                }
                countUser++;
            }
        }
        if(countUser == 0) {
                throw new UserNotFoundException(userId);
            }
    }
    
    int showUserBorrowedBooks(int userId) throws UserNotFoundException {
        int countUser = 0;
        int checkBorrow = 0;
        
        for(User u : users) {
             if(u.getId() == userId) {
                 for(Book b : books) {
                    if(b.borrowed == true && b.borrowedByUserId == userId) {
                        b.displayBook();
                        System.out.println("");
                        checkBorrow++;
                    }
                }
               countUser++;
             }
        }
        
        if(countUser == 0) {
            throw new UserNotFoundException(userId);
        }
        if(checkBorrow == 0) {
            System.out.println("You dont have borrowed books");
        }
        return checkBorrow;
    }
    
}
