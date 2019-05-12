package sda.lukaszs.myjdbcproject;

import org.springframework.stereotype.Repository;
import sda.lukaszs.myjdbcproject.manager.RepositoryManager;
import sda.lukaszs.myjdbcproject.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.log.Slf4JLogger");
        new HibernateInit();
        HibernateExample();
        //JDBCExample();
    }

    private static void JDBCExample() throws SQLException {
        //Scanner scanner = new Scanner(System.in);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/jdbcproject?serverTimezone=UTC&logger=com.mysql.cj.log.Slf4JLogger&profileSQL=true","jdbcproject","jdbcproject123");
        /*StatementExample.createTableExample(connection);
        StatementExample.insertExample(connection);
        StatementExample.insertWithParametersExample(connection,"Jan Kowalski",3500);*/

        /*System.out.print("Podaj próg zarobku: ");
        int salary = scanner.nextInt();
        StatementExample.deleteExample(connection,salary);
        StatementExample.deleteByIDExample(connection,0);*/

        for(Employee employee : StatementExample.selectAllExample(connection)){
            System.out.println(employee);
        }
        System.out.println();
        for(Employee employee : StatementExample.selectAllByName(connection,"Jan Kowalski")){
            System.out.println(employee);
        }
        System.out.println();
        for(Employee employee : StatementExample.selectAllByName(connection,"1' or '1'='1")){
            System.out.println(employee);
        }

        connection.close();
        //scanner.close();
    }

    private static void HibernateExample() {
        Scanner scanner = new Scanner(System.in);
        //zad_9(scanner);
        //testUsers();
        testProducts();
        scanner.close();
    }

    private static void testProducts(){
        //KLIENCI
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Adam","Adamowski"));
        customers.add(new Customer("Barbara","Barbarowicz"));
        customers.add(new Customer("Cezary","Cezarowski"));
        customers.get(0).setDetails(new CustomerDetails("Jagiellońska 6","85-123","Bydgoszcz","+48654923122"));
        customers.get(1).setDetails(new CustomerDetails("Żeglarska 16","81-423","Gdańsk","0522341223"));
        customers.get(2).setDetails(new CustomerDetails("Morska 1","35-123","Poznań","512909231"));
        RepositoryManager.getInstance().getCustomerRepository().addList(customers);

        //PRODUKTY I KATEGORIE
        ProductCategory beer = new ProductCategory("Piwo");
        ProductCategory whisky = new ProductCategory("Whisky");
        ProductCategory vodka = new ProductCategory("Wódka");
        ProductCategory wine = new ProductCategory("Wino");
        List<ProductCategory> categories = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        categories.add(beer);
        categories.add(whisky);
        categories.add(vodka);
        categories.add(wine);
        products.add(new Product("TYSKIE GRONIE / 0,5L",10.00,beer));
        products.add(new Product("GROLSCH / 0,45L",10.00,beer));
        products.add(new Product("HEINEKEN / 0,5L",10.00,beer));
        products.add(new Product("CYDR GRÓJECKI / 0,4L",6.00,beer));
        products.add(new Product("HIGHLAND RESERVE /BUT. 0,7L",150.00,whisky));
        products.add(new Product("JOHNNIE WALKER RED /BUT. 0,7L",200.00,whisky));
        products.add(new Product("BALLANTINES /BUT. 0,7L",200.00,whisky));
        products.add(new Product("CHIVAS REGAL /BUT. 0,7L",240.00,whisky));
        products.add(new Product("WÓDKA WYBOROWA /BUT. 0,5L",60.00,vodka));
        products.add(new Product("WÓDKA BOLS /BUT. 0,5L",60.00,vodka));
        products.add(new Product("WÓDKA FINLANDIA /BUT. 0,5L",80.00,vodka));
        products.add(new Product("SENSI BIAŁE PÓŁWYTR.",40.00,wine));
        products.add(new Product("TENTA BIAŁE PÓŁWYTR.",40.00,wine));
        products.add(new Product("PROSECCO MIONETTO",60.00,wine));
        products.add(new Product("CARLO ROSSI BIAŁE PÓŁWYTR. /BUT. 0,75L",50.00,wine));
        products.add(new Product("CARLO ROSSI CZERWONE PÓŁWYTR. /BUT. 0,75L",50.00,wine));
        RepositoryManager.getInstance().getProductCategoryRepository().addList(categories);
        RepositoryManager.getInstance().getProductRepository().addList(products);

        //ZAMÓWIENIA
        ShoppingOrder order1 = new ShoppingOrder();
        ShoppingOrder order2 = new ShoppingOrder();
        ShoppingOrder order3 = new ShoppingOrder();
        ShoppingOrder order4 = new ShoppingOrder();
        ShoppingOrder order5 = new ShoppingOrder();
        order1.setCustomer(RepositoryManager.getInstance().getCustomerRepository().getById(1));
        order2.setCustomer(RepositoryManager.getInstance().getCustomerRepository().getById(2));
        order3.setCustomer(RepositoryManager.getInstance().getCustomerRepository().getById(2));
        order4.setCustomer(RepositoryManager.getInstance().getCustomerRepository().getById(3));
        order5.setCustomer(RepositoryManager.getInstance().getCustomerRepository().getById(3));

        //TREŚĆ ZAMÓWIEŃ
        order1.addProduct(products.get(1),3);
        order1.addProduct(products.get(2),1);
        order1.addProduct(products.get(3),5);
        order1.addProduct(products.get(4),2);
        order2.addProduct(products.get(1),3);
        order2.addProduct(products.get(2),1);
        order2.addProduct(products.get(4),5);
        order2.addProduct(products.get(6),2);
        order2.addProduct(products.get(8),5);
        order2.addProduct(products.get(10),2);
        order3.addProduct(products.get(1),3);
        order3.addProduct(products.get(2),6);
        order3.addProduct(products.get(4),5);
        order3.addProduct(products.get(6),2);
        order4.addProduct(products.get(9),2);
        order4.addProduct(products.get(10),3);
        order4.addProduct(products.get(12),6);
        order4.addProduct(products.get(11),5);
        order4.addProduct(products.get(14),2);
        order5.addProduct(products.get(2),6);
        order5.addProduct(products.get(4),5);
        order5.addProduct(products.get(6),2);
        order5.addProduct(products.get(9),2);
        order5.addProduct(products.get(10),3);
        order5.addProduct(products.get(12),6);
        RepositoryManager.getInstance().getShoppingOrderRepository().add(order1);
        RepositoryManager.getInstance().getShoppingOrderRepository().add(order2);
        RepositoryManager.getInstance().getShoppingOrderRepository().add(order3);
        RepositoryManager.getInstance().getShoppingOrderRepository().add(order4);
        RepositoryManager.getInstance().getShoppingOrderRepository().add(order5);

        //WYPISANIE
        System.out.println();
        List<ShoppingOrder> shoppingOrders = RepositoryManager.getInstance().getShoppingOrderRepository().getAll();
        for(ShoppingOrder shoppingOrder : shoppingOrders){
            System.out.printf("Klient %s %s zamówił %d produktów dnia %s%n", shoppingOrder.getCustomer().getFirstName(), shoppingOrder.getCustomer().getLastName(), shoppingOrder.getShoppingOrderProducts().size(), shoppingOrder.getOrderDate());
            for(ShoppingOrderProductAlloc shoppingOrderProductAlloc : shoppingOrder.getShoppingOrderProducts()){
                System.out.printf("%s %s w ilości %d%n", shoppingOrderProductAlloc.getProduct().getProductCategory().getName(),shoppingOrderProductAlloc.getProduct().getName(), shoppingOrderProductAlloc.getQuantity());
            }
            System.out.println();
        }

        //ZMIANY W ZAMÓWIENIU
        ShoppingOrder shoppingOrder = RepositoryManager.getInstance().getShoppingOrderRepository().getById(1);
        shoppingOrder.setOrderDate(LocalDateTime.of(2019,5,12,16,0));
        RepositoryManager.getInstance().getShoppingOrderRepository().edit(shoppingOrder);

        ProductCategory category = RepositoryManager.getInstance().getProductCategoryRepository().getById(1);
        RepositoryManager.getInstance().getProductCategoryRepository().delete(category);
    }

    private static void testUsers(){
        //dodaj
        /*User user = new User();
        user.setLogin("test");
        RepositoryManager.getInstance().getUserRepository().add(user);*/

        //edytuj
        User user = RepositoryManager.getInstance().getUserRepository().getById(1);
        user.setLogin("test2");
        RepositoryManager.getInstance().getUserRepository().edit(user);
    }

    private static void zad_9(Scanner scanner){
        System.out.print("Wpisz ID pracownika: ");
        long id = scanner.nextLong();
        Employee employee = RepositoryManager.getInstance().getEmployeeRepository().getById(id);
        System.out.println(employee);
        System.out.println();

        System.out.print("Podaj liczbę całkowitą: ");
        int n = scanner.nextInt();
        System.out.println();
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            employees.add(new Employee(String.format("Pracownik nr %d", i + 1),3000));
        }
        RepositoryManager.getInstance().getEmployeeRepository().addList(employees);
        for(Employee e : RepositoryManager.getInstance().getEmployeeRepository().getAll()){
            System.out.println(e);
        }
        System.out.println();

        System.out.print("Podaj ID do usunięcia: ");
        id = scanner.nextLong();
        Employee employee1 = RepositoryManager.getInstance().getEmployeeRepository().getById(id);
        RepositoryManager.getInstance().getEmployeeRepository().delete(employee1);
        System.out.printf("%s został usunięty z bazy%n", employee1.getName());
    }
}
