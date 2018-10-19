/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.db.DataBase;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ashik
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DataBase db = new DataBase();
        String uname = request.getParameter("uname");
        String pass = request.getParameter("pass");
        //boolean t= db.existUser(uname,pass);
        if(db.existUser(uname, pass)){
            HttpSession session = request.getSession();
            session.setAttribute("username", uname);
            session.setAttribute("cartId",null);
            session.setAttribute("bookToCart",null);
            session.setAttribute("totalBill",0);
            session.setAttribute("fullName",db.userFullName(uname));
            String type = db.getUserType(uname);
            //response.sendRedirect("welcome.jsp");
            if(type==null || type.equals("c")){
                response.sendRedirect("welcome.jsp");
                
            }
            else{
                session.setAttribute("isAdmin","a");
                response.sendRedirect("admin.jsp");
            }
//        try (PrintWriter out = response.getWriter()) {
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet BookList</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet BookList at " + type + "</h1>");
//            
//            out.println("</body>");
//            out.println("</html>");
//        }
            
        }
        else{
            response.sendRedirect("loginFail.jsp");
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
