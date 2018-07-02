<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<script>
    window.location.href = "${APP_PATH}/user/index";
</script>