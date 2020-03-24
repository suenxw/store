<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <%
		//response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
			不需要重定向
	%> --%>
	<jsp:forward page="/index"></jsp:forward>
</body>
</html>