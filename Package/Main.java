
package Package;

import java.util.Scanner;

public class Main {
    static LibraryService service = new LibraryService();
    static Scanner scan = new Scanner(System.in);
    static int selection;
    
    public static void mainMenu() { 
        Admin admin = new Admin();
        String password;
        
        System.out.println("    Choose ");
        System.out.println("1, Admin");
        System.out.println("2. User");
        System.out.println("3. Exit");
        selection = scan.nextInt();
        
        switch(selection) {
            case 1: 
                System.out.print("Enter the password: ");
                password = scan.next();
                if(admin.authenticate(password)) {
                    adminMenu();
                }
                else {
                    System.out.println("Wrong password!");
                    mainMenu();
                }
                break;
            case 2:
                userMenu();
                break;
            case 3:
                System.exit(0);
        }
    }
    
    public static void userMenu() {
        System.out.println("    Choose ");
        System.out.println("1, Register");
        System.out.println("2. Borrow Book");
        System.out.println("3. Return Book");
        System.out.println("4. View my borrowed books");
        System.out.println("5. Exit");
        
        userOperation();
    }
    
    public static void userOperation() {
        int id;
        int lateDay;
        String name;
        String phone;
        String isbn;
        
        selection = scan.nextInt();
        switch(selection) {
            case 1: 
                System.out.print("Enter id(Num only): ");
                id = scan.nextInt();
                System.out.print("Enter your name: ");
                name = scan.next();
                System.out.print("Enter your phone number: ");
                phone = scan.next();
                
                service.registerUser(new User(id, name, phone));
                userMenu();
                break;
            case 2:
                System.out.println("Here is a list of available books.");
                
                if(!(service.showAvailableBooks() == 0)) {
                    System.out.print("Enter your id: ");
                    id = scan.nextInt();
                    System.out.print("Enter the ISBN: ");
                    isbn = scan.next();
                    
                    try {
                        service.borrowBook(id, isbn);
                    }
                    catch (UserNotFoundException e) {
                        System.out.println(e);
                    }
                    catch (BookNotAvailableException e) {
                        System.out.println(e);
                    }
                }
                userMenu();
                break;
            case 3:
                System.out.print("Enter your id: ");
                id = scan.nextInt();
                System.out.println("    List of borrowed books");
                
                try {
                    if(!(service.showUserBorrowedBooks(id) == 0)) {
                        System.out.print("Enter the ISBN: ");
                        isbn = scan.next();
                        System.out.print("Enter late day(0 if you don't have): ");
                        lateDay = scan.nextInt();
                        service.returnBook(id, isbn, lateDay);
                    }   
                }
                catch (UserNotFoundException e) {
                    System.out.println(e);
                }
                catch (BookNotAvailableException e) {
                    System.out.println(e);
                }
                userMenu();
                break;
            case 4:
                System.out.print("Enter your id: ");
                id = scan.nextInt();
                
                try {
                    System.out.println("    Here is a list of your borrowed books.");
                    service.showUserBorrowedBooks(id);
                }
                catch (UserNotFoundException e) {
                    System.out.println(e);
                }
                userMenu();
                break;
            case 5:
                mainMenu();
                
        }
    }
    
    public static void adminMenu() {
        System.out.println("    Choose ");
        System.out.println("1, Add Book");
        System.out.println("2. Show available books");
        System.out.println("3. Show borrowed books");
        System.out.println("4. Display registered users");
        System.out.println("5. Exit");
        
        adminOperation();
    }
    
    public static void adminOperation() {
        String isbn;
        String title;
        String author;
        
        selection = scan.nextInt();
        switch(selection) {
            case 1: 
                System.out.print("Enter the ISBN Number: ");
                isbn = scan.next();
                System.out.print("Enter the Title: ");
                title = scan.next();
                System.out.print("Enter the Author: ");
                author = scan.next();
                
                service.addBook(new Book(isbn, title, author));
                adminMenu();
                break;
            case 2: 
                System.out.println("    Here is a list of available books");
                service.showAvailableBooks();
                adminMenu();
                break;
            case 3:
                System.out.println("    Here is a list of borrowed boooks");
                service.showBorrowedBooks();
                adminMenu();
                break;
            case 4:
                System.out.println("    Here is a list of registered users");
                service.showAllUsers();
                adminMenu();
                break;
            case 5:
                mainMenu();
                break;
        }
    }
    
    public static void main(String args[]) {
        mainMenu();
    }
}
