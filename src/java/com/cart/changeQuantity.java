/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cart;

import com.db.DataBase;
import com.model.Book;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ashik
 */
@WebServlet(name = "changeQuantity", urlPatterns = {"/changeQuantity"})
public class changeQuantity extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String bkName = request.getParameter("select");
        String quantity = request.getParameter(bkName);
        String compQuan = "0";
        String cart_id = (String) session.getAttribute("cartId");
        String bookId = null;
        DataBase db = new DataBase();
        
        try{
            int newQuantity = Integer.parseInt(quantity);
            if(newQuantity<=0){
            response.sendRedirect("checkout.jsp");
        }
            else{
            ArrayList<String> books = (ArrayList<String>) session.getAttribute("books");
                String bookIterate;
                for(Object ob : books){
                    bookIterate = (String) ob;
                    StringTokenizer st = new StringTokenizer(bookIterate);
                    String bkname = st.nextToken();
                    String bkprice = st.nextToken();
                    String bkid = st.nextToken();
                    if(bkname.equals(bkName)){
                        bookId = bkid;
                        break;
                    }
                }
            int i = db.bookQuantityChangeInCart(bookId, cart_id , quantity);
                ArrayList<Book> book = (ArrayList<Book>) session.getAttribute("baal");
                for(Object o : book){
                    Book b = (Book) o;
                    String bookName = b.getBook();

                    if(bookName.equals(bkName)){
                        String oldQuantity = b.getQuantity();
                        int oldquantity = Integer.parseInt(oldQuantity);
                        String price = b.getPrice();
                        int Price = Integer.parseInt(price);
                        
                        Integer totalbill = (Integer) session.getAttribute("totalBill");
                        totalbill+=(newQuantity*Price);
                        totalbill-=(oldquantity*Price);
                        session.setAttribute("totalBill",totalbill);
                        b.setQuantity(quantity);
                        break;
                    }
                }
            response.sendRedirect("checkout.jsp");
        }
        }
        catch (NumberFormatException e) {
            response.sendRedirect("userFail.jsp");
        }
        
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
