<%-- 
    Document   : create
    Created on : Dec 5, 2016, 10:04:35 AM
    Author     : Ashik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
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
        <h1><br> </h1>
        <h1> Registration form</h1><h1><br> </h1>
            
        <form action="Registration" method="post"><table border="0" >
                <tbody>
                    <tr>
                        <td>First name</td>
                        <td><input type="text" name="firstName"></td>
                    </tr>
                    <tr>
                        <td>Last name</td>
                        <td><input type="text" name="lastName"></td>
                    </tr>
                    <tr>
                        <td>Enter Username</td>
                        <td><input type="text" name="usname"></td>
                    </tr>
                    <tr>
                        <td>Enter Password</td>
                        <td><input type="password" name="pw"><br></td>
                    </tr>
                </tbody>
            </table>

            <br>       
                    
               
               
            <input type="submit" value="Register">
    </center>
    </body>
</html>
