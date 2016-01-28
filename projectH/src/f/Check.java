package f;


import java.io.IOException;
import javax.jdo.PersistenceManager;
import javax.servlet.http.*;
import javax.jdo.Query;

import f.PMF;

import java.util.List;

public class Check extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    	
        String id = req.getParameter("id");  // IDの取得
        String pass = (req.getParameter("pass"));    // パスワードの取得
        boolean loginflag = false;
        HttpSession session = req.getSession();
        
        PersistenceManager pm = null;
        pm = PMF.get().getPersistenceManager();
        Query query = pm.newQuery(User_info.class);
        List<User_info> informations = (List<User_info>) query.execute();

        //ユーザー情報が登録されているデータストアから一致するidとpassを探す
        for ( User_info info : informations )
        {
        	if(info.getId().equals(id) && info.getPass().equals(pass))
        	{
        		session.setAttribute("name", info.getName());//マイページで使用するためにsessionにユーザー名を登録する
        		session.setAttribute("id", id);
                resp.sendRedirect("/mypage.jsp");
                loginflag = true;
        	}
        }
    	if (!loginflag)
    	{
        resp.sendRedirect("/failed.jsp");
    	}
    }
}
