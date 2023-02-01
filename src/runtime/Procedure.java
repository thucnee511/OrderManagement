/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package runtime;

import dta.OrderManagement;
import ui.Menu;

/**
 *
 * @author Administrator
 */
public class Procedure {

    public static void main(String[] args) {
        Menu menu = new Menu("Order Management");
        menu.addOption("Print all products");
        menu.addOption("Print all customer information");
        menu.addOption("Search a customer by her/his id");
        menu.addOption("Add new customer");
        menu.addOption("Update customer");
        menu.addOption("Save customers to file");
        menu.addOption("Print all order");
        menu.addOption("Print all pending order");
        menu.addOption("Add new order");
        menu.addOption("Update order");
        menu.addOption("Save orders to file");
        menu.addOption("Quit");

        OrderManagement om = new OrderManagement();
        while (true) {
            menu.printMenu();
            int choice = menu.getChoice();
            switch (choice) {
                case 1: {
                    om.printAllProducts();
                    break;
                }
                case 2: {
                    om.printAllCustomers();
                    break;
                }
                case 3: {
                    om.searchCustomerById();
                    break;
                }
                case 4: {
                    om.addNewCustomer();
                    break;
                }
                case 5: {
                    om.updateCustomer();
                    break;
                }
                case 6: {
                    om.saveCustomersToFile();
                    break;
                }
                case 7: {
                    om.printAllOrders();
                    break;
                }
                case 8: {
                    om.printAllPendingOrders();
                    break;
                }
                case 9: {
                    om.addNewOrder();
                    break;
                }
                case 10: {
                    Menu subMenu = new Menu("Update order");
                    subMenu.addOption("Update order infomation");
                    subMenu.addOption("Delete order");
                    while (true) {
                        subMenu.printMenu();
                        int _choice = subMenu.getChoice();
                        switch (_choice) {
                            case 1: {
                                om.updateOrder();
                                break;
                            }
                            case 2: {
                                om.deleteOrder();
                                break;
                            }
                        }
                        if (Menu.getYesOrNo("Back to main menu?")) {
                            break;
                        }
                    }
                    break;
                }
                case 11: {
                    om.saveOrdersToFile();
                    break;
                }
                case 12: {
                    return;
                }
            }
        }
    }
}
