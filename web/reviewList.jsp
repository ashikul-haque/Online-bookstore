<%-- 
    Document   : reviewList
    Created on : Dec 14, 2016, 7:49:14 AM
    Author     : Ashik
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reviews</title>
    <style>
body {
        background-image: url("bck.jpg");
} 
 
table {
    font-size: 24px;
}

ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: green;
}

li {
    float: left;
    border-right: 1px solid #bbb;
}

li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover:not(.active) {
    background-color:  #4CAF50;
}

.active {
    background-color: #111;
}

input[type=text]{
    background-color: whitesmoke;
    color: black;
    padding: 6px 12px;
    
}

input[type=text]:focus {
    border: 2px solid #333;
}

input[type=submit]{
    background-color: green;
    color: white;
    border: none;
    padding: 6px 12px;
    margin: 4px 2px;
    cursor: pointer;
}
    </style>
    </head>
    <body bgcolor="#988484">
    <center>
        
        <%
            if(session.getAttribute("username")==null){
                response.sendRedirect("index.jsp");
            }
        %>
        <ul>
            <li><a  href="welcome.jsp">Home</a></li>
            <li><a href="BookList">Book List</a></li>
            <li><a href="checkout.jsp">Your Cart</a></li>
            <li><a href="seewishList">Wish List</a></li>
            <li><a href="checkoutConfirm.jsp">Checkout</a></li>
            <li style="float:right"><a href="logout">Logout</a></li>
        </ul>
        
        <%
            ArrayList<String> a = (ArrayList<String>) session.getAttribute("reviewList");
            if(a == null || a.isEmpty()){ %>
            <h1>No reviews yet</h1>
            <%}
            else{ %>
                <h1> Here are the reviews for <%= session.getAttribute("reviewBookId") %> </h1>
                <% for(Object o : a){
                    String e = (String) o;
                    out.println(e+"<br>");
                }
            } %>
    </center>
    </body>
</html>