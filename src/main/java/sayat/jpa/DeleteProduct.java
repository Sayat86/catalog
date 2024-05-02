package sayat.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sayat.jpa.entity.CharProduct;
import sayat.jpa.entity.Product;

import java.util.Scanner;

public class DeleteProduct {
    public static void deleteProduct() {
        EntityManagerFactory factory = CentralFactory.createManager();
        EntityManager manager = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите ID продукта, который хотите удалить");
        String productName = scanner.nextLine();
        
        Product product = manager.find(Product.class, productName);

        try {
            manager.getTransaction().begin();

            for (int i = 0; i < product.getCharProducts().size(); i++) {
                manager.remove(product.getCharProducts().get(i));
            }

            manager.remove(product);

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
        manager.close();
    }
}
