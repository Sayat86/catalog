package sayat.jpa;

import java.util.Scanner;

public class ApMain {
    public static void main(String[] args) {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Выберите действие");
            System.out.println("1 - создать категорию");
            System.out.println("2 - создать продукт");
            System.out.println("3 - удалить категорию");
            System.out.println("4 - удалить продукт");
            System.out.println("5 - обновить продукт");
            System.out.println("6 - выйти из программы");

            int num = Integer.parseInt(scanner.nextLine());

            if (num == 6) {
                System.out.println("Вы вышли из программы");
                break;
            }

            switch (num) {
                case 1:
                    System.out.println("создать категорию");
                    CreateCategory.createCategory();
                    break;

                case 2:
                    System.out.println("создать продукт");
                    CreateProduct.createProduct();
                    break;

                case 3:
                    System.out.println("удалить категорию");
                    DeleteCategory.deleteCategory();
                    break;

                case 4:
                    System.out.println("удалить продукт");
                    DeleteProduct.deleteProduct();
                    break;

                case 5:
                    System.out.println("обновить продукт");
                    UpdateProduct.updateProduct();
                    break;

                default:
                    System.out.println("Вариант не существует");
                    break;
            }
        }
    }
}
