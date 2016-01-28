package f;


import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;
import javax.jdo.Query;

import f.PMF;

public class New extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    	
        String id = req.getParameter("id");  // 電話番号の取得
        String last = req.getParameter("last");
        String first = req.getParameter("first");  // 名前の取得
        String pass = (req.getParameter("pass"));    // パスワードの取得
        String repass = (req.getParameter("repass"));    // 再入力パスワードの取得
        boolean flag = true; //入力に誤りがあった場合にはfalseにする
        HttpSession session = req.getSession(); //セッションの設定
        
        PersistenceManager pm = null;
        pm = PMF.get().getPersistenceManager();
        Query query = pm.newQuery(User_info.class);
        List<User_info> informations = (List<User_info>) query.execute();
        
        
        if( id.length() == 0 || id.length() > 20){
        	flag = false;
        	session.setAttribute("idFlag", "false0");
        }
        else {
        	//ユーザー情報が登録されているデータストアから重複するidが無いか確認する
            for ( User_info info : informations ){
            	if(info.getId().equals(id)){
            		flag = false;
            		session.setAttribute("idFlag", "false1");
            	}
            }
            if(flag) session.setAttribute("idFlag", "true");
        }     
        
        if( last.length() == 0 || last.length() > 50){
        	flag = false;
        	session.setAttribute("lastFlag", "false");
        }
        else session.setAttribute("lastFlag", "true");
        
        if( first.length() == 0 || first.length() > 50){
        	flag = false;
        	session.setAttribute("firstFlag", "false");
        }
        else session.setAttribute("firstFlag", "true");
        
        
        
        if(pass.length() == 0 || pass.length() > 100) {
        	flag = false;
        	session.setAttribute("passFlag", "false");
        }
        else session.setAttribute("passFlag", "true");
        
        if(repass.length() == 0 || repass.length() > 100 || !repass.equals(pass)) {
        	flag = false;
        	session.setAttribute("repassFlag", "false");
        }
        else session.setAttribute("repassFlag", "true");
        
        // データが不正の場合には登録しない
        if (flag) {
        	String name = last+" "+first;
        	User_info info = new User_info(id, pass, name);
        	try {
        		pm.makePersistent(info);
        		
        	} finally {
        		pm.close();
        	}
        	resp.sendRedirect("/user_complete.jsp");
        }
        else{
        	resp.sendRedirect("/user_registration.jsp");
        }
    }
}
