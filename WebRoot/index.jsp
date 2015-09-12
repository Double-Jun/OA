<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
 	response.sendRedirect(path+"/home_index.html");
 	System.out.println(path);
%>

