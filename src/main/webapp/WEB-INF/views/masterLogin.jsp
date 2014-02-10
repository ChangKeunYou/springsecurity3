<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%String root = request.getContextPath(); %>
<link rel="stylesheet" type="text/css" href="<%=root %>/resources/css/shopping3-7.css">
<html>
<head>
<title>관리자 로그인 화면</title>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
 <script type="text/javascript">
 	$(document).ready(function(){
 		//alert(1);
 	});
 </script>
</head>
<body>
<div align="center" class="body">
<h2>관리자 로그인 화면</h2>
<c:if test="${not empty param.login_error}">
	<font color="red">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</font>  
</c:if>
<form action="j_spring_security_check" method="post">
<table>
	<tr height="40px">
		<td>유저ID</td>
		<td><input class="userId" type="text" class="text"
			name="j_username" size="20"></td>
	</tr>
	<tr height="40px">
		<td>패스워드</td>
		<td><input class="password" type="password" class="text"
			name="j_password" size="20"></td>
	</tr>
	<tr>
		<td colspan="2" align="center"></td>
	</tr>
</table>
<table>
	<tr>
		<td align="center"><input type="submit" value="로그인"></td>
	</tr>
</table>
</form>
<br>
</div>
</body>
</html>