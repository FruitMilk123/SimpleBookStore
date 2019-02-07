package cn.fruitmilk.user.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.fruitmilk.user.dao.UserException;
import cn.fruitmilk.user.domain.User;
import cn.fruitmilk.user.service.UserService;
import cn.itcast.utils.CommonUtils;

public class registServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		UserService us = new UserService();
		
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		form.setCode(CommonUtils.uuid() + CommonUtils.uuid());
		form.setUid(CommonUtils.uuid());
		
		Map<String,String> error = new HashMap<String,String>();
		
		String username = request.getParameter("username");
		if(username==null || username.trim().isEmpty()) {
			error.put("username", "�û�������Ϊ��");
		} else if(username.length()<3 || username.length()>10) {
			error.put("username", "�û������ȱ�����3��10λ֮��");
		}
		
		String password = request.getParameter("password");
		if(password==null || password.trim().isEmpty()) {
			error.put("password", "���벻��Ϊ��");
		} else if(password.length()<6 || password.length()>16) {
			error.put("password", "���볤�ȱ�����6��16λ֮��");
		}
		
		String email = request.getParameter("email");
		if(email==null || email.trim().isEmpty()) {
			error.put("email", "Email����Ϊ��");
		} else if(!email.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]" +
				"+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")) {
			error.put("email", "Email��ʽ����");
		}
		
		if(error.size()>0) {
			request.setAttribute("error", error);
			request.getRequestDispatcher("/jsps/user/regist.jsp").forward(request, response);
			return;
		}
	
		try {
			us.regist(form);
			request.setAttribute("success", "��ϲ��ע��ɹ�");
			request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
		} catch (UserException e) {
			request.setAttribute("msg", e.toString());
			request.getRequestDispatcher("/jsps/user/regist.jsp").forward(request,response);
		}
	}
}
