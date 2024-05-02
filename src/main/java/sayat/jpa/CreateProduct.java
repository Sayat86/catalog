package sayat.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import sayat.jpa.entity.Category;
import sayat.jpa.entity.CharProduct;
import sayat.jpa.entity.Product;

import java.util.List;
import java.util.Scanner;

public class CreateProduct {
    public static void createProduct() {
        EntityManagerFactory factory = CentralFactory.createManager();
        EntityManager manager = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя продукта, который хотите создать");
        String productName = scanner.nextLine();
        System.out.println("Введите сумму продукта");
        int priceName = 0;

        while (true) {
            try {
                priceName = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введите число");
            }
        }

        TypedQuery<Category> categoryTypedQuery = manager.createQuery("select c from Category c", Category.class);
        List<Category> categoryList = categoryTypedQuery.getResultList();
        int count = 0;
        for (int i = 0; i < categoryList.size(); i++) {
            count++;
            System.out.println(count + ":" + categoryList.get(i).getName());
        }

        System.out.println("Введите категорию продукта");
        String categoryName = scanner.nextLine();

        Category category = manager.find(Category.class, categoryName);


                    while (category == null) {
                        System.out.println("Введите верную категорию");
                        categoryName = scanner.nextLine();
                        category = manager.find(Category.class, categoryName);
                    }

        try {
            manager.getTransaction().begin();

            Product product = new Product();
            product.setName(productName);
            product.setPrice(priceName);
            product.setCategory(category);
            manager.persist(product);

            for (int i = 0; i < category.getCharacteristics().size(); i++) {
                CharProduct charProduct = new CharProduct();
                System.out.println(category.getCharacteristics().get(i).getName());
                String valueName = scanner.nextLine();
                charProduct.setProduct(product);
                charProduct.setCharacteristic(category.getCharacteristics().get(i));
                charProduct.setValue(valueName);
                manager.persist(charProduct);
            }

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
        manager.close();
    }
}
