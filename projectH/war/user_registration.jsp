<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.Date,java.text.SimpleDateFormat" %>
    
    <%String idFlag = (String)session.getAttribute("idFlag");//フラグを取得
    	if(idFlag == null) idFlag = "true";
      String firstFlag = (String)session.getAttribute("firstFlag");
      	if(firstFlag ==null) firstFlag = "true";
      String lastFlag = (String)session.getAttribute("lastFlag");//フラグを取得
    	if(lastFlag == null) lastFlag = "true";	
      String passFlag = (String)session.getAttribute("passFlag");//フラグを取得
    	if(passFlag == null) passFlag = "true";
      String repassFlag = (String)session.getAttribute("repassFlag");//フラグを取得
    	if(repassFlag == null) repassFlag = "true";
    	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/new" method="post">
<%if(lastFlag.equals("false")){ %>
<font color="#ff0000"> 名字が正しく入力されていません </font><br>
<%} %>
<%if(firstFlag.equals("false")){ %>
<font color="#ff0000"> 名前が正しく入力されていません </font><br>
<%} %>
名字:
<input type="text" name="last">
名前:
<input type="text" name="first">
<br><br>
<%if(idFlag.equals("false0")){ %>
<font color="#ff0000"> 電話番号が正しく入力されていません </font><br>
<%} %>
<%if(idFlag.equals("false1")){ %>
<font color="#ff0000"> その電話番号は既に使用されています </font><br>
<%} %>
電話番号:
<input type="text" name="id">
<br><br>
<%if(passFlag.equals("false")){ %>
<font color="#ff0000"> パスワードが正しく入力されていません </font><br>
<%} %>
パスワード:
<input type="text" name="pass"><br><br>
<%if(repassFlag.equals("false")){ %>
<font color="#ff0000"> パスワードの再入力が間違っています </font><br>
<%} %>
パスワード(再入力):
<input type="text" name="repass"><br><br>

<input type="submit" value="登録"><br>
</form>
</body>
</html>