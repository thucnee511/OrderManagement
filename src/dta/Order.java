/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dta;

/**
 *
 * @author Administrator
 */
public class Order implements Comparable<Order>{
    private String ordId ;
    private String cusId ;
    private String proId ;
    private int quantity ;
    private String date ;
    private boolean status = false;

    public Order(String ordId, String cusId, String proId, int quantity , String date, boolean status) {
        this.ordId = ordId;
        this.cusId = cusId;
        this.proId = proId;
        this.quantity = quantity;
        this.date = date;
        this.status = status;
    }

    public String getOrdId() {
        return ordId;
    }

    public String getCusId() {
        return cusId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    @Override
    public String toString(){
        String msg = String.format("%s,%s,%s,%d,%s,%s",
                ordId , cusId , proId , quantity , date , status) ;
        return msg ;
    }

    @Override
    public int compareTo(Order o) {
        return this.cusId.compareTo(o.getCusId()) ;
    }
}
