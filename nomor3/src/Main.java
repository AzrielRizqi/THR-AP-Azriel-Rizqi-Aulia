class Food {
    String name;
    Integer basePrice;

    public Food(String name, Integer basePrice) {

        if (basePrice < 1000000) {
            System.out.println("Error: " + name + " base price is too low! Auto-adjusting to 1,000,000.");
            this.basePrice = 1000000;
        } else {
            this.basePrice = basePrice;
        }
        this.name = name;
    }

    public Integer calcPrice() {
        return this.basePrice + 5000;
    }

    public void getInfo() {
        System.out.println("Name: " + this.name);
        System.out.println("Price: " + this.calcPrice());
        System.out.println();
    }
}

class RegularMenu extends Food {
    public RegularMenu(String name, Integer basePrice) {
        super(name, basePrice);
    }

    @Override
    public Integer calcPrice() {
        return super.calcPrice() + 10000;
    }
}

class SpecialMenu extends Food {
    public SpecialMenu(String name, Integer basePrice) {
        super(name, basePrice);
    }

    @Override
    public Integer calcPrice() {
        return super.calcPrice() + 20000;
    }
}

public class Main {
    public static void main(String[] args) {
        Food item1 = new Food("Beef Rendang", 15000);
        RegularMenu item2 = new RegularMenu("Chicken Ramen", 20000);
        SpecialMenu item3 = new SpecialMenu("Fiery Fried Rice", 80000);

        System.out.println("------------------------------------------------");
        
        // Printing the info
        item1.getInfo();
        item2.getInfo();
        item3.getInfo();
    }
}