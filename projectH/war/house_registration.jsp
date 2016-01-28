<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    String heightFlag = (String)session.getAttribute("heightFlag");//フラグを取得
    	if(heightFlag == null) heightFlag = "true";
    
    String widthFlag = (String)session.getAttribute("widthFlag");//フラグを取得
    	if(widthFlag == null) widthFlag = "true";
    
    String depthFlag = (String)session.getAttribute("depthFlag");//フラグを取得
    	if(depthFlag == null) depthFlag = "true";
    
    String yearFlag = (String)session.getAttribute("yearFlag");//フラグを取得
    	if(yearFlag == null) yearFlag = "true";    

    String lengthFlag = (String)session.getAttribute("lengthFlag");//フラグを取得
    	if(lengthFlag == null) lengthFlag = "true";  
    
    String diameterFlag = (String)session.getAttribute("diameterFlag");//フラグを取得
    	if(diameterFlag == null) diameterFlag = "true";
    
    String conectionFlag = (String)session.getAttribute("conectionFlag");//フラグを取得
    	if(conectionFlag == null) conectionFlag = "true";
    
   
   
    String cityFlag = (String)session.getAttribute("cityFlag");//フラグを取得
    	if(cityFlag == null) cityFlag = "true";
    %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ビニールハウス登録</title>
</head>
<body>
<form action="/HouseNew" method="post">
<%if(heightFlag.equals("false")){ %>
<font color="#ff0000"> 高さが正しく入力されていません </font><br>
<%} %>
<%if(widthFlag.equals("false")){ %>
<font color="#ff0000"> 間口が正しく入力されていません </font><br>
<%} %>
<%if(depthFlag.equals("false")){ %>
<font color="#ff0000"> 奥行が正しく入力されていません </font><br>
<%} %>
ビニールハウスの大きさ:
高さ<input type="text" name="height" size="3">m
間口<input type="text" name="width" size="3">m
奥行<input type="text" name="depth" size="3">m
<br>
<%if(yearFlag.equals("false")){ %>
<font color="#ff0000"> 経過年月が正しく入力されていません </font><br>
<%} %>
経過年月:
<input type="text" name="year" size="3">年
<br>
<%if(lengthFlag.equals("false")){ %>
<font color="#ff0000"> 厚さが正しく入力されていません </font><br>
<%} %>
<%if(diameterFlag.equals("false")){ %>
<font color="#ff0000"> 直径が正しく入力されていません </font><br>
<%} %>
パイプの厚さと太さ:
厚さ<input type="text" name="length" size="3">mm
直径<input type="text" name="diameter" size="3">mm
<br>
<%if(conectionFlag.equals("false")){ %>
<font color="#ff0000"> 接続方法が入力されていません </font><br>
<%} %>
ジョイントの接続方法:
<input type="radio" name="conection" value="外ジョイント接続">外ジョイント接続
<input type="radio" name="conection" value="スエッジ接続">スエッジ接続
<br>

<%if(cityFlag.equals("false")){ %>
<font color="#ff0000"> 市町村が正しく入力されていません </font><br>
<%} %>
ビニールハウスの場所:

市町村

<select name="city">
<option value="null">-選択してください-</option>
<option value="甲府">甲府</option>
<option value="北杜">北杜</option>
<option value="南部">南部</option>
<option value="富士吉田">富士吉田</option>
<option value="大月">大月</option>
</select>
<br>
<input type="submit" value="完了"><br>
</form>
</body>
</html>