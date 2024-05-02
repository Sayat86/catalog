package sayat.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import sayat.jpa.entity.CharProduct;
import sayat.jpa.entity.Characteristic;
import sayat.jpa.entity.Product;

import java.sql.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
//        String jdbcUrl = "jdbc:postgresql://localhost:5432/catalog_db";
//        String username = "postgres";
//        String password = "123456";
//        Connection connection = null;
//        try {
//            connection = DriverManager.getConnection(jdbcUrl, username, password);
//            String sqlQuery = "select avg(p.price_c) average from product_c p";
//            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.next();
//            System.out.println(resultSet.getString("average"));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

       // long productId = 4;
       // Product product = manager.find(Product.class, productId);
      //  System.out.println(product.getCategory().getName() + ":" + product.getName() + ":" + product.getPrice());

//        long categoryId = 2;
//        Product product1 = manager.find(Product.class, categoryId);
//        System.out.println(product1.getCategory().getName() + ":");
//        int average = 0;
//        int max = 0;
//        String productMax = "";
//
//        for (Product prod : product1.getCategory().getProducts()) {
//            System.out.println(prod.getName() + ":" + prod.getPrice() + "тг");
//            average = average + prod.getPrice();
//            if (prod.getPrice() > max) {
//                max = prod.getPrice();
//                productMax = prod.getName();
//            }
//        }
//        System.out.println("товар с максимальной ценой - " + productMax);
//        System.out.println("средняя цена - " + average / product1.getCategory().getProducts().size());

     //   long productId = 2;

     //   Product product2 = manager.find(Product.class, productId);
      //  CharProduct charProduct = manager.find(CharProduct.class, productId);
      //  Characteristic characteristic = manager.find(Characteristic.class, productId);

//        System.out.println("Category name - " + product2.getCategory().getName());
//
//        for (Product prod : product2.getCategory().getProducts()) {
//            System.out.println();
//            System.out.println("name - " + prod.getName());
//            System.out.println(prod.getCharProducts());
//            for (CharProduct prod2 : prod.getCharProducts()) {
//                System.out.println(prod2.getCharacteristic().getName() + ":" + prod2.getValue());
//            }
//        }

//        long productId = 4;
//
//        Product product3 = manager.find(Product.class, productId);
//        if (product3 != null) {
//            System.out.println(product3.getName());
//            for (CharProduct charProduct : product3.getCharProducts()) {
//                System.out.println(charProduct.getCharacteristic().getName() + ":" + charProduct.getValue());
//            }
//        }
//        else {
//            System.out.println("продукт не найден");
//        }


        try {
            manager.getTransaction().begin();

            int minPrice = 10000;
            int maxPrice = 80000;

            TypedQuery<Product> productTypedQuery = manager.createQuery("select p from Product p " +
                    "where p.price between ?1 and ?2", Product.class);
            productTypedQuery.setParameter(1, minPrice);
            productTypedQuery.setParameter(2, maxPrice);
            List<Product> productList = productTypedQuery.getResultList();
            for (Product product : productList) {
                System.out.println(product.getName());
                System.out.println(product.getPrice());
            }

            TypedQuery<Long> productsCountQuery = manager.createQuery("select count(p.id) from Product p", Long.class);
            long productsCount = productsCountQuery.getSingleResult();
            System.out.println(productsCount);

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException(e);
        }

        manager.close();
        factory.close();
    }
}
