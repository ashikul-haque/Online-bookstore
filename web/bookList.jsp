<%@page import="java.util.StringTokenizer"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : bookList
    Created on : Dec 5, 2016, 10:00:26 PM
    Author     : Ashik
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book List</title>
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
            <li><a href="welcome.jsp">Home</a></li>
            <li><a class="active" href="BookList">Book List</a></li>
            <li><a href="checkout.jsp">Your Cart</a></li>
            <li><a href="seewishList">Wish List</a></li>
            <li><a href="checkoutConfirm.jsp">Checkout</a></li>
            <li style="float:right"><a href="logout">Logout</a></li>
            </ul>

        <br><br><br>
        <form action="addInCart" method="POST">
            <table border="0" width="30" cellspacing="12">
             <tbody>   
                    
       <h1>Select Book</h1>
        <%! ArrayList<String> ls; %>
        <%
            ls = (ArrayList<String>) session.getAttribute("books");
            for(Object object : ls) {
                String element = (String) object;
       
            StringTokenizer st = new StringTokenizer(element);
            String bookName = st.nextToken(); 
            String price = st.nextToken();
            String bookId = st.nextToken();
            //session.setAttribute("bookName", bookName);
         %>
         
             <tr>
        <td><input type="radio" name="select" value= <%=bookId%> /> </td> 
        <td><%out.println(bookName);%> </td> 
        <td><%out.println(price);%> </td> 
            </tr>
         <%}%>
         </tbody>
        </table>
        <br><br><input type="text" name="quantity" value="1" /> &nbsp;&nbsp;&nbsp;
        <input type="submit"  value="Add to cart"/> <br><br>
        <input type="submit"  value="Add to wishlist" onclick="form.action='wishlist';" /><br><br>
        <input type="submit" value="Give review" onclick="form.action='review';" /><br><br>
        <input type="submit" value="See review" onclick="form.action='reviewList';" /><br><br>
        
        </form> 
    </center>
    </body>    
        
    
</html>
