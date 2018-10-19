/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin;

import com.db.DataBase;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ashik
 */
@WebServlet(name = "addBook", urlPatterns = {"/addBook"})
public class addBook extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DataBase db = new DataBase();
        String book_id =  request.getParameter("bookId");
        String book_name =  request.getParameter("bookName");
        String price =  request.getParameter("bookPrice");
        try {
            int asd = Integer.parseInt(price);
            if(asd>0){
                String isbn = request.getParameter("isbn");
                String version = request.getParameter("version");
                String publisher = request.getParameter("publisher");
                StringTokenizer st = new StringTokenizer(publisher);
                String pubId = st.nextToken();
                String author = request.getParameter("author");
                StringTokenizer sts = new StringTokenizer(author);
                String autId = sts.nextToken();

                int i = db.addBook(book_id, book_name, price,isbn,version,pubId);
                int t = db.addBookToAuthor(autId, book_id);
                if(i==1 && t==1){
                    response.sendRedirect("adminSuccess.jsp");
                }
                else{
                    response.sendRedirect("adminFail.jsp");
                }
            }
            else{
                response.sendRedirect("adminFail.jsp");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("adminFail.jsp");
        }
        
//        try (PrintWriter out = response.getWriter()) {
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet BookList</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet BookList at " + book_id+" "+book_name+" "+price + "</h1>");
//            
//            out.println("</body>");
//            out.println("</html>");
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
