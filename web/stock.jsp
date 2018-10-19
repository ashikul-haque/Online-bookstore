<%-- 
    Document   : stock
    Created on : Dec 17, 2016, 7:16:27 AM
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
        <title>Stock</title>
    
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
    <center>
            
        <ul>
            <li><a href="admin.jsp">Home</a></li>
            <li><a href="adminBookList.jsp">Books</a></li>
            <li><a class="active" href="stock.jsp">Stock</a></li>
            <li><a href="transaction.jsp">Transaction</a></li>
            <li style="float:right"><a  href="logout">Logout</a></li>
        </ul>
        <br><br><br>
        <h1>Stock of books</h1>
        <table border="0" cellspacing="12">
            <tbody>
        <% 
            DataBase db = new DataBase();
            ArrayList<String> books = new ArrayList<String>();
            books = (ArrayList<String>) db.getBookNames();
            for(Object object : books ){
                String t = (String) object;
                StringTokenizer st = new StringTokenizer(t);
                String bkName = st.nextToken();
                String bkPrice = st.nextToken();
                String bkId = st.nextToken();
                String quantity =  db.bookInStock( bkId );
                if(quantity!=null){
                int comp = Integer.parseInt(quantity); %>
                <tr>
                <% if(comp<10){ %>
                
                
                    <td> Book name : <%=bkName%></td>
                    <td> Quantity : <font color="red"> <%=quantity%> </font></td>
                
                <% } else{%>
                
                <td> Book name : <%=bkName%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td> Quantity : <%=quantity%> </td>
                
                </tr>
            <br>     

           <%  } %>
            <% }} %>
            </tbody>
        </table>  
    </center>
    </body>
</html
