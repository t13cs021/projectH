package f;


import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

import f.PMF;
import f.User_info;

/**
 * 概要：該当するビニールハウスの削除を行うクラス
 * 機能：
 * ビニールハウス変更・削除画面で削除ボタンが押下された場合に、
 * 該当するビニールハウスのデータをデータストアから削除する
 **/
public class Del extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        
    	/** 削除するビニールハウスのIDを取得する*/
    	HttpSession session = req.getSession();
    	long houseId = Long.parseLong(req.getParameter("houseId"));
    
    	
    	/** データストアから該当するビニールハウスを探し、データストアから削除する*/
        PersistenceManager pm = null;
        try {
            pm = PMF.get().getPersistenceManager();
            Query query = pm.newQuery(House_info.class);
            query.setFilter("id == houseid");
            query.declareParameters("long houseid");
            List<House_info> hinformations = (List<House_info>) query.execute(houseId);
            pm.deletePersistentAll(hinformations);
           
            
        } finally {
            if (pm != null && !pm.isClosed())
                pm.close();
        }

        /** ビニールハウス削除完了画面へ遷移*/
        resp.sendRedirect("/house_delete_complete.jsp");
        }
}
