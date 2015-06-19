<%--
  Created by IntelliJ IDEA.
  User: panther
  Date: 15/01/15
  Time: 8:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
  <title>Start App Home Page</title>
  <script src="scripts/form.js" language="Javascript" type="text/javascript"></script>
  <script>
  </script>
</head>
<body>
<div id="mappings">
  <h3>Looks Like You Have Added the Following Mappings Previously</h3>
</div>
<div id="pending">
  <h3>You Have Authenticated the Webengage Previously but Needs To connect a Zendesk Account</h3>
</div>
<form method="POST">
  <%= request.getRequestURI() %>
  <div id="dynamicInput">
    <h3>Lets Start with the authentication</h3>
    <input type='button' value='Start OAuth' onclick="passOn();">
  </div>
  <!-- <input type="button" value="Add another text input" onClick="addInput('dynamicInput');">-->
</form>
</body>
</html>