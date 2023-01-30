/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dta;

import java.util.Comparator;

/**
 *
 * @author Administrator
 */
public class Customer{
    private String id ;
    private String name ;
    private String address ;
    private String phoneNumber ;

    public Customer(){}
    public Customer(String id, String name, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public String toString(){
        String msg = String.format("%s,%s,%s,%s",
                id , name , address , phoneNumber) ;
        return msg ;
    }
}
