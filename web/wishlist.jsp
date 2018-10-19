<%-- 
    Document   : wishlist
    Created on : Dec 10, 2016, 10:33:17 PM
    Author     : Ashik
--%>

<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Wishlist</title>
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
            <li><a class="active" href="seewishList">Wish List</a></li>
            <li><a href="checkoutConfirm.jsp">Checkout</a></li>
            <li style="float:right"><a href="logout">Logout</a></li>
        </ul><br><br>
        
        <form action="editWish"> 
        <%
            ArrayList<String> book = (ArrayList<String>) session.getAttribute("wishBookList");
            if(book==null || book.isEmpty()){
                out.println("<h1>No book in your wishlist</h1>");
            }
            else{
                out.println("<h1>Your wishlist</h1>");
                for(Object o:book){
                    String wishBook = (String) o;
                    StringTokenizer st = new StringTokenizer( wishBook);
                    String bookName = st.nextToken();
        %>
         <h2><input type="radio" name="select" value="<%=bookName%>"/><% out.println(wishBook); %></h2>
        <% } %>
        <br>
        <input type="text" name="quantity" value="" /> &nbsp;&nbsp;
        <input type="submit" value="Add to cart" onclick="form.action='editCart';"/> <br><br>
        <input type="submit" value="Remove from wishlist" />
        </form>
        <% } %>
    </center>    
    </body>
</html>
