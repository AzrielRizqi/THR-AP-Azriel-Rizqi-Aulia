import java.util.Stack;
import java.util.Scanner;

// 1. BASE CLASS
class Book {
    protected String title;
    protected String author;
    protected int year;

    public Book(String title, String author, int year) {
        // TRAP 1: MUST ADD "AIUEO" at the end of the title
        String modifiedTitle = title + " AIUEO";
        
        // Constraint: Title must be under 255 characters
        if (modifiedTitle.length() >= 255) {
            this.title = modifiedTitle.substring(0, 254);
        } else {
            this.title = modifiedTitle;
        }

        // Constraint: Author must be under 50 characters
        if (author.length() >= 50) {
            this.author = author.substring(0, 49);
        } else {
            this.author = author;
        }

        // Constraint: 1800 < year < 2026
        if (year > 1800 && year < 2026) {
            this.year = year;
        } else {
            this.year = 2025; // Fallback to a valid year
        }
    }

    public void getInfo() {
        System.out.println("Title: " + this.title);
        System.out.println("Author: " + this.author);
        System.out.println("Year of Publication: " + this.year);
    }
}

// 2. SUBCLASS: GeneralBook
class GeneralBook extends Book {
    private String genre;

    public GeneralBook(String title, String author, int year, String genre) {
        super(title, author, year); // Constraint: Must use super keyword
        
        // Constraint: Genre is no more than 30 characters
        if (genre.length() > 30) {
            this.genre = genre.substring(0, 30);
        } else {
            this.genre = genre;
        }
    }

    @Override
    public void getInfo() {
        super.getInfo(); // Constraint: Must use super keyword
        System.out.println("Genre: " + this.genre);
    }
}

// 3. SUBCLASS: ChildrenBook
class ChildrenBook extends Book {
    private int minAge;
    private boolean hasVisualisation;

    public ChildrenBook(String title, String author, int year, int minAge, boolean hasVisualisation) {
        super(title, author, year);
        
        // Constraint: 3 < age < 12
        if (minAge > 3 && minAge < 12) {
            this.minAge = minAge;
        } else {
            this.minAge = 5; // Fallback to a valid age
        }
        this.hasVisualisation = hasVisualisation;
    }

    @Override
    public void getInfo() {
        super.getInfo();
        System.out.println("Minimum Age: " + this.minAge);
        // Converting boolean to "Yes/No" to match the exact output format
        System.out.println("Has Visualisation: " + (this.hasVisualisation ? "Yes" : "No"));
    }
}

// MAIN EXECUTION / CLI APP
public class Main {
    public static void main(String[] args) {
        // Constraint: MUST be stored in a stack
        Stack<Book> library = new Stack<>();
        Scanner scanner = new Scanner(System.in);

        // Pre-loading with the 3 required test cases...
        library.push(new Book("Why Black Moves First", "Wesley So", 2025));
        library.push(new GeneralBook("Inside Black Mesa", "Dr. Isaac Kleiner", 1997, "Documentary"));
        library.push(new ChildrenBook("Got Science?", "Rachel Dawes", 2015, 5, true));
        
        // TRAP 2: ...and adding 2 more to satisfy the "pre-load 5 books" rule
        library.push(new Book("The Enigma Machine", "Alan Turing", 1950));
        library.push(new GeneralBook("Oceanography", "Jacques Cousteau", 1970, "Science"));

        // TRAP 3: The exact Splash Screen requirement
        System.out.println("I HOPE YOU GET SCRATCHED IN THE FACE BY MY CAT, FATTY BOIS\n");
        System.out.println("Welcome to the Library of Alexandria Task Force System!");

        boolean running = true;
        while (running) {
            System.out.println("------------------------------------------------");
            System.out.println("1. View all books");
            System.out.println("2. Add a new basic book");
            System.out.println("3. Delete a specific book");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.println("------------------------------------------------");

            if (choice == 1) {
                if (library.isEmpty()) {
                    System.out.println("The library is currently empty.");
                } else {
                    for (int i = 0; i < library.size(); i++) {
                        System.out.println("--- Book ID: " + i + " ---");
                        library.get(i).getInfo();
                        System.out.println(); // Spacing
                    }
                }
            } else if (choice == 2) {
                System.out.print("Enter Title: ");
                String title = scanner.nextLine();
                System.out.print("Enter Author: ");
                String author = scanner.nextLine();
                System.out.print("Enter Year of Publication: ");
                int year = scanner.nextInt();
                
                library.push(new Book(title, author, year));
                System.out.println("Book added successfully!");
                
            } else if (choice == 3) {
                System.out.print("Enter the Book ID you want to delete: ");
                int id = scanner.nextInt();
                
                if (id >= 0 && id < library.size()) {
                    // Utilizing the fact that Stack extends Vector to delete a specific index
                    library.remove(id);
                    System.out.println("Book deleted successfully!");
                } else {
                    System.out.println("Invalid Book ID.");
                }
            } else if (choice == 4) {
                System.out.println("Shutting down system. Stay safe from the cat.");
                running = false;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}