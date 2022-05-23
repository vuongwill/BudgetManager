package budget;
import java.io.File;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Double> allPurchases = new LinkedHashMap<>();
        Map<String, Double> food = new LinkedHashMap<>();
        Map<String, Double> clothes = new LinkedHashMap<>();
        Map<String, Double> entertainment = new LinkedHashMap<>();
        Map<String, Double> other = new LinkedHashMap<>();


        double balance = 0;

        try {
            while (true){
                System.out.println("Choose your action:\n" +
                        "1) Add income\n" +
                        "2) Add purchase\n" +
                        "3) Show a list of purchases\n" +
                        "4) Balance\n" +
                        "5) Save\n" +
                        "6) Load\n" +
                        "7) Analyze (Sort)\n" +
                        "0) Exit");
                int input = Integer.parseInt(scanner.nextLine());
                if (input == 0) {
                    System.out.println("");
                    System.out.println("Bye!");
                    break;
                }
                switch (input) {
                    case 1:
                        System.out.println("");
                        System.out.println("Enter income:");
                        double income = Double.parseDouble(scanner.nextLine());
                        balance += income;
                        System.out.println("Income was added!");
                        System.out.println("");
                        break;
                    case 2:
                        while (true) {
                            System.out.println("");
                            System.out.println("Choose the type of purchase\n" +
                                    "1) Food\n" +
                                    "2) Clothes\n" +
                                    "3) Entertainment\n" +
                                    "4) Other\n" +
                                    "5) Back");
                            int categoryPurchase = Integer.parseInt(scanner.nextLine());
                            if (categoryPurchase == 5) {
                                break;
                            }
                            System.out.println("");
                            System.out.println("Enter purchase name:");
                            String item = scanner.nextLine();
                            System.out.println("Enter its price:");
                            double cost = Double.parseDouble(scanner.nextLine());
                            balance -= cost;
                            if (balance - cost < 0) {
                                balance = 0;
                            }
                            switch (categoryPurchase) {
                                case 1:
                                    allPurchases.put(item, cost);
                                    food.put(item, cost);
                                    continue;
                                case 2:
                                    allPurchases.put(item, cost);
                                    clothes.put(item, cost);
                                    continue;
                                case 3:
                                    allPurchases.put(item, cost);
                                    entertainment.put(item, cost);
                                    continue;
                                case 4:
                                    allPurchases.put(item, cost);
                                    other.put(item, cost);
                                    continue;
                            }
                        }
                        System.out.println("");
                        break;
                    case 3:
                        while (true) {
                            System.out.println("");
                            if (allPurchases.size() == 0) {
                                System.out.println("The purchase list is empty");
                            }
                            System.out.println("Choose the type of purchase\n" +
                                    "1) Food\n" +
                                    "2) Clothes\n" +
                                    "3) Entertainment\n" +
                                    "4) Other\n" +
                                    "5) All\n" +
                                    "6) Back");
                            int categoryView = Integer.parseInt(scanner.nextLine());
                            if (categoryView == 6) {
                                System.out.println("");
                                break;
                            }
                            System.out.println("");
                            switch (categoryView) {
                                case 1:
                                    System.out.println("Food:");
                                    food.forEach((items, price) -> System.out.printf("%s $%.2f%n", items, price));
                                    double foodSum = 0;
                                    for (Double i : food.values()) {
                                        foodSum += i;
                                    }
                                    System.out.printf("Total sum: %.2f%n", foodSum);
                                    continue;
                                case 2:
                                    System.out.println("Clothes:");
                                    clothes.forEach((items, price) -> System.out.printf("%s $%.2f%n", items, price));
                                    double clothesSum = 0;
                                    for (Double i : clothes.values()) {
                                        clothesSum += i;
                                    }
                                    System.out.printf("Total sum: %.2f%n", clothesSum);
                                    continue;
                                case 3:
                                    System.out.println("Entertainment:");
                                    entertainment.forEach((items, price) -> System.out.printf("%s $%.2f%n", items, price));
                                    double entertainmentSum = 0;
                                    for (Double i : entertainment.values()) {
                                        entertainmentSum += i;
                                    }
                                    System.out.printf("Total sum: %.2f%n", entertainmentSum);
                                    continue;
                                case 4:
                                    System.out.println("Other:");
                                    other.forEach((items, price) -> System.out.printf("%s $%.2f%n", items, price));
                                    double otherSum = 0;
                                    for (Double i : other.values()) {
                                        otherSum += i;
                                    }
                                    System.out.printf("Total sum: %.2f%n", otherSum);
                                    continue;
                                case 5:
                                    System.out.println("All:");
                                    allPurchases.forEach((items, price) -> System.out.printf("%s $%.2f%n", items, price));
                                    double allSum = 0;
                                    for (Double i : allPurchases.values()) {
                                        allSum += i;
                                    }
                                    System.out.printf("Total sum: %.2f%n", allSum);
                                    continue;
                            }
                        }
                        break;
                    case 4:
                        System.out.println("");
                        System.out.printf("Balance: $%.2f%n", balance);
                        System.out.println("");
                        break;
                    case 5:
                        System.out.println("");
                        System.out.println("Enter name of save");
                        String fileSave = scanner.nextLine();
                        File save = new File(fileSave + ".txt");
                        try (PrintWriter print = new PrintWriter(save)){
                            print.printf("Balance: $%.2f%n", balance);
                            print.println("");
                            double allSum = 0;
                            for (Double i : allPurchases.values()) {
                                allSum += i;
                            }
                            print.printf("All Purchases Total: $%.2f%n", allSum);
                            print.println("");
                            print.println("Food:");
                            for (var i : food.entrySet()) {
                                print.printf("%s $%.2f%n", i.getKey(), i.getValue());
                            }
                            double foodSum = 0;
                            for (Double i : food.values()) {
                                foodSum += i;
                            }
                            print.println("");
                            print.printf("Food Total: $%.2f%n", foodSum);
                            print.println("");
                            print.println("Clothes:");
                            for (var i : clothes.entrySet()) {
                                print.printf("%s $%.2f%n", i.getKey(), i.getValue());
                            }
                            double clothesSum = 0;
                            for (Double i : clothes.values()) {
                                clothesSum += i;
                            }
                            print.println("");
                            print.printf("Clothes Total: $%.2f%n", clothesSum);
                            print.println("");
                            print.println("Entertainment:");
                            for (var i : entertainment.entrySet()) {
                                print.printf("%s $%.2f%n", i.getKey(), i.getValue());
                            }
                            double entertainmentSum = 0;
                            for (Double i : entertainment.values()) {
                                entertainmentSum += i;
                            }
                            print.println("");
                            print.printf("Entertainment Total: $%.2f%n", entertainmentSum);
                            print.println("");
                            print.println("Other:");
                            for (var i : other.entrySet()) {
                                print.printf("%s $%.2f%n", i.getKey(), i.getValue());
                            }
                            print.println("");
                            double otherSum = 0;
                            for (Double i : other.values()) {
                                otherSum += i;
                            }
                            print.printf("Other Total: $%.2f%n", otherSum);
                        } catch (Exception e) {
                            System.out.println("File not created.");
                        }
                        System.out.println(fileSave + " was saved!");
                        System.out.println("");
                        break;
                    case 6:
                        System.out.println("");
                        System.out.println("Enter name of file to load");
                        String fileLoad = scanner.nextLine();
                        File file = new File(fileLoad + ".txt");
                        try (Scanner load = new Scanner(file)) {
                            while (load.hasNext()) {
                                String temp = load.nextLine();
                                if (temp.contains("Balance:")) {
                                    Double budget = Double.parseDouble(temp.substring(temp.lastIndexOf("$") + 1));
                                    balance = budget;
                                }
                                if (temp.contains("Food:")) {
                                    while (load.hasNextLine()) {
                                        temp = load.nextLine();
                                        if (temp.isEmpty()) {
                                            break;
                                        }
                                        String item = temp.substring(0, temp.lastIndexOf("$") - 1);
                                        double cost = Double.parseDouble(temp.substring(temp.lastIndexOf("$") + 1));
                                        food.put(item, cost);
                                        allPurchases.put(item, cost);
                                    }
                                }
                                if (temp.contains("Clothes:")) {
                                    while (load.hasNextLine()) {
                                        temp = load.nextLine();
                                        if (temp.isEmpty()) {
                                            break;
                                        }
                                        String item = temp.substring(0, temp.lastIndexOf("$") - 1);
                                        double cost = Double.parseDouble(temp.substring(temp.lastIndexOf("$") + 1));
                                        clothes.put(item, cost);
                                        allPurchases.put(item, cost);
                                    }
                                }
                                if (temp.contains("Entertainment:")) {
                                    while (load.hasNextLine()) {
                                        temp = load.nextLine();
                                        if (temp.isEmpty()) {
                                            break;
                                        }
                                        String item = temp.substring(0, temp.lastIndexOf("$") - 1);
                                        double cost = Double.parseDouble(temp.substring(temp.lastIndexOf("$") + 1));
                                        entertainment.put(item, cost);
                                        allPurchases.put(item, cost);
                                    }
                                }
                                if (temp.contains("Other:")) {
                                    while (load.hasNextLine()) {
                                        temp = load.nextLine();
                                        if (temp.isEmpty()) {
                                            break;
                                        }
                                        String item = temp.substring(0, temp.lastIndexOf("$") - 1);
                                        double cost = Double.parseDouble(temp.substring(temp.lastIndexOf("$") + 1));
                                        other.put(item, cost);
                                        allPurchases.put(item, cost);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        System.out.println(fileLoad + " was loaded!");
                        System.out.println("");
                        break;
                    case 7:
                        System.out.println("");

                        Map<String, Double> map = new LinkedHashMap<>();
                        Map<String, Double> sortedCategory = new LinkedHashMap<>();

                        double foodSum = 0;
                        for (double i : food.values()) {
                            foodSum += i;
                        }
                        map.put("Food", foodSum);
                        double clothesSum = 0;
                        for (double i : clothes.values()) {
                            clothesSum += i;
                        }
                        map.put("Clothes", clothesSum);
                        double entertainmentSum = 0;
                        for (double i : entertainment.values()) {
                            entertainmentSum += i;
                        }
                        map.put("Entertainment", entertainmentSum);
                        double otherSum = 0;
                        for (double i : other.values()) {
                            otherSum += i;
                        }
                        map.put("Other", otherSum);

                        while (true) {
                            System.out.println("How do you want to sort?\n" +
                                "1) Sort all purchases\n" +
                                "2) Sort by type\n" +
                                "3) Sort by certain type\n" +
                                "4) Back");
                            int sort = Integer.parseInt(scanner.nextLine());
                            if (sort == 4) {
                                System.out.println("");
                                break;
                            }
                            switch (sort) {
                                case 1:
                                    if (allPurchases.isEmpty()) {
                                        System.out.println("");
                                        System.out.println("The purchase list is empty!");
                                        System.out.println("");
                                        break;
                                    }
                                    LinkedHashMap<String, Double> sortedItem = new LinkedHashMap<>();
                                    System.out.println("");
                                    allPurchases.entrySet().stream()
                                            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                            .forEachOrdered(i -> sortedItem.put(i.getKey(), i.getValue()));
                                    System.out.println("All:");
                                    for (var i : sortedItem.entrySet()) {
                                        System.out.printf("%s $%.2f%n", i.getKey(), i.getValue());
                                    }
                                    System.out.println("");
                                    continue;
                                case 2:
                                    map.entrySet().stream()
                                            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                            .forEachOrdered(i -> sortedCategory.put(i.getKey(), i.getValue()));
                                    System.out.println("");
                                    for (var i : sortedCategory.entrySet()) {
                                        System.out.printf("%s - $%.2f%n", i.getKey(), i.getValue());
                                    }
                                    System.out.println("");
                                    continue;
                                case 3:
                                    System.out.println("");
                                    System.out.println("Choose the type of purchase\n" +
                                            "1) Food\n" +
                                            "2) Clothes\n" +
                                            "3) Entertainment\n" +
                                            "4) Other");
                                    int sortCategory = Integer.parseInt(scanner.nextLine());
                                    switch (sortCategory) {
                                        case 1:
                                            if (food.isEmpty()) {
                                                System.out.println("");
                                                System.out.println("The purchase list is empty!");
                                                System.out.println("");
                                                break;
                                            }
                                            Map<String, Double> sortedFood = new LinkedHashMap<>();
                                            food.entrySet().stream()
                                                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                                    .forEachOrdered(i -> sortedFood.put(i.getKey(), i.getValue()));
                                            System.out.println("");
                                            System.out.println("Food:");
                                            for (var i : sortedFood.entrySet()) {
                                                System.out.printf("%s $%.2f%n", i.getKey(), i.getValue());
                                            }
                                            System.out.printf("Total: $%.2f%n", foodSum);
                                            System.out.println("");
                                            break;
                                        case 2:
                                            if (clothes.isEmpty()) {
                                                System.out.println("");
                                                System.out.println("The purchase list is empty!");
                                                System.out.println("");
                                                break;
                                            }
                                            Map<String, Double> sortedClothes = new LinkedHashMap<>();
                                            clothes.entrySet().stream()
                                                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                                    .forEachOrdered(i -> sortedClothes.put(i.getKey(), i.getValue()));
                                            System.out.println("");
                                            System.out.println("Clothes:");
                                            for (var i : sortedClothes.entrySet()) {
                                                System.out.printf("%s $%.2f%n", i.getKey(), i.getValue());
                                            }
                                            System.out.printf("Total: $%.2f%n", clothesSum);
                                            System.out.println("");
                                            break;
                                        case 3:
                                            if (entertainment.isEmpty()) {
                                                System.out.println("");
                                                System.out.println("The purchase list is empty!");
                                                System.out.println("");
                                                break;
                                            }
                                            Map<String, Double> sortedEntertainment = new LinkedHashMap<>();
                                            entertainment.entrySet().stream()
                                                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                                    .forEachOrdered(i -> sortedEntertainment.put(i.getKey(), i.getValue()));
                                            System.out.println("");
                                            System.out.println("Entertainment:");
                                            for (var i : sortedEntertainment.entrySet()) {
                                                System.out.printf("%s $%.2f%n", i.getKey(), i.getValue());
                                            }
                                            System.out.printf("Total: $%.2f%n", entertainmentSum);
                                            System.out.println("");
                                            break;
                                        case 4:
                                            if (other.isEmpty()) {
                                                System.out.println("");
                                                System.out.println("The purchase list is empty!");
                                                System.out.println("");
                                                break;
                                            }
                                            Map<String, Double> sortedOther = new LinkedHashMap<>();
                                            other.entrySet().stream()
                                                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                                    .forEachOrdered(i -> sortedOther.put(i.getKey(), i.getValue()));
                                            System.out.println("");
                                            System.out.println("Other:");
                                            for (var i : sortedOther.entrySet()) {
                                                System.out.printf("%s $%.2f%n", i.getKey(), i.getValue());
                                            }
                                            System.out.printf("Total: $%.2f%n", otherSum);
                                            System.out.println("");
                                            break;
                                    }
                            }
                        }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void expenses() {
        Map<String, Double> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        List<String> split = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        double sum = 0;
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            list.add(input);
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isEmpty()) {
                list.remove(i);
            }
        }
        for (String i : list) {
            System.out.println(i);
        }
        split = List.copyOf(list);
        for (String i : split) {
            i = i.substring(i.indexOf("$") + 1);
            sum += Double.parseDouble(i);
        }
        System.out.printf("Total: $%.2f", sum);
    }
}
