package sayat.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sayat.jpa.entity.Category;
import sayat.jpa.entity.Product;

import java.util.Scanner;

public class UpdateProductPrice {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите категорию продукта");
        String categoryName = scanner.nextLine();
        Category category = manager.find(Category.class, categoryName);

        System.out.println("Введите процент");
        int priceUp = Integer.parseInt(scanner.nextLine());

        try {
            manager.getTransaction().begin();

            for (int i = 0; i < category.getProducts().size(); i++) {
                int percent = category.getProducts().get(i).getPrice() * priceUp / 100;
                int totalPrice = percent + category.getProducts().get(i).getPrice();
                category.getProducts().get(i).setPrice(totalPrice);

                System.out.println("цена-" + category.getProducts().get(i).getPrice());
                System.out.println("общая цена-" + totalPrice);
            }

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
        manager.close();
        factory.close();
    }
}
