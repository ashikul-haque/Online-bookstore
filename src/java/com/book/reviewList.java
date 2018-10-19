/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book;

import com.db.DataBase;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "reviewList", urlPatterns = {"/reviewList"})
public class reviewList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DataBase db = new DataBase();
        String bookId = request.getParameter("select");
        ArrayList<String> review = new ArrayList<String>();
        review = db.fetchReview(bookId);
        HttpSession session  = request.getSession();
        session.setAttribute("reviewList",review);
        String bookName = db.getBookName(bookId);
        session.setAttribute("reviewBookId",bookName);
        if(bookId!=null){
            response.sendRedirect("reviewList.jsp");
        }
        else{
            response.sendRedirect("bookList.jsp");
        }
//        
//            try (PrintWriter out = response.getWriter()) {
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet BookList</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet BookList at " + request.getContextPath() + "</h1>");
//                
//                if(review.isEmpty())out.println("<h1>"+"baal"+"</h1>");
//            for(Object object : review) {
//                String element = (String) object;
//                out.println("<h1>"+element+"</h1>");
//            }
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
