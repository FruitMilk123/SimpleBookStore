package cn.fruitmilk.user.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.fruitmilk.cart.domain.Cart;
import cn.fruitmilk.user.dao.UserException;
import cn.fruitmilk.user.domain.User;
import cn.fruitmilk.user.service.UserService;
import cn.itcast.utils.CommonUtils;

public class loginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		UserService us = new UserService();
		
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		try {		
			request.getSession().setAttribute("user", us.login(form));
			
			//在用户登录成功后，给用户一辆购物车(即在session中保存一个cart对象)
			request.getSession().setAttribute("cart", new Cart());
			
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/jsps/user/login.jsp").forward(request, response);
		}
	}
}
