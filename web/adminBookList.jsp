<%-- 
    Document   : adminBookList
    Created on : Dec 13, 2016, 9:59:14 AM
    Author     : Ashik
--%>

<%@page import="com.db.DataBase"%>
<%@page import="java.util.StringTokenizer"%>
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
    <body>
        <%
        if( session.getAttribute("isAdmin") != "a" ){
            response.sendRedirect("index.jsp");
        } %>
    <center>
            
        <ul>
            <li><a  href="admin.jsp">Home</a></li>
            <li><a href="addBook.jsp">Add Book</a></li>
            <li><a class="active" href="adminBookList">Book List</a> </li> 
            <li><a href="transaction.jsp">Transaction</a></li>
            <li style="float:right"><a  href="adminLogout">Logout</a></li>
        </ul>
        <br><br>
        <h1>List of Books</h1>
        <form action="PreEditBook" method="POST">
            <table border="0" width="12" cellpadding="10">
                <tbody>
                    
                

            <% 
                DataBase db = new DataBase();
                ArrayList<String> books = (ArrayList<String>) db.getBookNames() ;
                for(Object o:books){
                String t = (String) o;
                StringTokenizer st = new StringTokenizer(t);
                String bkname = st.nextToken();
                String price = st.nextToken();
                String id = st.nextToken(); %>
                <tr>
                        <td><input  type = "radio" name = "name" value = <%=bkname%> /></td>
                        <td><%out.println(id);%></td>
                        <td><%out.println(bkname);%></td>
                        <td><%out.println(price);%></td>
                </tr>
                
            <% } %>
            
            </tbody>
            </table>
            <br><input type="submit" value="Edit" />
        </form>
    </center>   
    </body>
</html>
