import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private final int id;
    private final String title;
    private final String author;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}

class User {
    private final int id;
    private final String name;
    private final List<Book> borrowedBooks;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
}

class Library {
    private final List<Book> books;
    private final List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public Book getBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}

public class DigitalLibraryManagement {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Library library = new Library();

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> adminMenu();
                case 2 -> userMenu();
                case 3 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void adminMenu() {
        System.out.println("Admin Menu");
        System.out.println("1. Add Book");
        System.out.println("2. Add User");
        System.out.println("3. Delete User");
        System.out.println("4. Modify User");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> addBook();
            case 2 -> addUser();
            case 3 -> deleteUser(scanner);
            case 4 -> modifyUser(scanner);
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void addBook() {
        System.out.println("Enter Book ID:");
        int id = scanner.nextInt();
        System.out.println("Enter Book Title:");
        String title = scanner.next();
        System.out.println("Enter Book Author:");
        String author = scanner.next();
        library.addBook(new Book(id, title, author));
        System.out.println("Book added successfully.");
    }

    private static void addUser() {
        System.out.println("Enter User ID:");
        int id = scanner.nextInt();
        System.out.println("Enter User Name:");
        String name = scanner.next();
        library.addUser(new User(id, name));
        System.out.println("User added successfully.");
    }

    private static void deleteUser(Scanner scanner) {
        System.out.println("Enter User ID to delete:");
        int id = scanner.nextInt();
        User user = library.getUserById(id);
        if (user != null) {
            library.getUsers().remove(user);
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void modifyUser(Scanner scanner) {
        System.out.println("Enter User ID to modify:");
        int id = scanner.nextInt();
        User user = library.getUserById(id);
        if (user != null) {
            System.out.println("Enter new User Name:");
            String name = scanner.next();
            library.getUsers().remove(user);
            library.addUser(new User(id, name));
            System.out.println("User modified successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void userMenu() {
        System.out.println("User Menu");
        System.out.println("1. View Books");
        System.out.println("2. Search Book");
        System.out.println("3. Issue Book");
        System.out.println("4. Return Book");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> viewBooks();
            case 2 -> searchBook(scanner);
            case 3 -> issueBook(scanner);
            case 4 -> returnBook(scanner);
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void viewBooks() {
        List<Book> books = library.getBooks();
        for (Book book : books) {
            System.out.println("ID: " + book.getId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor());
        }
    }

    private static void searchBook(Scanner scanner) {
        System.out.println("Enter Book ID to search:");
        int id = scanner.nextInt();
        Book book = library.getBookById(id);
        if (book != null) {
            System.out.println("ID: " + book.getId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor());
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void issueBook(Scanner scanner) {
        System.out.println("Enter User ID:");
        int userId = scanner.nextInt();
        User user = library.getUserById(userId);
        if (user != null) {
            System.out.println("Enter Book ID to issue:");
            int bookId = scanner.nextInt();
            Book book = library.getBookById(bookId);
            if (book != null) {
                user.borrowBook(book);
                System.out.println("Book issued successfully.");
            } else {
                System.out.println("Book not found.");
            }
        } else {
            System.out.println("User not found.");
        }
    }

    private static void returnBook(Scanner scanner) {
        System.out.println("Enter User ID:");
        int userId = scanner.nextInt();
        User user = library.getUserById(userId);
        if (user != null) {
            System.out.println("Enter Book ID to return:");
            int bookId = scanner.nextInt();
            Book book = library.getBookById(bookId);
            if (book != null) {
                user.returnBook(book);
                System.out.println("Book returned successfully.");
            } else {
                System.out.println("Book not found.");
            }
        } else {
            System.out.println("User not found.");
        }
    }
}
