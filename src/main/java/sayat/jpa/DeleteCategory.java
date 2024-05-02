package sayat.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sayat.jpa.entity.Category;
import sayat.jpa.entity.Product;

import java.util.Scanner;

public class DeleteCategory {
    public static void deleteCategory() {
        EntityManagerFactory factory = CentralFactory.createManager();
        EntityManager manager = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите ID категории продукта, которую хотите удалить");
        String categoryName = scanner.nextLine();

        Category category = manager.find(Category.class, categoryName);


        try {
            manager.getTransaction().begin();

            if (category.getProducts().size() != 0) {
                System.out.println("Операция не возможна");
            }
            else {
                for (int i = 0; i < category.getProducts().size(); i++) {
                    manager.remove(category.getProducts().get(i));
                }
                for (int i = 0; i < category.getCharacteristics().size(); i++) {
                    manager.remove(category.getCharacteristics().get(i));
                }
                manager.remove(category);
            }

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
        manager.close();
    }
}
