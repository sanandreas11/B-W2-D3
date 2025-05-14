import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Clienti
        Customer c1 = new Customer(1L, "Alice", 2);
        Customer c2 = new Customer(2L, "Bob", 1);

        // Prodotti
        Product p1 = new Product(1L, "Effective Java", "Books", 150.0);
        Product p2 = new Product(2L, "Java for Babies", "Baby", 40.0);
        Product p3 = new Product(3L, "Boy’s Jacket", "Boys", 60.0);
        Product p4 = new Product(4L, "Advanced Books", "Books", 90.0);
        Product p5 = new Product(5L, "Toy Car", "Boys", 30.0);

        // Ordini
        Order o1 = new Order(101L, "Delivered", LocalDate.of(2021, 2, 10),
                LocalDate.of(2021, 2, 20), List.of(p1, p2), c1);

        Order o2 = new Order(102L, "Shipped", LocalDate.of(2021, 3, 5),
                LocalDate.of(2021, 3, 15), List.of(p3, p4), c1);

        Order o3 = new Order(103L, "Processing", LocalDate.of(2021, 1, 25),
                LocalDate.of(2021, 2, 1), List.of(p5), c2);

        List<Order> orders = List.of(o1, o2, o3);

        // ---------------------------
        System.out.println("Esercizio 1:");
        List<Product> ex1 = orders.stream()
                .flatMap(o -> o.getProducts().stream())
                .filter(p -> "Books".equalsIgnoreCase(p.getCategory()) && p.getPrice() > 100)
                .distinct()
                .collect(Collectors.toList());
        ex1.forEach(System.out::println);

        // ---------------------------
        System.out.println("\nEsercizio 2:");
        List<Order> ex2 = orders.stream()
                .filter(o -> o.getProducts().stream()
                        .anyMatch(p -> "Baby".equalsIgnoreCase(p.getCategory())))
                .collect(Collectors.toList());
        ex2.forEach(System.out::println);

        // ---------------------------
        System.out.println("\nEsercizio 3:");
        List<Product> ex3 = orders.stream()
                .flatMap(o -> o.getProducts().stream())
                .filter(p -> "Boys".equalsIgnoreCase(p.getCategory()))
                .map(p -> new Product(p.getId(), p.getName(), p.getCategory(), p.getPrice() * 0.9))
                .distinct()
                .collect(Collectors.toList());
        ex3.forEach(System.out::println);

        // ---------------------------
        System.out.println("\nEsercizio 4:");
        LocalDate from = LocalDate.of(2021, 2, 1);
        LocalDate to = LocalDate.of(2021, 4, 1);

        List<Product> ex4 = orders.stream()
                .filter(o -> o.getCustomer().getTier() == 2)
                .filter(o -> !o.getOrderDate().isBefore(from) && !o.getOrderDate().isAfter(to))
                .flatMap(o -> o.getProducts().stream())
                .distinct()
                .collect(Collectors.toList());
        ex4.forEach(System.out::println);
    }
}

