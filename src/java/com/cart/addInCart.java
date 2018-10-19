/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cart;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.db.DataBase;
import com.model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

/**
 *
 * @author Ashik
 */
@WebServlet(name = "addInCart", urlPatterns = {"/addInCart"})
public class addInCart extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int i=0;
        DataBase db = new DataBase();
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("username");
        String quantity = request.getParameter("quantity");
        String compQuan = "0";
        
        //String cart_id2 = (String) session.getAttribute("bookName");
        String bookId = request.getParameter("select");
        String cmpquan = db.bookInStock(bookId);
//        int stockQuan = 0;
        int reqQuan = 0;
//        if(cmpquan!=null){
//            stockQuan = Integer.parseInt(cmpquan);
//        }
        try{
            reqQuan = Integer.parseInt(quantity);
        
            if(bookId==null || reqQuan<=0){
                response.sendRedirect("userFail.jsp");
            }
            else{

                String price=null,bookName=null ;
                String cart_id = (String) session.getAttribute("cartId");
                if(cart_id == null){
                Random randomGenerator = new Random();
                cart_id = user + quantity + randomGenerator.nextInt(100)  ;
                String customer = user ;
                i = db.createCart(cart_id, customer);
                session.setAttribute("cartId",cart_id);
                }
                int n = db.bookToCart(cart_id,bookId,quantity);

                ArrayList<String> book = (ArrayList<String>) session.getAttribute("books");
                String bookIterate;
                for(Object ob : book){
                    bookIterate = (String) ob;
                    StringTokenizer st = new StringTokenizer(bookIterate);
                    String bkname = st.nextToken();
                    String bkprice = st.nextToken();
                    String bkid = st.nextToken();
                    if(bkid.equals(bookId)){
                        price = bkprice;
                        bookName = bkname;
                        break;
                    }
                }
                int pricE = Integer.parseInt(price);
                int quantitY = Integer.parseInt(quantity);
                Integer totalBill = (Integer) session.getAttribute("totalBill");
                totalBill+=(pricE*quantitY);
                session.setAttribute("totalBill",totalBill);
                int f = 0;
                ArrayList<Book> bookCart = (ArrayList<Book>) session.getAttribute("baal");
                if(bookCart == null){
                    bookCart = new ArrayList<Book>();
                    Book b = new Book(bookName, price, quantity); 
                    bookCart.add(b);
                }
                else{
                    for(Object o: bookCart){
                        Book b = (Book) o;
                        String name = b.getBook();
                        if(name.equals(bookName)){
                            f = 1;
                            String oldQuantity = b.getQuantity();
                            int q1 = Integer.parseInt(oldQuantity);
                            int q2 = Integer.parseInt(quantity);
                            int q = q1 +q2;
                            Integer it = q;
                            String newQuantity = it.toString();
                            b.setQuantity(newQuantity);
                            break;
                        }
                    }
                    if(f==0){
                        Book b = new Book(bookName, price, quantity);
                        bookCart.add(b);
                    }
                }

                session.setAttribute("baal", bookCart);

                response.sendRedirect("bookList.jsp");
        }
        }
        catch (NumberFormatException e) {
            response.sendRedirect("userFail.jsp");
        }
        
        
//        try (PrintWriter out = response.getWriter()) {
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet BookList</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>"+bookId+" was added to cart by "+user+"</h1>");
//            out.println("<h1>"+cart_id+"</h1>");
//                
//            if(n==1){
//                
//            out.println("<h1>"+bookId+" was added to cart by "+user+" and price "+price+"</h1>");
//            }
//            if(i==1){
//                out.println("<h1>"+cart_id+"</h1>");
//            }
//            ArrayList<Book> ls;
//            ls = (ArrayList<Book>) session.getAttribute("bookToCart");
//            for(Object object : ls) {
//                Book st = (Book) object;
//            bookName = st.getBook(); 
//            price = st.getPrice();
//            quantity = st.getQuantity();
//            out.println("<h1>"+bookName+" "+price+" "+quantity+"</h1>");
//            }
//            out.println("</body>");
//            out.println("</html>");
//            //response.sendRedirect("bookList.jsp");
//        }
//        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
