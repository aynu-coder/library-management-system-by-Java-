# Java Library Management System

A console-based library management program written in Java as a university learning project. It demonstrates basic object-oriented programming and simple book and member workflows.

## Project Overview

This project was created to practice Java fundamentals by modeling books, users, and an administrator, then connecting those classes through a menu-driven application. It is an educational example rather than a production-ready library system.

## Existing Features

- Add books with an ISBN, title, and author through the Admin menu.
- Display available books and borrowed books.
- Register a user with an ID, name, and phone number.
- Display registered users.
- Let a registered user attempt to borrow a book by ISBN.
- Display the books recorded as borrowed by a user.
- Return a book recorded as borrowed by that user.
- Calculate a fine using the number of late days entered by the user and a fixed rate of 10 Birr per day.
- Use checked exceptions for some missing-user and book lookup cases.

The program keeps these records in memory only. Its borrowing checks are limited: for example, it does not prevent an already-borrowed book from being assigned to another user.

## Technologies and Concepts

- **Java 21 source and target configuration** (see `nbproject/project.properties`).
- **Java Collections Framework:** `ArrayList` instances, referenced through the `List` interface, hold books and users.
- **Object-oriented programming:** classes represent books, people, users, and library operations.
- **Inheritance and abstraction:** `User` extends the abstract `Person` class.
- **Interfaces and method overriding:** `User` implements `FinePolicy` and provides its fine calculation and member-detail display.
- **Exception handling:** checked exceptions report some missing user and book cases.
- **Console input/output:** `Scanner` reads menu input and `System.out` displays prompts and results.
- **Apache Ant / NetBeans project files:** the project contains an Ant build file and NetBeans configuration.

The code uses some OOP concepts, but encapsulation is limited: several fields and methods are package-visible rather than private.

## Project Structure

```text
.
├── src/
│   └── Package/
│       ├── Admin.java
│       ├── Book.java
│       ├── BookNotAvailableException.java
│       ├── FinePolicy.java
│       ├── LibraryService.java
│       ├── Main.java
│       ├── Person.java
│       ├── User.java
│       └── UserNotFoundException.java
├── test/                 # No test source files are currently present
├── build/                # Existing compiled class files
├── nbproject/            # NetBeans project configuration
├── build.xml             # Ant build script
└── manifest.mf           # JAR manifest
```

### Main Java Classes

| Class | Responsibility |
|---|---|
| `Main` | Starts the application, displays the menus, reads console input, and calls `LibraryService`. Its `main` method is the Java entry point. |
| `LibraryService` | Holds the in-memory book and user lists and implements adding, listing, registering, borrowing, and returning operations. |
| `Book` | Stores a book's ISBN, title, author, and current borrowed state and borrower ID. |
| `Person` | Abstract base class containing a person's ID, name, phone number, and an abstract detail-display method. |
| `User` | Extends `Person`, displays user details, and calculates a fine according to `FinePolicy`. |
| `FinePolicy` | Defines the fine calculation method and the fixed rate of 10 Birr per late day. |
| `Admin` | Checks the administrator password, which is currently hard-coded in the source. |
| `BookNotAvailableException` | Checked exception used for some invalid book lookups. |
| `UserNotFoundException` | Checked exception used when a requested user ID is not found. |

## How the Application Works

`Main` reads the user's menu choice and calls methods on a single `LibraryService` instance. The service updates or searches its in-memory lists and prints results to the console.

### Main Menu

- **Admin:** asks for a password, then opens the Admin menu if it is accepted.
- **User:** opens the User menu.
- **Exit:** closes the application.

### Admin Menu

- **Add Book:** prompts for ISBN, title, and author, then adds a `Book` to the in-memory list.
- **Show available books:** displays books whose borrowed flag is false.
- **Show borrowed books:** displays books whose borrowed flag is true and the recorded borrower ID.
- **Display registered users:** displays the users currently in memory.
- **Exit:** returns to the main menu.

### User Menu

- **Register:** prompts for a numeric ID, name, and phone number, then adds a `User` to the in-memory list.
- **Borrow Book:** displays available books, asks for a user ID and ISBN, and attempts to record the borrow.
- **Return Book:** asks for a user ID, displays that user's recorded borrowed books, then asks for the ISBN and late-day count before returning it and printing the calculated fine.
- **View my borrowed books:** asks for a user ID and displays books recorded against it.
- **Exit:** returns to the main menu.

The menu's “available books” list is informational; the service does not check the book's borrowed flag before recording a new borrow. Also, the entered late-day count is not calculated from a due date because the application does not store loan dates.

## How to Run

You need a JDK capable of compiling Java 21 source code. The NetBeans project is configured for Java 21. From PowerShell, open the project folder and compile the Java source files:

```powershell
cd "C:\path\to\Library Management System"
javac -d "build\classes" src\Package\*.java
```

Then run the application:

```powershell
java -cp "build\classes" Package.Main
```

Replace the example path with the folder where you saved the project. The administrator password is hard-coded in `Admin.java`; this is suitable only as a classroom demonstration, not for real accounts.

**NetBeans note:** `nbproject/project.properties` currently names `library.management.system.LibraryManagementSystem` as the main class, but the source entry point is `Package.Main`. If NetBeans does not run the project, select `Package.Main` as the main class in the project run settings.

## Current Limitations

- **In-memory storage:** books, users, and borrowing state are lost when the application exits; there is no database or file persistence.
- **Console-only interface:** all interaction uses text menus and terminal input; there is no graphical interface.
- **Limited input validation:** IDs, ISBNs, names, phone numbers, and late days are not comprehensively validated. Input fields are read token by token, so names and titles containing spaces are not handled as full strings.
- **Limited borrowing checks:** the service does not prevent borrowing a book that is already marked borrowed, and recording another borrow can replace the borrower ID.
- **No loan dates or history:** due dates, actual overdue tracking, and completed borrowing history are not recorded. The user manually supplies late days.
- **No catalog maintenance or search:** there are no implemented search, update, or delete operations.
- **Demonstration authentication:** the Admin password is stored directly in source, and users are identified by an entered ID rather than authenticated accounts.
- **Limited testing:** the `test` folder contains no test source files.
- **NetBeans run configuration mismatch:** the configured main class does not match the main class found in `src/Package`.

## What I Learned

Developing this project helped me practice Java classes and objects, organizing related behavior into methods, using inheritance and interfaces, working with `ArrayList`, reading console input, and handling some error cases with checked exceptions. It also helped me see why validation, persistence, and clear separation between application logic and user interaction matter in a larger software project.

## Screenshot

<!-- Replace this placeholder with a screenshot after adding the image to the repository. -->

_Screenshot placeholder: add an image of the application running in a terminal._
