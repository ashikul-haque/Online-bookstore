<%-- 
    Document   : addDistributor
    Created on : Dec 13, 2016, 11:25:42 AM
    Author     : Ashik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add distributor</title>
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
    <body>
        <%
        if( session.getAttribute("isAdmin") != "a" ){
            response.sendRedirect("index.jsp");
        } %>
    <center>
            
        <ul>
            <li><a  href="admin.jsp">Home</a></li>
            <li><a href="addBook.jsp">Add Book</a></li>
            <li><a href="adminBookList">Book List</a> </li> 
            <li><a href="transaction.jsp">Transaction</a></li>
            <li style="float:right"><a  href="adminLogout">Logout</a></li>
        </ul>
        <br><br>
        <form action="addDistributor" method="POST">
            Distributor Id: <input type="text" name="distributorId" value="" /><br>
            Distributor Name: <input type="text" name="distributorName" value="" /><br><br>
            <input type="submit" value="Submit" />
        </form>
        
    </center>
    </body>
</html>
