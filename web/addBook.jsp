<%-- 
    Document   : addBook
    Created on : Dec 13, 2016, 9:02:36 AM
    Author     : Ashik
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.db.DataBase"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add book</title>
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

select{
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
    <body>
        <%
        if( session.getAttribute("isAdmin") != "a" ){
            response.sendRedirect("index.jsp");
        } %>
    <center>
            
        <ul>
            <li><a  href="admin.jsp">Home</a></li>
            <li><a class="active" href="addBook.jsp">Add Book</a></li>
            <li><a href="adminBookList">Book List</a> </li> 
            <li><a href="transaction.jsp">Transaction</a></li>
            <li style="float:right"><a  href="adminLogout">Logout</a></li>
        </ul>
        <br><br>
        <h1>Add new book</h1>
        <form action="addBook" method="POST">
        Book Id: <input type="text" name="bookId" value="" /> <br>
        Book Name: <input type="text" name="bookName" value="" /> <br>
        Book Price: <input type="text" name="bookPrice" value="" /> <br>
        ISBN: <input type="text" name="isbn" value="" /> <br>
        Version: <input type="text" name="version" value="" /> <br>
        Author: <select name="author">
        <%
            DataBase db = new DataBase();
            ArrayList<String>  a = db.authorName();
            for(Object objct:a){ 
                String s = (String) objct; %>
                <option><%=s %></option>
            <% } %>
        </select> <br>
        Publisher:  <select name="publisher">
        <%
            ArrayList<String>  b = db.publisherName();
            for(Object objct:b){ 
                String d = (String) objct; %>
                <option><%=d %></option>
            <% } %>   
        </select> <br>
        <input type="submit" value="Submit" />
        </form>
    </center>   
    </body>
</html>
