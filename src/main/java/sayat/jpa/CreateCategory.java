package sayat.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import sayat.jpa.entity.Category;
import sayat.jpa.entity.Characteristic;

import java.util.List;
import java.util.Scanner;

public class CreateCategory {
    public static void createCategory() {
        EntityManagerFactory factory = CentralFactory.createManager();
        EntityManager manager = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        try {
            manager.getTransaction().begin();

            System.out.println("Введите категорию, которую хотите создать");
            String categoryName = scanner.nextLine();


            TypedQuery<Long> categoryTypedQuery = manager.createQuery("select count(c.id) from Category c " +
                    "where c.name = ?1", Long.class);
            categoryTypedQuery.setParameter(1, categoryName);
            long categoryCount = categoryTypedQuery.getSingleResult();
            while (categoryCount > 0) {
                System.out.println("Категория уже существует");
                categoryName = scanner.nextLine();
                categoryTypedQuery.setParameter(1, categoryName);
                categoryCount = categoryTypedQuery.getSingleResult();
            }

            Category category = new Category();
            category.setName(categoryName);
            manager.persist(category);

            System.out.println("Введите характеристики");
            String charName = scanner.nextLine();
            String[] array = charName.split(",");

            for (int i = 0; i < array.length; i++) {
                Characteristic characteristic = new Characteristic();
                characteristic.setCategory_char(category);
                characteristic.setName(array[i]);
                manager.persist(characteristic);
            }

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException();
        }

        manager.close();
    }
}
