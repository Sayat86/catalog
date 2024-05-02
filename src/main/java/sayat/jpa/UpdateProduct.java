package sayat.jpa;

import jakarta.persistence.*;
import sayat.jpa.entity.CharProduct;
import sayat.jpa.entity.Characteristic;
import sayat.jpa.entity.Product;

import java.util.List;
import java.util.Scanner;

public class UpdateProduct {
    public static void updateProduct() {
        EntityManagerFactory factory = CentralFactory.createManager();
        EntityManager manager = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ID продукта, который хотите обновить");
        String prodId = scanner.nextLine();
        Product product = manager.find(Product.class, prodId);


        try {
            manager.getTransaction().begin();

            System.out.println("Введите имя продукта");
            String productName = scanner.nextLine();

            if (!productName.isEmpty()) {
                product.setName(productName);
            }
            else {
                System.out.println("Вы не ввели данные");
            }

            System.out.println("Введите цену продукта");
            String price = scanner.nextLine();

            if (price.isEmpty()) {
                System.out.println("Вы не ввели цену");
            }
            else {
                int priceName = Integer.parseInt(price);
                product.setPrice(priceName);
                manager.persist(product);
            }
            for (int i = 0; i < product.getCategory().getCharacteristics().size(); i++) {
                System.out.println(product.getCategory().getCharacteristics().get(i).getName());

                TypedQuery<CharProduct> charProductTypedQuery = manager.createQuery("select c from CharProduct c " +
                        "where c.characteristic.id = ?1 and c.product.id = ?2", CharProduct.class);
                charProductTypedQuery.setParameter(1, product.getCategory().getCharacteristics().get(i).getId());
                charProductTypedQuery.setParameter(2, product.getId());

                try {
                    CharProduct cProd = charProductTypedQuery.getSingleResult();
                    String productValue = scanner.nextLine();
                    cProd.setValue(productValue);
                } catch (NoResultException e) {
                    CharProduct charProduct = new CharProduct();
                    String valueName = scanner.nextLine();
                    charProduct.setProduct(product);
                    charProduct.setCharacteristic(product.getCategory().getCharacteristics().get(i));
                    charProduct.setValue(valueName);
                    manager.persist(charProduct);
                }
            }

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
        manager.close();
    }
}
