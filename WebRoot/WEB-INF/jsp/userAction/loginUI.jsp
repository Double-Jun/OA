<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Itcast OA</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jsp" %>
	<script type="text/javascript">
		$(function(){
			document.forms[0].loginName.focus();
		});
		
		// 在被嵌套时就刷新上级窗口
		if(window.parent != window){
			window.parent.location.reload(true);
		}
	</script>
</head>

<body leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 class=PageBody >



<!-- 显示表单 -->
<s:form action="user_login" focusElement="loginNameInput">
    <div id="CenterAreaBg"> 
        <div id="CenterArea">
            <div id="LogoImg"><img border="0" src="${pageContext.request.contextPath}/style/blue/images/logo.png" /></div>
            <div id="LoginInfo">
                <table BORDER=0 CELLSPACING=0 CELLPADDING=0 width=100%>
                	<tr>
                		<td colspan="2"><!-- 显示错误 -->
							<font color="red"><s:fielderror/></font>
                		</td>
                	</tr>
                	 <tr>
                	 	<td>用户名：</td>
                	 	<td><s:textfield name="loginName"></s:textfield></td>
                	 </tr>
                	 <tr>
                	 	<td>密　码：</td>
                	 	<td><s:password name="password" showPassword="false"></s:password></td>
                	 </tr>
                	 <tr>
                	 	<td colspan="2"><s:submit name="提交"></s:submit></td>
                	 </tr>
                </table>
            </div>
        </div>
    </div>
    </s:form>
</body>

</html>

