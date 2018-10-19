/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBase {

    String dbURL = "jdbc:oracle:thin:@DESKTOP-3TBUOUT:1521:orcl";
    String username = "hr";
    String password = "hr";

    Connection conn = null;

    public DataBase() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(dbURL, username, password);
            if (conn != null) {
                System.out.println("Connection successfully established.");
            } else {
                System.out.println("Could not establish connection");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    public List<String> getEmployeeNames()
//    {
//        List<String> employeeNames = new ArrayList<String>();
//        String selectStatement = "select first_name, last_name from employees";
//        try
//        {    
//            PreparedStatement stmt = conn.prepareStatement(selectStatement);
//            ResultSet rs = stmt.executeQuery();
//            while(rs.next())
//            {
//                String first = rs.getString("first_name");
//                String last = rs.getString("last_name");
//                String name = first + " " + last;
//                employeeNames.add(name);
//            }
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        
//        return employeeNames;
//    }

    public List<String> getBookNames() {
        List<String> bookNames = new ArrayList<String>();
        List<String> bookIds = new ArrayList<String>();
        String selectStatement = "select book_name, price, book_id from book";

        try {
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("book_name");
                String price = rs.getString("price");
                String id = rs.getString("book_id");
                String bookName = name + " " + price + " " + id;
                bookNames.add(bookName);
                //bookIds.add(bookId);
            }
        } catch (SQLException e) {
        }

        return bookNames;
    }

//    public String getBookPrice(String book_id){
//        
//        try {
//            String query = "select price from book";
//            PreparedStatement stmt = conn.prepareStatement(query);
//            stmt.setString(1, book_id);
//            
//            ResultSet rs = stmt.executeQuery();
//            String price = rs.getString("price");
//            return price;
//            
//        } 
//        catch (SQLException ex) {
//            return "error";
//        }
//    }   
    public int addBook(String book_id, String book_name, String book_price, String isbn, String version, String publisher_id) {
        try {
            String query = "insert into book(book_id,book_name,price,isbn,version,publisher_id) values(?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, book_id);
            stmt.setString(2, book_name);
            stmt.setString(3, book_price);
            stmt.setString(4, isbn);
            stmt.setString(5, version);
            stmt.setString(6, publisher_id);
            int i = stmt.executeUpdate();
            return i;
        } catch (SQLException e) {
            return 0;
        }
    }
    
    public int addBookToAuthor(String author_id, String book_id) {
        try {
            String insert = "insert into writes values(?,?)";
            PreparedStatement stmt = conn.prepareStatement(insert);
            stmt.setString(1,author_id);
            stmt.setString(2,book_id);
            int i = stmt.executeUpdate();
            return i;
        } catch (SQLException e) {
            return 0;
        }
    }
    
    public int editBook(String book_id, String book_name, String book_price) {
        try {
            String editQuery = "update book "
                    + "set book_name = ? , price = ? "
                    + "where book_id = ? ";
            PreparedStatement stmt = conn.prepareStatement(editQuery);
            stmt.setString(1, book_name);
            stmt.setString(2, book_price);
            stmt.setString(3, book_id);
            int i = stmt.executeUpdate();
            return i;
        } catch (SQLException e) {
            return 0;
        }
    }

    public int addTransaction(String distributor_id, String book_id, String quantity, String price) {
        try {
            String insert = "insert into distributes values(?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(insert);
            stmt.setString(1, distributor_id);
            stmt.setString(2, book_id);
            stmt.setString(3, quantity);
            stmt.setString(4, price);
            int i = stmt.executeUpdate();
            return i;
        } catch (SQLException e) {
            return 0;
        }
    }

    public ArrayList<String> getTransaction() {
        ArrayList<String> a = new ArrayList<String>();
        try {
            String query = "select * from distributes";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String distId = rs.getString("distributor_id");
                String bookId = rs.getString("book_id");
                String bookPrice = rs.getString("price");
                String quantity = rs.getString("quantity");
                String result = "Distributor id: " + distId + '\n'
                        + "  Book Id: " + bookId + '\n' + "  Price: " + bookPrice + '\n' + "  Quantity: " + quantity;
                a.add(result);
            }
        } catch (Exception e) {
        }
        return a;
    }

    public int removeBookFromCart(String cart_id, String book_id) {
        String query = "delete from contains where cart_id=? and book_id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, cart_id);
            stmt.setString(2, book_id);
            int i = stmt.executeUpdate();
            return i;
        } catch (Exception e) {
            return 0;
        }
    }

    public ArrayList<String> getDistributor() {
        ArrayList<String> a = new ArrayList<String>();
        try {
            String query = "select * from distributor";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("distributor_id");
                String name = rs.getString("distributor_name");
                String t = id + " " + name;
                a.add(t);
            }
        } catch (SQLException e) {
        }
        return a;
    }
    
    public int addPublisher(String id, String name) {
        try {
            String insert = "insert into publisher values(?,?)";
            PreparedStatement stmt = conn.prepareStatement(insert);
            stmt.setString(1, id);
            stmt.setString(2, name);
            int i = stmt.executeUpdate();
            return i;
        } catch (SQLException e) {
            return 0;
        }
    }
    
     public int addAuthor(String id, String name) {
        try {
            String insert = "insert into author values(?,?)";
            PreparedStatement stmt = conn.prepareStatement(insert);
            stmt.setString(1, id);
            stmt.setString(2, name);
            int i = stmt.executeUpdate();
            return i;
        } catch (SQLException e) {
            return 0;
        }
    }

    public int addDistributor(String id, String name) {
        try {
            String insert = "insert into distributor values(?,?)";
            PreparedStatement stmt = conn.prepareStatement(insert);
            stmt.setString(1, id);
            stmt.setString(2, name);
            int i = stmt.executeUpdate();
            return i;
        } catch (SQLException e) {
            return 0;
        }
    }

    public ArrayList<String> seeWish(String user_id) {
        ArrayList<String> wish = new ArrayList<String>();
        try {
            String query = "select book_name,price"
                    + " from book b join wishes w using(book_id) where w.customer_id= ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String bookName = rs.getString("book_name");
                String bookPrice = rs.getString("price");
                String bookWish = bookName + "  " + bookPrice;
                wish.add(bookWish);
            }
        } catch (SQLException e) {
        }
        return wish;
    }

    public int removeWish(String book_name) {
        try {
            String query = "delete from wishes where book_id="
                    + "( select book_id from book where book_name= ? )";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, book_name);
            int i = stmt.executeUpdate();
            return i;
        } catch (SQLException e) {
            return 0;
        }

    }
    
    public String cartBookQuantity(String cart_id, String book_id){
        String s = "select quantity from contains where cart_id=? and book-id=?";
        String quantity=null;
        try {
            PreparedStatement stmt = conn.prepareStatement(s);
            stmt.setString(1,cart_id);
            stmt.setString(2, book_id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                quantity = rs.getString("quantity");
            }
            return quantity;
        } catch (Exception e) {
            return null;
        }
        
        
    }
    
    public int bookQuantityChangeInCart(String book_id,String cart_id,String quantity){
        String s = "update contains set quantity=? where book_id=? and cart_id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(s);
            stmt.setString(1,quantity);
            stmt.setString(2,book_id);
            stmt.setString(3, cart_id);
            int i = stmt.executeUpdate();
            return i;
        } catch (Exception e) {
            return 0;
        }
    }
    
    public int bookToCart(String cart_id, String book_id, String quantity) {
        try {
            String q = cartBookQuantity(cart_id,book_id);
            if(q!=null){
                quantity+=q;
                String s = "update contains set quantity = ? where cart_id=? and book_id=?";
                PreparedStatement stmt = conn.prepareStatement(s);
                stmt.setString(1,quantity);
                stmt.setString(2,cart_id);
                stmt.setString(3,book_id);
                int i = stmt.executeUpdate();
                return i;
            }
            else{
                String insertCommand1 = "insert into contains values(?,?,?)";
                PreparedStatement stmt1 = conn.prepareStatement(insertCommand1);
                stmt1.setString(1, cart_id);
                stmt1.setString(2, book_id);
                stmt1.setString(3, quantity);
                int count = stmt1.executeUpdate();
                return count;
            }
        } catch (SQLException e) {
            return 0;
        }
    }

