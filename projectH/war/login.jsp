<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>危険通知システム</title>
</head>
<body>
<form action="/check" method="post">
ID:
<input type="text" name="id"><br>
パスワード:
<input type="password" name="pass"><br>
<input type="submit" value="ログイン"><br>
</form>
<form action="user_registration.jsp" method="post">
<input type="submit" value="新規登録"><br>
</form>
</body>
</html>