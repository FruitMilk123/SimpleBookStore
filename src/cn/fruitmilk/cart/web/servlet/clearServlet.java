package cn.fruitmilk.cart.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.fruitmilk.cart.domain.Cart;

public class clearServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.clear();
		
		request.getRequestDispatcher("/jsps/cart/list.jsp").forward(request, response);
	}

}