//    public int bookToCart(String cart_id, String book_id, String quantity)
//    {
//        try
//        {
//            String insertCommand1 = "insert into contains values(?,?,?)";
//            PreparedStatement stmt1 = conn.prepareStatement(insertCommand1);
//            stmt1.setString(1, cart_id);
//            stmt1.setString(2, book_id);
//            stmt1.setString(3, quantity);
//            int count = stmt1.executeUpdate();
//            return count;
//        }
//        catch(SQLException e)
//        {
//            return 0;
//        }       
//    }
    public String getUserType(String customer_id) {
        String s = "select type from customer where customer_id=?";
        String type = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(s);
            stmt.setString(1, customer_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                type = rs.getString("type");
            }
        } catch (Exception e) {
        }
           return type;
    }

    public int createAccount(String customer_id, String customer_name, String email, String password, String type) {
        try {
            String insertCommand = "insert into customer values(?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setString(1, customer_id);
            stmt.setString(2, customer_name);
            stmt.setString(3, email);
            stmt.setString(4, password);
            stmt.setString(5, type);
            int count = stmt.executeUpdate();
            return count;
        } catch (SQLException e) {
            return 0;
        }
    }

    public int bookToWishList(String book_id, String customer_id) {
        try {
            String insertCommand = "insert into wishes values(?,?)";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setString(1, book_id);
            stmt.setString(2, customer_id);
            int count = stmt.executeUpdate();
            return count;
        } catch (SQLException e) {
            return 0;
        }
    }

    public int addReview(String review_id, String review) {
        try {
            String insert = "insert into review(review_id,review) values(?,?)";
            PreparedStatement stmt = conn.prepareStatement(insert);
            stmt.setString(1, review_id);
            stmt.setString(2, review);
            int i = stmt.executeUpdate();
            return i;
        } catch (Exception e) {
            return 0;
        }
    }
    
    public String userFullName(String user_id){
        String s = "select customer_name,email from customer where customer_id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(s);
            stmt.setString(1,user_id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String first = rs.getString("customer_name");
                String last = rs.getString("email");
                String name = first+" "+last;
                System.out.println(name);
                return name;
            }
        } catch (SQLException e) {
        }
        return null;
    }
    
    public ArrayList<String> authorName(){
        String s = "select * from author";
        ArrayList<String> st = new ArrayList<String>();
                
        try {
            PreparedStatement stmt = conn.prepareStatement(s);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String id = rs.getString("author_id");
                String name = rs.getString("author_name");
                String author = id+" "+name;
                st.add(author);
            }
        } catch (Exception e) {
        }
        return st;
    }
    
    public ArrayList<String> publisherName(){
        String s = "select * from publisher";
        ArrayList<String> st = new ArrayList<String>();
                
        try {
            PreparedStatement stmt = conn.prepareStatement(s);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String id = rs.getString("publisher_id");
                String name = rs.getString("publisher_name");
                String author = id+" "+name;
                st.add(author);
            }
        } catch (Exception e) {
        }
        return st;
    }
    
    public String bookInStock(String book_id){
        try {
            String st = "select sum(quantity) from distributes where book_id=? group by book_id";
            PreparedStatement stmt = conn.prepareStatement(st);
            stmt.setString(1,book_id);
            ResultSet rs = stmt.executeQuery();
            String s=null;
            while(rs.next()){
                s = rs.getString("sum(quantity)");
            }
            return s;
        } catch (Exception e) {
            return null;
        }
    }
    
    public int reviews(String review_id, String customer, String book) {
        try {
            String query = "insert into reviews values(?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, review_id);
            stmt.setString(2, customer);
            stmt.setString(3, book);
            int i = stmt.executeUpdate();
            return i;
        } catch (SQLException e) {
            return 0;
        }
    }

    public String getBookName(String bookId) {
        String query = "select book_name from book where book_id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, bookId);
            ResultSet rs = stmt.executeQuery();
            String bookName = null;
            while (rs.next()) {
                bookName = rs.getString("book_name");
            }
            return bookName;
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<String> fetchReview(String book_id) {
        ArrayList<String> t = new ArrayList<String>();
        try {
            String a = "select customer_id,review,datetime from reviews join review using(review_id)"
                    + "where book_id=?";
            PreparedStatement stmt = conn.prepareStatement(a);
            stmt.setString(1,book_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String user = rs.getString("customer_id");
                String r = rs.getString("review");
                Date date = rs.getDate("datetime");
                String val = date.toString();
                String res = "<br><font size=8>" + user + "</font>&nbsp;&nbsp;" + "<font size=2>" + val + "</font>" + "<br><br>" + "<font size=5>" + r + "</font>";
                t.add(res);
            }
        } catch (Exception e) {
        }
        return t;
    }

    public int createCart(String cart_id, String customer_id) {
        try {
            String insertCommand = "insert into cart(cart_id,customer_id) values(?,?)";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setString(1, cart_id);
            stmt.setString(2, customer_id);
            int count = stmt.executeUpdate();
            return count;
        } catch (SQLException e) {
            return 0;
        }
    }

    public boolean existUser(String uname, String pass) {
        try {
            String query = "select * from customer where customer_id = ? and password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, uname);
            stmt.setString(2, pass);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error");
        }
        return false;
    }

//    public boolean existBeforeReg(String uname) {
//        try
//        {
//            String query = "select * from customer where customer_id = ?";
//            PreparedStatement stmt = conn.prepareStatement(query);
//            stmt.setString(1, uname);
//            ResultSet rs = stmt.executeQuery();
//            if(rs.next()){
//                return false;
//            }
//            return true;
//        }
//        catch(SQLException e)
//        {
//            return false;
//        }  
//    }
}
