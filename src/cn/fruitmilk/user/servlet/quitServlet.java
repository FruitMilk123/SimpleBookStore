package cn.fruitmilk.user.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class quitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//�û��˳���Servlet��ֱ������session����
		
		request.getSession().invalidate(); //invalidate��Ϊ����session�ķ���
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

}
