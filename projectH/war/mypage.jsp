<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.Date,java.text.SimpleDateFormat" %>

<%@ page import="f.PMF"%>
<%@ page import="f.User_info"%>
<%@ page import="f.House_info"%>
<%@ page import="f.Calculate"%>
<%@ page import="f.W_City"%>
<%@ page import="javax.jdo.PersistenceManager"%>
<%@ page import="javax.jdo.Query"%>
<%@ page import="java.text.MessageFormat"%>
<%@ page import="java.util.List"%>
<%@ page import="java.lang.Math.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">




<%
	String userId = (String)session.getAttribute("id");//ユーザーIDを取得
	
	
	
	Calculate c = new Calculate();	
	
	//おまじない
	PersistenceManager pm = null;
	pm = PMF.get().getPersistenceManager();
	Query query = pm.newQuery(House_info.class);
	query.setFilter("user_id == id");
	query.declareParameters("String id");
	query.setOrdering("date asc");//リストを昇順に並べ替え
	List<House_info> informations = (List<House_info>) query.execute(userId);

	String userName = (String)session.getAttribute("name");//セッションにsetされているユーザー名を取得

	int house = 1;//ハウス番号
	int warning[] = new int[informations.size()];//各ハウスの警告を保存しておく配列
	long houseid;	//データストアに登録されているハウスのID
	String city_name = null;//市町村の名前
	
	
	String h_time[] = new String[informations.size()];		//各ハウスの警告のある時間
	String h_wheather[] = new String[informations.size()];	//各ハウスの天気データ
	double h_wind[] = new double[informations.size()];		//各ハウスの風のデータ
	double h_snow[] = new double[informations.size()];		//各ハウスの雪のデータ
	int flag = 0;			//警告あり，なしのフラグ
	int houseNum = 0;		//warning[]の添字として使っている
	
	for( House_info hinfo : informations )
	{
		
		if(hinfo.getCity().equals("甲府"))
		{
			city_name = "w-kofu.txt";
		}
		else if(hinfo.getCity().equals("南部"))
		{
			city_name = "w-nanbu.txt";
		}
		else if(hinfo.getCity().equals("北杜"))
		{
			city_name = "w-hokuto.txt";
		}
		else if(hinfo.getCity().equals("富士吉田"))
		{
			city_name = "w-fujiyoshida.txt";
		}
		else if(hinfo.getCity().equals("大月"))
		{
			city_name = "w-otuki.txt";
		}
		
		W_City w = new W_City(city_name);		//該当する市町村の気象データ
		String time[] = w.getTime();			//時間
		String wheather[] = w.getWheather();	//天気
		double wind[] = w.getWind();			//風
		double snow[] = w.getSnow();			//雪
		boolean warningflag = true;				//警告の有無
		int i = 0;	
		
		/***
		警告が見つかるまで気象データを走査し、警告箇所が見つかったらその時の時間、風、雪の情報を該当するビニールハウスに与える
		***/
		while(warningflag && i < wind.length )
		{
			// 警告があるかどうか、その警告は何によるものかを判定する
			warning[houseNum] = c.compare(hinfo.getConection(), hinfo.getWidth(), hinfo.getHeight(), hinfo.getYear(), wind[i], snow[i]);
			
			// 警告の種類が降雪量であった場合の、天気が雨であった場合の例外処理
			if ( warning[houseNum] == 1 && wheather[i].equals("rain"))
			{
				warning[houseNum] = 0;
			}
			
			// 警告のあるハウスに時間、風、雪、警告の種類の情報を渡す
			if ( warning[houseNum] != 0)
			{
				h_time[houseNum] = time[i];
				h_wind[houseNum] = wind[i];
				h_snow[houseNum] = snow[i];
				warningflag = false;
			}
			i++;
		}
			
		//もしどれか一つでもハウスに警告があったらflag=1
		if ( warning[houseNum] != 0)
		{
		flag = 1;
		}
		houseNum++;
	}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ビニールハウス倒壊防止サービス</title>
<!--Load the AJAX API-->
<body>
	<br>
	<font size="5"><%out.print(userName); %>さんのマイページ</font>
	<br>
	<!--  耐久係数 <%//out.print(dFactor(year)); %>
	<br>
	風速の基準値 <%//out.print(windBasis(conection,width,height)); %>
	<br>
	積雪の基準値 <%//out.print(snowBasis(conection,width,height)); %>
	<br>
	-->
	<%if(flag != 0){ %>
	<font size="5" color="#ff0000">※<%out.print(userName); %>さんのビニールハウスには倒壊の危険性があります</font>
	<%} %>
	<br>
	<form action="/house_registration.jsp" method="post">
	ビニールハウス一覧
	
	<input type="submit" value="登録">
	</form>
	<table border="1">
		<tr>
			<th>ハウスNo.</th>
			<th>場所</th>
			<th>警告</th>
			<th>時間</th>
			<th>風</th>
			<th>積雪量</th>
		</tr>
		
	<% 
	
	houseNum = 0;
	for ( House_info hinfo : informations)
	{
		houseid = hinfo.getId();
		
	%>
		<tr>
		
			<td>
			<!-- リンクをクリックするとそのハウスの情報変更画面にとぶ -->
			<a href="/house_change.jsp?data=<%=houseid%>"><% out.print(house);%></a>
			</td>
			<td><% out.print(hinfo.getCity()); %></td>
			<td><% 
			if(warning[houseNum] != 0) out.print("あり");
			else out.print("なし"); %> </td>
			<td><%
			if(warning[houseNum] != 0)
			{
				out.print(h_time[houseNum]);
			}
			else {%>
					-<%} 
			%></td>
			<td><%
			if(warning[houseNum] == 2 || warning[houseNum] == 3)
			{
				out.print(h_wind[houseNum]);
			}
				else {%>
					- <%} 
			%></td>
			<td><%
			if(warning[houseNum] == 1 || warning[houseNum] == 3)
			{
				out.print(h_snow[houseNum]);
			}
			else {%>
					- <%} 
			house++;
			%></td>
		</tr>
	<%
	houseNum++;
	}
	%>	
		
	</table>
	<br>
	
</body>
</html>

