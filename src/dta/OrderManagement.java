/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dta;

import java.util.ArrayList;
import java.util.Collections;
import tools.*;
import ui.Menu;

/**
 *
 * @author Administrator
 */
public class OrderManagement {

    private final String CUSFILEPATH = "/src/files/customers.txt";
    private final String PROFILEPATH = "/src/files/products.txt";
    private final String ORDFILEPATH = "/src/files/orders.txt";
    private final String CUSIDREG = "C\\d{4}";
    private final String PROIDREG = "P\\d{4}";
    private final String ORDIDREG = "O\\d{4}";
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();

    public OrderManagement() {
        this.init();
    }

    public void printAllProducts() {
        products.forEach((item) -> {
            System.out.println(item.toString());
        });
    }

    public void printAllCustomers() {
        customers.forEach((item) -> {
            System.out.println(item.toString());
        });
    }

    public void searchCustomerById() {
        String cusId = InputHandler.getString("Enter customer's id: ", "Unavailable id", CUSIDREG);
        if (searchCustomerIndexById(cusId) == -1) {
            System.out.println("This customer does not exist");
        } else {
            Customer cus = searchCustomerObjectById(cusId);
            System.out.println(cus.toString());
        }
    }

    public void addNewCustomer() {
        while (true) {
            String cusId = InputHandler.getString("Enter customer's id: ", "Unavailable id", CUSIDREG);
            if (searchCustomerObjectById(cusId) == null) {
                String cusName = InputHandler.getString("Enter customer's name: ", "Unavailable name");
                String cusAddress = InputHandler.getString("Enter customer's address", "Unavailble address");
                String cusPhoneNum = InputHandler.getString("Enter customer's phone numer: ", "Unavailble phone number", 10, 12);
                customers.add(new Customer(cusId, cusName, cusAddress, cusPhoneNum));
                if (!addContinously()) {
                    return;
                }
            } else {
                System.out.println("This customer's id already existed");
            }
        }
    }

    public void updateCustomer() {
        String cusId = InputHandler.getString("Enter customer's id: ", "Unavailable id", CUSIDREG);
        Customer cus = searchCustomerObjectById(cusId);
        if (cus != null) {
            String cusName = InputHandler.getString("Enter customer's name: ");
            String cusAddress = InputHandler.getString("Enter customer's address");
            String cusPhoneNum = InputHandler.getString("Enter customer's phone numer: ");
            if (!cusPhoneNum.isEmpty()) {
                int len = cusPhoneNum.length();
                if (len < 10 || len > 12) {
                    System.out.println("Update customer failed! Unavailable phone number");
                    return;
                } else {
                    cus.setPhoneNumber(cusPhoneNum);
                }
            }
            if (!cusName.isEmpty()) {
                cus.setName(cusName);
            }
            if (!cusAddress.isEmpty()) {
                cus.setAddress(cusAddress);
            }
            System.out.println("Update successfully: " + cus.toString());
        } else {
            System.out.println("Customer does not exist");
        }
    }

    public void saveCustomersToFile() {
        ArrayList<String> dta = new ArrayList<>();
        customers.forEach((item) -> {
            dta.add(item.toString());
        });
        FileHandler.writeToFile(CUSFILEPATH, dta);
    }

    public void printAllOrders() {
        Collections.sort(orders);
        orders.forEach((item) -> {
            System.out.println(item.toString());
        });
    }

    public void printAllPendingOrders() {
        orders.forEach((item) -> {
            if (!item.isStatus()) {
                System.out.println(item.toString());
            }
        });
    }

    public void addNewOrder() {
        while (true) {
            String ordId = InputHandler.getString("Enter order's id: ", "Unavailable id", ORDIDREG);
            if (searchOrderObjectById(ordId) == null) {
                String cusId = customers.get(getCustomerIdSubMenu()).getId();
                String proId = products.get(getProductIdSubMenu()).getId();
                int quantity = InputHandler.getPositiveInt("Enter order's quantity: ", "Unavailble quantity number");
                String date = InputHandler.getDateMDY("Enter order's date: ");
                boolean status = InputHandler.getBoolean("Enter order's status: ");
                orders.add(new Order(ordId, cusId, proId, quantity, date, status));
                if (!addContinously()) {
                    return;
                }
            } else {
                System.out.println("This order's id already existed!");
            }
        }
    }

    public void updateOrder() {
        String ordId = InputHandler.getString("Enter ord's id: ", "Unavailable id", ORDIDREG);
        Order ord = searchOrderObjectById(ordId);
        if (ord != null) {
            String status = InputHandler.getString("Enter order's new status: ");
            if (!status.isEmpty()) {
                try {
                    boolean _status = Boolean.parseBoolean(status);
                    ord.setStatus(_status);
                } catch (Exception e) {
                    System.out.println("Update failed: Status must be true or false!");
                }
                System.out.println("Update successfully: " + ord.toString());
            }
        } else {
            System.out.println("Order does not exist");
        }
    }
    
