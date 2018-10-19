/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

/**
 *
 * @author Ashik
 */
public class Book {
    
    String bookName;
    String price;
    String quantity;

    public Book(String bookName, String price,String quantity) {
        this.bookName = bookName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getBook() {
        return bookName;
    }

    public void setProduct(String bookName) {
        this.bookName = bookName;
    }

    public String getPrice() {
        return price;
    }
    
    public String getQuantity(){
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    
}
