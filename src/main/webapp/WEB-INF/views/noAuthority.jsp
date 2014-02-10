<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ include file="/WEB-INF/views/header.jsp"%>

<html>
<head>
<title>접근 불가 오류 화면</title>
</head>
<body>
<div align="center" class="body">
<h2><font color="red">이 화면에 접근할 권한이 없습니다.</font></h2>
<a href="<%=root%>/login.html">■로그인 화면으로</a></div>

</br>
=========================================================================================
</br>
${sessionScope["SPRING_SECURITY_403_EXCEPTION"].message }
<!-- 
 ${SPRING_SECURITY_403_EXCEPTION}
 -->
<br>
<%
     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
     Object principal = auth.getPrincipal();
     if (principal instanceof UserDetails) {
         String username = ((UserDetails) principal).getUsername();
         String password = ((UserDetails) principal).getPassword();
         out.println("Account : " + username.toString() + "<br>");
	}
%>

</body>
</html>