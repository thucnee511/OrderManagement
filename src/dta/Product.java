/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dta;

/**
 *
 * @author Administrator
 */
public class Product {
    private String id ;
    private String name ;
    private String unit ;
    private String origin ;
    private double price ;

    public Product(String id, String name, String unit, String origin, double price) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.origin = origin;
        this.price = price;
    }

    public String getId() {
        return id;
    }
    
    @Override
    public String toString(){
        String msg = String.format("%s,%s,%s,%s,%.3f",
                id , name , unit , origin , price) ;
        return msg ;
    }
}
