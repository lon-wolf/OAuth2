<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="javax.servlet.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
  <link href="<%= request.getContextPath() %>/css/style.css" rel='stylesheet' type='text/css' />
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
  <link href='http://fonts.googleapis.com/css?family=Open+Sans:600italic,400,300,600,700' rel='stylesheet' type='text/css'>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="main">
  <div class="login-form">
    <h1>Enter Details</h1>
    <div class="head">
    </div>
    <form action="<%= request.getContextPath() %>/auth/client" method="post">
      <input type="text" name = "email" value="Email ID" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Enter Your EmailID';}" >
      <input type="text" name = "contact" value="Contact" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Contact';}">
      <input type="text" name = "lcode" value="License Code" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'LicenseCode';}">
      <div class="submit">
        <input type="submit" onclick="alert('Are you sure You want to continue') " value="LOGIN" >
      </div>
    </form>
  </div>
  </div>s
</body>
</html>