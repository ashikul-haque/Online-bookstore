/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin;

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
@WebServlet(name = "PreEditBook", urlPatterns = {"/PreEditBook"})
public class PreEditBook extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookName = request.getParameter("name");
        String bookId = null;
        String bookPrice = null;
        HttpSession session = request.getSession();
        ArrayList<String> books = (ArrayList<String>) session.getAttribute("books");
        for(Object o: books){
            String t = (String) o;
            StringTokenizer st = new StringTokenizer(t);
            String bkName = st.nextToken();
            String bkPrice = st.nextToken();
            String bkId = st.nextToken();
            if(bkName.equals(bookName)){
                bookId = bkId;
                bookPrice = bkPrice;
                break;
            }
        }
        session.setAttribute("editBookName", bookName);
        session.setAttribute("editBookId", bookId);
        session.setAttribute("editBookPrice", bookPrice);
        response.sendRedirect("editBook.jsp");
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
