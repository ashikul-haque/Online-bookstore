<%-- 
    Document   : login
    Created on : Dec 5, 2016, 10:04:21 AM
    Author     : Ashik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LogIn</title>
        <style>
body {
        background-image: url("bck.jpg");
} 
 
table {
    font-size: 20px;
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

input[type=password]{
    background-color: whitesmoke;
    color: black;
    padding: 6px 12px;
    
}

input[type=text]:focus {
    border: 2px solid #333;
}

input[type=password]:focus {
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
        <h1><br></h1><h1>Login page</h1><h1><br></h1>
        <form action="Login" method="Post">
            <table border="0" width="50" cellspacing="12" cellpadding="6">
                <tbody>
                    <tr>
                        <td>Username</td>
                        <td><input type="text" name="uname"></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="pass"></td>
                    </tr>
                </tbody>
            </table>
            <br>
            <input type="submit" value="login">
        </form>
        
        <form action="create.jsp">
            
            <input type="submit" value="No account?">
        </form>
    </center>
    </body>
</html>
