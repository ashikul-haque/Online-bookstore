<%-- 
    Document   : checkout
    Created on : Dec 6, 2016, 10:36:46 PM
    Author     : Ashik
--%>

<%@page import="com.model.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
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
            <li><a  href="BookList">Book List</a></li>
            <li><a class="active" href="checkout.jsp">Your Cart</a></li>
            <li><a href="seewishList">Wish List</a></li>
            <li><a href="checkoutConfirm.jsp">Checkout</a></li>
            <li style="float:right"><a href="logout">Logout</a></li>
            </ul>
       
        <%! ArrayList<Book> ls; %>
        <%
            ls = (ArrayList<Book>) session.getAttribute("baal");
            if(ls==null || ls.isEmpty()){
                out.println("<h1>No book in your cart</h1>");
            }
            else{
                out.println("<h1>Books in cart: </h1>");
                for(Object object : ls) {
                    Book st = (Book) object;
                    String bookName = st.getBook(); 
                    String price = st.getPrice();
                    String quantity = st.getQuantity();
           %>
           <form action="removeFromCart">
               <h3> <input type="radio" name="select" value="<%= bookName%>" /> <% out.println("Book Name: "+bookName+"&nbsp;&nbsp;&nbsp;&nbsp;Price: "+price+"&nbsp;&nbsp;&nbsp;&nbsp;"); %> 
                <input type="text" name="<%=bookName%>" value="<%=quantity%>" /></h3><br>
                     <% } %>
               <input type="submit" value="Confirm your edit" onclick="form.action='changeQuantity'" /><br><br>
               <input type="submit" value="Remove from cart" /><br>
               <h2> Total Bill: <%=session.getAttribute("totalBill")%> </h2>
               <a href="checkoutConfirm.jsp">Checkout</a>
         </form>
        <% } %>
    </center>     
    </body>
</html>
