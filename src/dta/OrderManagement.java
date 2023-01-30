/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dta;

import java.util.ArrayList;
import tools.*;

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
