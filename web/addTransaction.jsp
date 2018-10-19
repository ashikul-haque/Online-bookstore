<%-- 
    Document   : addTransaction
    Created on : Dec 13, 2016, 11:55:24 AM
    Author     : Ashik
--%>

<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.db.DataBase"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add transaction</title>
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
        <form action="addTransaction" method="POST">
            Distributor id: <select name="distributorId">
            <%
                DataBase db = new DataBase();
                ArrayList<String> a = db.getDistributor();
                for(Object o: a){
                    String t = (String) o; %>
                    <option><%=t%></option>
                <% } %>   
            </select> <br><br>
            Book Id: <select name="bookId">
                <%
                    ArrayList<String> books = (ArrayList<String>) db.getBookNames();
                    for(Object o:books){
                        String t = (String) o;
                        StringTokenizer st = new StringTokenizer(t);
                        String bookName = st.nextToken();
                        String price = st.nextToken();
                        String id = st.nextToken();
                        String e = id+" "+ bookName;%>
                        <option><%= e %></option>
                    <% } %>
            </select> <br><br>
            Book Price: <input type="text" name="price" value="" /> <br><br>
            Book quantity: <input type="text" name="quantity" value="" /> <br><br>
            <input type="submit" value="Submit" />
        </form>
        
    </center>
    </body>
</html>
