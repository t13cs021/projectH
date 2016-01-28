package f_test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import f.Check;
import f.PMF;
import f.User_info;

public class CheckTest {

	
	/*
	 * よくわからないけどこれがないとうまくいかない.
	 * 多分ローカルのデータベースを定義してるのかも？
	 * *********************************************************************************************************************************************
	 */
	private final LocalServiceTestHelper helper = 
			new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	@Before
	public void setUp()
	{
		helper.setUp();
	}
	@After
	public void tearDown()
	{
		helper.tearDown();
	}
	/*
	 * *********************************************************************************************************************************************
	 */
	
	
	@Test
	public void データストアに登録してあるidとパスワードを入力するとマイページに遷移する() {
		
		
		Check c = new Check();

		MockHttpServletRequest req = new MockHttpServletRequest();
		MockHttpServletResponse resp = new MockHttpServletResponse();

		PersistenceManager pm = null;
		pm = PMF.get().getPersistenceManager();	
		User_info info = new User_info("08011111111", "12", "yamamoto"); //test用のユーザー登録
		try 
		{
			pm.makePersistent(info);

			//ログイン画面での入力	
			req.setParameter("id", "08011111111");
			req.setParameter("pass", "12");
			req.setParameter("name", "yamamoto");

			try 
			{
				//reqとしてユーザー情報の入力によってrespにresp.sendRedirect("/mypage.jsp")が入っているはず.
				c.doPost(req, resp);
			} 
			catch (IOException e) 
			{
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		} 
		finally 
		{
			pm.close();
		}
		
		assertEquals("/mypage.jsp",resp.getRedirectedUrl());
	}

}
