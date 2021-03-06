package f;


import java.io.IOException;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

import org.datanucleus.util.StringUtils;

import java.math.BigDecimal;


import f.PMF;

public class HouseNew extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		HttpSession session = req.getSession();

		boolean flag = true; //入力に誤りがあった場合にはfalseにする
		double height = -1;
		double width = -1;
		double depth = -1;
		int year = -1;
		double length = -1;
		double diameter = -1;
		String conection = null;
		
		String user_id = (String)session.getAttribute("id");  // ユーザーIDの取得

		try{
			height = Double.parseDouble( req.getParameter("height"));  // 高さの取得
			session.setAttribute("heightFlag", "true");
		}catch(NumberFormatException nfex){
			flag = false;
			session.setAttribute("heightFlag", "false");
		}
		if(scalecheck(height) > 0 || height == 0){
			flag = false;
			session.setAttribute("heightFlag", "false");
		}

		try{
			width = Double.parseDouble( req.getParameter("width"));  // 間口の取得
			session.setAttribute("widthFlag", "true");
		}catch(NumberFormatException nfex){
			flag = false;
			session.setAttribute("widthFlag", "false");
		}
		if(scalecheck(width) > 0 || width == 0){
			flag = false;
			session.setAttribute("widthFlag", "false");
		}

		try{
			depth = Double.parseDouble( req.getParameter("depth"));    // 奥行の取得
			session.setAttribute("depthFlag", "true");
		}catch(NumberFormatException nfex){
			flag = false;
			session.setAttribute("depthFlag", "false");
		}
		if(scalecheck(depth) > 0 || depth == 0){
			flag = false;
			session.setAttribute("depthFlag", "false");
		}

		try{
			year = Integer.parseInt( req.getParameter("year"));    // 経過年数の取得
			session.setAttribute("yearFlag", "true");
		}catch(NumberFormatException nfex){
			flag = false;
			session.setAttribute("yearFlag", "false");
		}
		if(year < 0){
			flag = false;
			session.setAttribute("yearFlag", "false");
		}

		try{
			length = Double.parseDouble( req.getParameter("length"));    // 厚さの取得
			session.setAttribute("lengthFlag", "true");
		}catch(NumberFormatException nfex){
			flag = false;
			session.setAttribute("lengthFlag", "false");
		}
		if(scalecheck(length) > 0 || length == 0){
			flag = false;
			session.setAttribute("lengthFlag", "false");
		}

		try{
			diameter = Double.parseDouble( req.getParameter("diameter"));    // 直径の取得
			session.setAttribute("diameterFlag", "true");
		}catch(NumberFormatException nfex){
			flag = false;
			session.setAttribute("diameterFlag", "false");
		}
		if(scalecheck(diameter) > 0 || diameter == 0){
			flag = false;
			session.setAttribute("diameterFlag", "false");
		}

		conection = req.getParameter("conection");		// 接続方法の取得
		if(StringUtils.isEmpty(conection)){
			flag = false;
			session.setAttribute("conectionFlag", "false");
		}
		else{
			session.setAttribute("conectionFlag", "true");
		}



		String city = (req.getParameter("city"));    // 市町村の取得
		if(city.equals("null")){
			flag = false;
			session.setAttribute("cityFlag", "false");
		}
		else{
			session.setAttribute("cityFlag", "true");
		}

		if(flag){
			House_info h_info = new House_info(user_id, height, width, depth, year, length, diameter, conection, city, new Date());
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				pm.makePersistent(h_info);

			} finally {
				pm.close();
			}	
			resp.sendRedirect("/house_complete.jsp");
		}
		else{
			resp.sendRedirect("/house_registration.jsp");
		}
	}
	
	// double型の数値の桁数を判定する
	public int scalecheck(double dnum){
		int sc = 0;
		BigDecimal bi = new BigDecimal(String.valueOf(dnum));

		if (bi.compareTo(BigDecimal.valueOf(0)) < 0) {
			// 値がマイナスだった場合の処理
			sc = 1;
		} else if (bi.precision() - bi.scale() > 2) {
			// 整数部の桁数が上限を超えてる場合の処理
			sc = 2;
		} else if (bi.scale() > 2) {
			// 小数点以下の桁数が上限を超えてる場合の処理
			sc = 3;
		}
		
		return sc;
	}
}
