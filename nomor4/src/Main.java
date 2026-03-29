class PhysicalMedia {
    protected String title;
    protected int releaseYear;
    protected double price;

    public PhysicalMedia(String title, int releaseYear, double price) {
        if (title.length() >= 255) {
            this.title = title.substring(0, 254);
        } else {
            this.title = title;
        }

        if (releaseYear > 1800 && releaseYear < 2026) {
            this.releaseYear = releaseYear;
        } else {
            this.releaseYear = 2025;
        }

        this.price = price;
    }

    public void getDescription() {
        System.out.println("Title: " + this.title);
        System.out.println("releaseYear: " + this.releaseYear);
        System.out.println("Price: " + (int)this.price);
    }
}

class Dvd extends PhysicalMedia {
    private double runtime;

    public Dvd(String title, int releaseYear, double price, double runtime) {
        super(title, releaseYear, price);

        if (runtime < 720) {
            this.runtime = runtime;
        } else {
            this.runtime = 719;
        }
    }

    @Override
    public void getDescription() {
        super.getDescription();
        System.out.println("Runtime: " + (int)this.runtime + " minutes\n");
    }

    public void screwRuntimeEndlessly() {
        this.runtime += 99999999999.0;
        screwRuntimeEndlessly();
    }
}

class Magazine extends PhysicalMedia {
    private String author;
    private int numPages;

    public Magazine(String title, int releaseYear, double price, String author, int numPages) {
        super(title, releaseYear, price);
        
        if (author.length() >= 50) {
            this.author = author.substring(0, 49);
        } else {
            this.author = author;
        }
        this.numPages = numPages;
    }

    @Override
    public void getDescription() {
        super.getDescription();
        System.out.println("Author: " + this.author);
        System.out.println("Number of Pages: " + this.numPages + "\n");
    }
}

class Vinyl extends PhysicalMedia {
    private int size;

    public Vinyl(String title, int releaseYear, double price, int size) {
        super(title, releaseYear, price);
        
        if (size <= 12) {
            this.size = size;
        } else {
            this.size = 12;
        }
    }

    @Override
    public void getDescription() {
        super.getDescription();
        System.out.println("Size in inches: " + this.size + "\n");
    }
}

public class Main {
    public static void main(String[] args) {
        Dvd item1 = new Dvd("Baby be Mine", 1982, 50000, 4);
        Magazine item2 = new Magazine("Nintendo Power #82", 1997, 25000, "Nintendo", 36);
        Vinyl item3 = new Vinyl("Song of The Wind", 1967, 350000, 12);

        item1.getDescription();
        item2.getDescription();
        item3.getDescription();

        System.out.println("Executing Bob's request to screw DVD runtime...");
        item1.screwRuntimeEndlessly();
    }
}