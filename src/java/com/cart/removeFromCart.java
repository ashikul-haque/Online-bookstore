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
@WebServlet(name = "removeFromCart", urlPatterns = {"/removeFromCart"})
public class removeFromCart extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DataBase db = new DataBase();
        int i = 0;
        String bookName  = request.getParameter("select");
        String bookId = null;
        HttpSession session = request.getSession();
        ArrayList<String> books = (ArrayList<String>) session.getAttribute("books");
        for(Object o : books){
            String e = (String) o;
            StringTokenizer st = new StringTokenizer(e);
            String bkName= st.nextToken();
            String price = st.nextToken();
            String bkId = st.nextToken();
            if(bkName.equals(bookName)){
                bookId = bkId; 
                break;
            }
            
        }
//        String quantity = (String) session.getAttribute("removeCartBookQuantity");
        ArrayList<Book> a = (ArrayList<Book>) session.getAttribute("baal");
        String cartId = (String) session.getAttribute("cartId");
        i = db.removeBookFromCart(cartId, bookId);
        for(Object o : a){
            Book t = (Book) o;
            String s = t.getBook();
            String p = t.getPrice();
            String q = t.getQuantity();
            if(s.equals(bookName) && i!=0){
                Integer totalBill = (Integer) session.getAttribute("totalBill");
                int pricE = Integer.parseInt(p);
                int quantitY = Integer.parseInt(q);
                totalBill-=(pricE*quantitY);
                session.setAttribute("totalBill",totalBill);
                a.remove(t);
                break;
            }
        }
        response.sendRedirect("checkout.jsp");
//        if(i!=0){
//            response.sendRedirect("userSuccess.jsp");
//        }
//        else{
//            response.sendRedirect("userFail.jsp");
//        }
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