    public void deleteOrder(){
        String ordId = InputHandler.getString("Enter ord's id: ", "Unavailable id", ORDIDREG);
        Order ord = searchOrderObjectById(ordId);
        if (ord == null){
            System.out.println("Order does not exist");
        }else{
            System.out.println(ord.toString());
            System.out.print("Do you want to delete this order?");
            if(Menu.getYesOrNo("")) orders.remove(ord) ;
            System.out.println("Delete successfully!");
        }
    }

    public void saveOrdersToFile() {
        ArrayList<String> dta = new ArrayList<>();
        orders.forEach((item) -> {
            dta.add(item.toString());
        });
        FileHandler.writeToFile(CUSFILEPATH, dta);
    }

    private int getCustomerIdSubMenu() {
        System.out.println("Customer id list:");
        customers.forEach((item) -> {
            System.out.println(customers.indexOf(item) + 1 + ". " + item.getId());
        });
        int choice = InputHandler.getInt("Choose a customer id", "Choose a number!", 1, customers.size());
        return choice - 1;
    }

    private int getProductIdSubMenu() {
        System.out.println("Customer id list:");
        products.forEach((item) -> {
            System.out.println(products.indexOf(item) + 1 + ". " + item.getId());
        });
        int choice = InputHandler.getInt("Choose a customer id", "Choose a number!", 1, products.size());
        return choice - 1;
    }

    private int searchOrderIndexById(String ordId) {
        for (Order item : orders) {
            if ((item.getOrdId()).equals(ordId)) {
                return orders.indexOf(item);
            }
        }
        return -1;
    }

    private Order searchOrderObjectById(String ordId) {
        int index = searchOrderIndexById(ordId);
        if (index == -1) {
            return null;
        } else {
            return orders.get(index);
        }
    }

    private boolean addContinously() {
        Menu cont = new Menu("Countinously add new ?");
        cont.addOption("Yes");
        cont.addOption("No");
        cont.printMenu();
        int choice = cont.getChoice();
        return choice == 1;
    }

    private int searchCustomerIndexById(String cusId) {
        for (Customer item : customers) {
            if ((item.getId()).equals(cusId)) {
                return customers.indexOf(item);
            }
        }
        return -1;
    }

    private Customer searchCustomerObjectById(String cusId) {
        int index = searchCustomerIndexById(cusId);
        if (index == -1) {
            return null;
        } else {
            return customers.get(index);
        }
    }

    private void init() {
        ArrayList<String> dta = new ArrayList<>();
        dta.addAll(FileHandler.readFromFile(CUSFILEPATH));
        dta.addAll(FileHandler.readFromFile(PROFILEPATH));
        dta.addAll(FileHandler.readFromFile(ORDFILEPATH));
        dta.forEach((item -> {
            String data[] = item.split(",");
            if (data[0].matches(CUSIDREG)) {
                customers.add(new Customer(data[0], data[1], data[2], data[3]));
            } else if (data[0].matches(PROIDREG)) {
                products.add(new Product(data[0], data[1], data[2], data[3],
                        Double.parseDouble(data[4])));
            } else {
                orders.add(new Order(data[0], data[1], data[2],
                        Integer.parseInt(data[3]),
                        data[4],
                        Boolean.parseBoolean(data[5])));
            }
        }));
    }

    private ArrayList<Customer> getCusDta() {
        ArrayList<String> dta = FileHandler.readFromFile(CUSFILEPATH);
        ArrayList<Customer> ret = new ArrayList<>();
        for (String str : dta) {
            String data[] = str.split(",");
            ret.add(new Customer(data[0], data[1], data[2], data[3]));
        }
        return ret;
    }

    private ArrayList<Product> getProDta() {
        ArrayList<String> dta = FileHandler.readFromFile(PROFILEPATH);
        ArrayList<Product> ret = new ArrayList<>();
        for (String str : dta) {
            String data[] = str.split(",");
            ret.add(new Product(data[0], data[1], data[2], data[3],
                    Double.parseDouble(data[4])));
        }
        return ret;
    }

    private ArrayList<Order> getOrdDta() {
        ArrayList<String> dta = FileHandler.readFromFile(ORDFILEPATH);
        ArrayList<Order> ret = new ArrayList<>();
        for (String str : dta) {
            String data[] = str.split(",");
            ret.add(new Order(data[0], data[1], data[2],
                    Integer.parseInt(data[3]),
                    data[4],
                    Boolean.parseBoolean(data[5])));
        }
        return ret;
    }
}
